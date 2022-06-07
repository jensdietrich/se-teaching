package nz.ac.vuw.jenz.antr.util;

import java.util.ArrayList;
import java.util.List;

public class InOrderTraversal {

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
