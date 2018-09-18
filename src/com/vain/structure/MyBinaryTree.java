package com.vain.structure;

import java.util.LinkedList;

/**
 * @author vain
 * @Description
 * @date 2018/9/13 21:26
 */
public class MyBinaryTree {
    public static void main(String[] args) {
        /**
         *                  a
         *              b      c
         *          d      e     f
         *         g      h        i
         前序遍历：根节点->左子树->右子树
         中序遍历：左子树->根节点->右子树
         后序遍历：左子树->右子树->根节点
         */

        Node root = new Node("a");

        Node d = new Node("d");
        Node g = new Node("g");
        d.setLeft(g);


        Node b = new Node("b");
        b.setLeft(d);

        root.setLeft(b);
        Node f = new Node("f");

        f.setRight(new Node("i"));

        Node c = new Node("c");
        Node e = new Node("e");
        e.setLeft(new Node("h"));
        c.setLeft(e);
        c.setRight(f);
        root.setRight(c);
        //前序遍历的结果应该是：  a b d g c e h f i
        Traversal.preOrderRec(root);
        System.out.println("----------------");
        //中序遍历的结果应该是：  g d b a h e c f i
        Traversal.inOrderRec(root);
        System.out.println("----------------");
        //后序遍历的结果应该是:   g d b h e i f c a
        Traversal.postOrderRec(root);
        System.out.println("----------------");
        //层次遍历的结果是啥
        Traversal.levelOrderRec(root);
    }

}

class Node {
    private String value;
    private Node left;
    private Node right;

    public Node(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
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
}

class Traversal {
    /**
     * 前序遍历
     *
     * @param root
     */
    public static void preOrderRec(Node root) {
        if (null != root) {
            System.out.println(root.getValue());
            preOrderRec(root.getLeft());
            preOrderRec(root.getRight());
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public static void inOrderRec(Node root) {
        if (null != root) {
            inOrderRec(root.getLeft());
            System.out.println(root.getValue());
            inOrderRec(root.getRight());
        }

    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public static void postOrderRec(Node root) {
        if (null != root) {
            postOrderRec(root.getLeft());
            postOrderRec(root.getRight());
            System.out.println(root.getValue());
        }
    }

    /**
     * 层次遍历
     */
    public static void levelOrderRec(Node root) {
        if (null != root) {
            LinkedList<Node> queue = new LinkedList<>();
            Node node;
            queue.offer(root);
            while (!queue.isEmpty()) {
                node = queue.poll();
                System.out.println(node.getValue());
                if (null != node.getLeft()) {
                    queue.offer(node.getLeft());
                }
                if (null != node.getRight()) {
                    queue.offer(node.getRight());
                }
            }
        }
    }
}