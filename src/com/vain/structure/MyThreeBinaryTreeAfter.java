package com.vain.structure;

/**
 * @author vain
 * @Description 线索二叉树的后序遍历
 * @date 2018/9/19 22:10
 */
public class MyThreeBinaryTreeAfter {
    private Node root;

    static class Node {
        private String value;
        private Node left;
        private Node right;
        private Node parent;
        private boolean isLeftThread;
        private boolean isRightThread;

        public Node(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public boolean isLeftThread() {
            return isLeftThread;
        }

        public void setLeftThread(boolean leftThread) {
            isLeftThread = leftThread;
        }

        public boolean isRightThread() {
            return isRightThread;
        }

        public void setRightThread(boolean rightThread) {
            isRightThread = rightThread;
        }
    }

    static Node createBinaryTree(String[] array, int index) {
        Node node = null;
        if (index < array.length) {
            node = new Node(array[index]);
            node.left = createBinaryTree(array, index * 2 + 1);
            node.right = createBinaryTree(array, index * 2 + 2);

            if (null != node.getLeft()) {
                node.left.parent = node;
            }
            if (null != node.getRight()) {
                node.right.parent = node;
            }
        }
        return node;
    }

    void postThreadOrder(Node node) {
        if (null == node) {
            return;
        }
        postThreadOrder(node.getLeft());
        postThreadOrder(node.getRight());
        if (null == node.getLeft()) {
            //左节点为空 将左节点指向为前驱节点
            node.left = root;
            node.isLeftThread = true;
        }
        if (null != root && root.getRight() == null) {
            //将前驱节点的右节点指向当前节点
            root.right = node;
            root.isRightThread = true;
        }
        root = node;
    }

    void postThreadList(Node root) {
        Node node = root;
        while (null != node && !node.isLeftThread) {
            node = node.getLeft();
        }

        Node preNode = null;
        while (null != node) {
            //右节点为线索
            if (node.isRightThread) {
                System.out.println(node.getValue());
                preNode = node;
                node = node.getRight();
            } else {
                if (node.getRight() == preNode) {
                    System.out.println(node.getValue());
                    if (node == root) {
                        return;
                    }
                    preNode = node;
                    node = node.parent;
                } else {
                    node = node.right;
                    while (node != null && !node.isLeftThread) {
                        node = node.getLeft();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] array = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        Node root = createBinaryTree(array, 0);

        MyThreeBinaryTreeAfter tree = new MyThreeBinaryTreeAfter();
        tree.postThreadOrder(root);
        System.out.println("后序按后继节点遍历线索二叉树结果：");
        tree.postThreadList(root);
    }
}
