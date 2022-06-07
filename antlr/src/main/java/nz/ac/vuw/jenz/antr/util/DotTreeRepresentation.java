package nz.ac.vuw.jenz.antr.util;

import java.util.*;

/**
 * Utility from https://stackoverflow.com/questions/49413911/antlr4-parse-tree-to-dot-using-dotgenerator .
 * @author Mike Lischke
 */
public class DotTreeRepresentation {

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

