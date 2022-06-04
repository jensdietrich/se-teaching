package nz.ac.vuw.jenz.jsonparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

/**
 * Utility from https://stackoverflow.com/questions/49413911/antlr4-parse-tree-to-dot-using-dotgenerator .
 * @author Mike Lischke
 */
class DotTreeRepresentation {

    public String display(SimpleTree tree) {
        return display(tree, DotOptions.DEFAULT);
    }

    public String display(SimpleTree tree, DotOptions options) {
        return display(new InOrderTraversal().traverse(tree), options);
    }

    public String display(List<SimpleTree.Node> nodes, DotOptions options) {

        StringBuilder builder = new StringBuilder("graph tree {\n\n");
        Map<SimpleTree.Node, String> nodeNameMap = new HashMap<>();
        int nodeCount = 0;

        if (options.parameters != null) {
            builder.append(options.parameters);
        }

        for (SimpleTree.Node node : nodes) {

            nodeCount++;
            String nodeName = String.format("node_%s", nodeCount);
            nodeNameMap.put(node, nodeName);

            builder.append(String.format("  %s [label=\"%s\", shape=%s];\n",
                    nodeName,
                    node.getLabel().replace("\"", "\\\""),
                    node.isTokenNode() ? options.lexerRuleShape : options.parserRuleShape));
        }

        builder.append("\n");

        for (SimpleTree.Node node : nodes) {

            String name = nodeNameMap.get(node);

            for (SimpleTree.Node child : node.getChildren()) {
                String childName = nodeNameMap.get(child);
                builder.append("  ").append(name).append(" -- ").append(childName).append("\n");
            }
        }

        return builder.append("}\n").toString();
    }
}

class InOrderTraversal {

    public List<SimpleTree.Node> traverse(SimpleTree tree) {

        if (tree == null)
            throw new IllegalArgumentException("tree == null");

        List<SimpleTree.Node> nodes = new ArrayList<>();

        traverse(tree.root, nodes);

        return nodes;
    }

    private void traverse(SimpleTree.Node node, List<SimpleTree.Node> nodes) {

        if (node.hasChildren()) {
            traverse(node.getChildren().get(0), nodes);
        }

        nodes.add(node);

        for (int i = 1; i < node.getChildCount(); i++) {
            traverse(node.getChild(i), nodes);
        }
    }
}

class DotOptions {

    public static final DotOptions DEFAULT = new DotOptions.Builder().build();

    public static final String DEFAULT_PARAMETERS = null;
    public static final String DEFAULT_LEXER_RULE_SHAPE = "box";
    public static final String DEFAULT_PARSER_RULE_SHAPE = "ellipse";

    public static class Builder {

        private String parameters = DEFAULT_PARAMETERS;
        private String lexerRuleShape = DEFAULT_LEXER_RULE_SHAPE;
        private String parserRuleShape = DEFAULT_PARSER_RULE_SHAPE;

        public DotOptions.Builder withParameters(String parameters) {
            this.parameters = parameters;
            return this;
        }

        public DotOptions.Builder withLexerRuleShape(String lexerRuleShape) {
            this.lexerRuleShape = lexerRuleShape;
            return this;
        }

        public DotOptions.Builder withParserRuleShape(String parserRuleShape) {
            this.parserRuleShape = parserRuleShape;
            return this;
        }

        public DotOptions build() {

            if (lexerRuleShape == null)
                throw new IllegalStateException("lexerRuleShape == null");

            if (parserRuleShape == null)
                throw new IllegalStateException("parserRuleShape == null");

            return new DotOptions(parameters, lexerRuleShape, parserRuleShape);
        }
    }

    public final String parameters;
    public final String lexerRuleShape;
    public final String parserRuleShape;

    private DotOptions(String parameters, String lexerRuleShape, String parserRuleShape) {
        this.parameters = parameters;
        this.lexerRuleShape = lexerRuleShape;
        this.parserRuleShape = parserRuleShape;
    }
}

class SimpleTree {

    public static class Builder {

        private Parser parser = null;
        private ParseTree parseTree = null;
        private Set<Integer> ignoredTokenTypes = new HashSet<>();
        private boolean displaySymbolicName = true;

        public SimpleTree build() {

            if (parser == null) {
                throw new  IllegalStateException("parser == null");
            }

            if (parseTree == null) {
                throw new  IllegalStateException("parseTree == null");
            }

            return new SimpleTree(parser, parseTree, ignoredTokenTypes, displaySymbolicName);
        }

        public SimpleTree.Builder withParser(Parser parser) {
            this.parser = parser;
            return this;
        }

        public SimpleTree.Builder withParseTree(ParseTree parseTree) {
            this.parseTree = parseTree;
            return this;
        }

        public SimpleTree.Builder withIgnoredTokenTypes(Integer... ignoredTokenTypes) {
            this.ignoredTokenTypes = new HashSet<>(Arrays.asList(ignoredTokenTypes));
            return this;
        }

        public SimpleTree.Builder withDisplaySymbolicName(boolean displaySymbolicName) {
            this.displaySymbolicName = displaySymbolicName;
            return this;
        }
    }

    public final SimpleTree.Node root;

    private SimpleTree(Parser parser, ParseTree parseTree, Set<Integer> ignoredTokenTypes, boolean displaySymbolicName) {
        this.root = new SimpleTree.Node(parser, parseTree, ignoredTokenTypes, displaySymbolicName);
    }

    public SimpleTree(SimpleTree.Node root) {

        if (root == null)
            throw new IllegalArgumentException("root == null");

        this.root = root;
    }

    public SimpleTree copy() {
        return new SimpleTree(root.copy());
    }

    public String toLispTree() {

        StringBuilder builder = new StringBuilder();

        toLispTree(this.root, builder);

        return builder.toString().trim();
    }

    private void toLispTree(SimpleTree.Node node, StringBuilder builder) {

        if (node.isLeaf()) {
            builder.append(node.getLabel()).append(" ");
        }
        else {
            builder.append("(").append(node.label).append(" ");

            for (SimpleTree.Node child : node.children) {
                toLispTree(child, builder);
            }

            builder.append(") ");
        }
    }

    @Override
    public String toString() {
        return String.format("%s", this.root);
    }

    public static class Node {

        protected String label;
        protected int level;
        protected boolean isTokenNode;
        protected List<SimpleTree.Node> children;

        Node(Parser parser, ParseTree parseTree, Set<Integer> ignoredTokenTypes, boolean displaySymbolicName) {
            this(parser.getRuleNames()[((RuleContext)parseTree).getRuleIndex()], 0, false);
            traverse(parseTree, this, parser, ignoredTokenTypes, displaySymbolicName);
        }

        public Node(String label, int level, boolean isTokenNode) {
            this.label = label;
            this.level = level;
            this.isTokenNode = isTokenNode;
            this.children = new ArrayList<>();
        }

        public void replaceWith(SimpleTree.Node node) {
            this.label = node.label;
            this.level = node.level;
            this.isTokenNode = node.isTokenNode;
            this.children.remove(node);
            this.children.addAll(node.children);
        }

        public SimpleTree.Node copy() {

            SimpleTree.Node copy = new SimpleTree.Node(this.label, this.level, this.isTokenNode);

            for (SimpleTree.Node child : this.children) {
                copy.children.add(child.copy());
            }

            return copy;
        }

        public void normalizeLevels(int level) {

            this.level = level;

            for (SimpleTree.Node child : children) {
                child.normalizeLevels(level + 1);
            }
        }

        public boolean hasChildren() {
            return !children.isEmpty();
        }

        public boolean isLeaf() {
            return !hasChildren();
        }

        public int getChildCount() {
            return children.size();
        }

        public SimpleTree.Node getChild(int index) {
            return children.get(index);
        }

        public int getLevel() {
            return level;
        }

        public String getLabel() {
            return label;
        }

        public boolean isTokenNode() {
            return isTokenNode;
        }

        public List<SimpleTree.Node> getChildren() {
            return new ArrayList<>(children);
        }

        private void traverse(ParseTree parseTree, SimpleTree.Node parent, Parser parser, Set<Integer> ignoredTokenTypes, boolean displaySymbolicName) {

            List<SimpleTree.ParseTreeParent> todo = new ArrayList<>();

            for (int i = 0; i < parseTree.getChildCount(); i++) {

                ParseTree child = parseTree.getChild(i);

                if (child.getPayload() instanceof CommonToken) {

                    CommonToken token = (CommonToken) child.getPayload();

                    if (!ignoredTokenTypes.contains(token.getType())) {

                        String tempText = displaySymbolicName ?
                                String.format("%s: '%s'",
                                        parser.getVocabulary().getSymbolicName(token.getType()),
                                        token.getText()
                                                .replace("\r", "\\r")
                                                .replace("\n", "\\n")
                                                .replace("\t", "\\t")
                                                .replace("'", "\\'")) :
                                String.format("%s",
                                        token.getText()
                                                .replace("\r", "\\r")
                                                .replace("\n", "\\n")
                                                .replace("\t", "\\t"));

                        if (parent.label == null) {
                            parent.label = tempText;
                        }
                        else {
                            parent.children.add(new SimpleTree.Node(tempText, parent.level + 1, true));
                        }
                    }
                }
                else {
                    SimpleTree.Node node = new SimpleTree.Node(parser.getRuleNames()[((RuleContext)child).getRuleIndex()], parent.level + 1, false);
                    parent.children.add(node);
                    todo.add(new SimpleTree.ParseTreeParent(child, node));
                }
            }

            for (SimpleTree.ParseTreeParent wrapper : todo) {
                traverse(wrapper.parseTree, wrapper.parent, parser, ignoredTokenTypes, displaySymbolicName);
            }
        }

        @Override
        public String toString() {
            return String.format("{label=%s, level=%s, isTokenNode=%s, children=%s}", label, level, isTokenNode, children);
        }
    }

    private static class ParseTreeParent {

        final ParseTree parseTree;
        final SimpleTree.Node parent;

        private ParseTreeParent(ParseTree parseTree, SimpleTree.Node parent) {
            this.parseTree = parseTree;
            this.parent = parent;
        }
    }
}