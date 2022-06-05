package nz.ac.vuw.jenz.jsonparser.util;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

public class SimpleTree {

    public static class Builder {

        private Parser parser = null;
        private ParseTree parseTree = null;
        private Set<Integer> ignoredTokenTypes = new HashSet<>();
        private boolean displaySymbolicName = true;

        public SimpleTree build() {

            if (parser == null) {
                throw new IllegalStateException("parser == null");
            }

            if (parseTree == null) {
                throw new IllegalStateException("parseTree == null");
            }

            return new SimpleTree(parser, parseTree, ignoredTokenTypes, displaySymbolicName);
        }

        public Builder withParser(Parser parser) {
            this.parser = parser;
            return this;
        }

        public Builder withParseTree(ParseTree parseTree) {
            this.parseTree = parseTree;
            return this;
        }

        public Builder withIgnoredTokenTypes(Integer... ignoredTokenTypes) {
            this.ignoredTokenTypes = new HashSet<>(Arrays.asList(ignoredTokenTypes));
            return this;
        }

        public Builder withDisplaySymbolicName(boolean displaySymbolicName) {
            this.displaySymbolicName = displaySymbolicName;
            return this;
        }
    }

    public final Node root;

    private SimpleTree(Parser parser, ParseTree parseTree, Set<Integer> ignoredTokenTypes, boolean displaySymbolicName) {
        this.root = new Node(parser, parseTree, ignoredTokenTypes, displaySymbolicName);
    }

    public SimpleTree(Node root) {

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

    private void toLispTree(Node node, StringBuilder builder) {

        if (node.isLeaf()) {
            builder.append(node.getLabel()).append(" ");
        } else {
            builder.append("(").append(node.label).append(" ");

            for (Node child : node.children) {
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
        protected List<Node> children;

        Node(Parser parser, ParseTree parseTree, Set<Integer> ignoredTokenTypes, boolean displaySymbolicName) {
            this(parser.getRuleNames()[((RuleContext) parseTree).getRuleIndex()], 0, false);
            traverse(parseTree, this, parser, ignoredTokenTypes, displaySymbolicName);
        }

        public Node(String label, int level, boolean isTokenNode) {
            this.label = label;
            this.level = level;
            this.isTokenNode = isTokenNode;
            this.children = new ArrayList<>();
        }

        public void replaceWith(Node node) {
            this.label = node.label;
            this.level = node.level;
            this.isTokenNode = node.isTokenNode;
            this.children.remove(node);
            this.children.addAll(node.children);
        }

        public Node copy() {

            Node copy = new Node(this.label, this.level, this.isTokenNode);

            for (Node child : this.children) {
                copy.children.add(child.copy());
            }

            return copy;
        }

        public void normalizeLevels(int level) {

            this.level = level;

            for (Node child : children) {
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

        public Node getChild(int index) {
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

        public List<Node> getChildren() {
            return new ArrayList<>(children);
        }

        private void traverse(ParseTree parseTree, Node parent, Parser parser, Set<Integer> ignoredTokenTypes, boolean displaySymbolicName) {

            List<ParseTreeParent> todo = new ArrayList<>();

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
                        } else {
                            parent.children.add(new Node(tempText, parent.level + 1, true));
                        }
                    }
                } else {
                    Node node = new Node(parser.getRuleNames()[((RuleContext) child).getRuleIndex()], parent.level + 1, false);
                    parent.children.add(node);
                    todo.add(new ParseTreeParent(child, node));
                }
            }

            for (ParseTreeParent wrapper : todo) {
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
        final Node parent;

        private ParseTreeParent(ParseTree parseTree, Node parent) {
            this.parseTree = parseTree;
            this.parent = parent;
        }
    }
}
