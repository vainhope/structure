package com.vain.structure;

/**
 * @author vain
 * @Description 线索二叉树
 * @date 2018/9/18 23:23
 */
public class MyThreadBinaryTree {

    private Node root;

    static class Node {
        String value;
        Node left;
        Node right;
        /**
         * false 指向子节点  true标识前驱或后驱线索
         */
        boolean isLeftThread = false;
        boolean isRightThread = false;

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

    /**
     * 按照完全二叉树 生成
     *
     * @param array
     * @param index
     * @return
     */
    static Node createBinaryTree(String[] array, int index) {
        Node node = null;
        if (index < array.length) {
            node = new Node(array[index]);
            node.left = createBinaryTree(array, index * 2 + 1);
            node.right = createBinaryTree(array, index * 2 + 2);
        }
        return node;
    }

    /**
     * 中序线索化二叉树 右子节点的右节点指向父节点的父节点 左子节点的左节点指向父节点的父节点
     *
     * @param node
     */
    void inThreadOrder(Node node) {
        if (null == node) {
            return;
        }
        //处理左边
        inThreadOrder(node.getLeft());

        //变更指向节点
        if (null == node.getLeft()) {
            //初始化的root为空 指向空节点
            node.left = root;
            node.isLeftThread = true;
        }

        if (null != root && root.getRight() == null) {
            //将前节点的右节点 指向当前节点
            root.right = node;
            root.isRightThread = true;
        }
        root = node;
        //处理右边
        inThreadOrder(node.getRight());
    }

    /**
     * 中序遍历（从最左节点开始）
     *
     * @param node
     */
    void inThreadList(Node node) {
        while (null != node && !node.isLeftThread) {
            node = node.getLeft();
        }
        while (null != node) {
            System.out.println(node.getValue());
            //如果右边是数据
            if (node.isRightThread) {
                node = node.getRight();
            } else {
                //右节点不是线索 找右节点的左边开始
                node = node.getRight();
                while (null != node && !node.isLeftThread) {
                    node = node.getLeft();
                }
            }
        }
    }

    /**
     * 中序遍历二叉树 按照前驱的方式遍历（ 找到最有右边的子节点开始倒叙遍历）
     *
     * @param node
     */
    void inPreThreadList(Node node) {
        //找到最后一个右边的节点
        while (null != node && null != node.getRight() && !node.isRightThread) {
            node = node.right;
        }
        while (null != node) {
            System.out.println(node.getValue());
            //左边是线索
            if (node.isLeftThread) {
                node = node.getLeft();
            } else {
                //左边不是线索 开始找左子树的节点
                node = node.getLeft();
                while (null != node.getRight() && !node.isRightThread) {
                    node = node.getRight();
                }
            }
        }
    }

    /**
     * 前序线索话二叉树
     *
     * @param node
     */
    void preThreadOrder(Node node) {
        if (null == node) {
            return;
        }
        if (null == node.getLeft()) {
            //左节点为空 指向前节点
            node.left = root;
            node.isLeftThread = true;
        }
        if (null != root && null != root.getRight()) {
            //前节点指向当前节点
            root.right = node;
            root.isLeftThread = true;
        }

        root = node;

        if (!node.isLeftThread) {
            preThreadOrder(node.getLeft());
        }

        if (!node.isRightThread) {
            preThreadOrder(node.getRight());
        }
    }

    /**
     * 前序遍历线索二叉树
     *
     * @param node
     */
    void preThreadList(Node node) {
        while (null != node) {
            while (!node.isLeftThread) {
                System.out.println(node.getValue());
                node = node.getLeft();
            }
            System.out.println(node.getValue());
            node = node.getRight();
        }
    }


    public static void main(String[] args) {
        String[] array = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        Node root = createBinaryTree(array, 0);

        MyThreadBinaryTree tree = new MyThreadBinaryTree();
        tree.inThreadOrder(root);
        System.out.println("中序按后继节点遍历线索二叉树结果：");
        tree.inThreadList(root);
        System.out.println("\n中序按后继节点遍历线索二叉树结果：");
        tree.inPreThreadList(root);

        Node root2 = createBinaryTree(array, 0);
        MyThreadBinaryTree tree2 = new MyThreadBinaryTree();
        tree2.preThreadOrder(root2);
        tree2.root = null;
        System.out.println("\n前序按后继节点遍历线索二叉树结果：");
        tree.preThreadList(root2);
    }
}
