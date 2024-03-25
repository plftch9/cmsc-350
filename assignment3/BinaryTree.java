/*This class handles the creation and storage of nodes in a binary tree and also 
 * contains methods used to manipulate data inside the nodes
 */

import java.util.Stack;
import java.util.EmptyStackException;

public class BinaryTree {

    Node parent, child;

    public BinaryTree(String input) throws InvalidTreeSyntax {

        Stack<Node> nodeStack = new Stack<>();
        String[] strArray = input.substring(1, input.length() - 1)
                .split("(?<=\\()|(?=\\()|(?<=\\))|(?=\\))");

        parent = new Node(strArray[0]);

        for (int i = 1; i < strArray.length - 1; i++) {
            if (strArray[i].equals("(")) {
                nodeStack.push(parent);

                if (child != null) {
                    parent = child;
                }
            } else if (strArray[i].equals(")")) {
                try {
                    child = parent;
                    parent = nodeStack.pop();
                } catch (EmptyStackException e) {
                    throw new InvalidTreeSyntax("Double check parentheses");
                }
            } else {
                child = new Node(strArray[i]);
                if (parent != null) {
                    parent.addChild(child);
                }
            }

        }

        if (this.recNodes(parent) * 3 != input.length()) {
            throw new InvalidTreeSyntax("Check string for syntax");
        }
    }

    static class Node {
        private String data;
        private Node left;
        private Node right;

        Node(String data) {
            this.data = data;
        }

        private void addChild(Node child) throws InvalidTreeSyntax {
            if (this.left == null) {
                this.setLeft(child);
            } else if (this.right == null) {
                this.setRight(child);
            } else {
                throw new InvalidTreeSyntax("Too many children added to tree");
            }
        }

        private void setLeft(Node newLeft) {
            left = newLeft;
        }

        private void setRight(Node newRight) {
            right = newRight;
        }

        public String toString() {
            return toString(this);
        }

        private static String toString(Node root) {

            return (root == null) ? "" : "(" + root.data + toString(root.left) + toString(root.right) + ")";
        }
    }

    public boolean isBalanced() {
        return recIsBalanced(this.parent);
    }

    // recursive
    private boolean recIsBalanced(Node root) {

        if (root == null) {
            return true;
        }

        return (Math.abs(recHeight(root.left) - recHeight(root.right)) <= 1 &&
                (recIsBalanced(root.left) && recIsBalanced(root.right)));
    }

    public boolean isFull() {
        return recIsFull(this.parent, recHeight(this.parent), 0);
    }

    // recursive
    private boolean recIsFull(Node root, int height, int index) {

        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return height == index + 1;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        return recIsFull(root.left, height, index + 1) && recIsFull(root.right, height, index + 1);
    }

    public boolean isProper() {
        return recIsProper(this.parent);
    }

    // recursive
    private boolean recIsProper(Node root) {
        if (root == null) {
            return true;
        }
        return ((root.left != null || root.right == null) && (root.left == null || root.right != null))
                && (recIsProper(root.left) && recIsProper(root.right));
    }

    public int height() {
        return recHeight(this.parent) - 1;
    }

    // recursive
    private int recHeight(Node root) {
        return (root == null) ? 0 : 1 + Math.max(recHeight(root.left), recHeight(root.right));
    }

    public int nodes() {
        return recNodes(this.parent);
    }

    // recursive
    private int recNodes(Node root) {
        return (root == null) ? 0 : 1 + recNodes(root.left) + recNodes(root.right);
    }

    public String inOrder() {

        return recInOrder(this.parent);
    }

    // recursive
    private String recInOrder(Node root) {
        return (root == null) ? "" : "(" + recInOrder(root.left) + root.data + recInOrder(root.right) + ")";
    }

    public String toString() {
        return parent.toString();
    }
}
