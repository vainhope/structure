package com.vain.structure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author vain
 * @Description 树转换为二叉树
 * @date 2018/9/22 13:56
 */
public class Forest {

    private int nodeCount;

    private Map<Integer, Node> nodeMap = new HashMap<>();

    private TreeNode[] tree;

    private MapNode[] map;

    private Node root;

    /**
     * 中间转换
     */
    class MapNode {
        String data;
        /**
         * 左孩子在 数组中的下标位置 -1 表示没有
         */
        int left = -1;
        /**
         * 右孩子在数组中的下标位置 -1 表示没有
         */
        int right = -1;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }
    }

    class TreeNode {
        String data;
        /**
         * 孩子节点链表数据
         */
        Queue<Integer> firstChild;

        public void addChild(int child) {
            firstChild.add(child);
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Queue<Integer> getFirstChild() {
            return firstChild;
        }

        public void setFirstChild(Queue<Integer> firstChild) {
            this.firstChild = firstChild;
        }
    }

    static class Node {
        String data;
        Node left;
        Node right;

        public Node() {
        }

        public Node(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
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

    public Forest(int nodeCount) {
        this.nodeCount = nodeCount;
        tree = new TreeNode[nodeCount];
        map = new MapNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            tree[i] = new TreeNode();
            map[i] = new MapNode();
            nodeMap.put(i, new Node());
        }
        root = nodeMap.get(0);
    }

    /**
     * 设置节点的数据
     *
     * @param index
     * @param data
     */
    public void setTreeData(int index, String data) {
        tree[index].setData(data);
        map[index].setData(data);
        nodeMap.get(index).setData(data);
    }

    /**
     * 设置节点的子节点
     *
     * @param index
     * @param childIndex
     */
    public void addTreeChild(int index, int childIndex) {
        if (tree[index].firstChild == null) {
            tree[index].firstChild = new LinkedList<>();
        }
        tree[index].firstChild.add(childIndex);
    }

    public Node toTranslateBinaryTree() {
        for (int i = 0; i < this.nodeCount; i++) {
            Queue<Integer> childList = tree[i].firstChild;
            int now = -1;
            //上一个孩子节点的下标
            int previous = i;
            if (childList != null) {
                //存在孩子节点的时候，第一个孩子节点为左孩子
                if (!childList.isEmpty()) {
                    now = childList.poll();
                    //标记左节点
                    map[previous].setLeft(now);
                    previous = now;
                }

                //剩下的节点 依次放入右边
                while (!childList.isEmpty()) {
                    now = childList.poll();
                    //标记右节点
                    map[previous].setRight(now);
                    previous = now;
                }
            }
        }

        //根据记录 生成二叉树
        for (int i = 0; i < this.nodeCount; i++) {
            int left = map[i].getLeft();
            int right = map[i].getRight();
            Node node = nodeMap.get(i);
            //获取对应的左右节点数据 设置给二叉树
            if (-1 != left) {
                node.setLeft(nodeMap.get(left));
            }
            if (-1 != right) {
                node.setRight(nodeMap.get(right));
            }
        }
        root = nodeMap.get(0);
        return root;
    }


    public static void inRootNode(Node node) {
        if (null != node) {
            inRootNode(node.getLeft());
            System.out.println(node.getData());
            inRootNode(node.getRight());
        }
    }


    public static void postRootNode(Node node) {
        if (null != node) {
            postRootNode(node.getLeft());
            postRootNode(node.getRight());
            System.out.println(node.getData());
        }
    }

    public static void main(String[] args) {
        Forest forest = new Forest(8);
        forest.setTreeData(0, "A");
        forest.setTreeData(1, "B");
        forest.setTreeData(2, "C");
        forest.setTreeData(3, "D");
        forest.setTreeData(4, "E");
        forest.setTreeData(5, "F");
        forest.setTreeData(6, "G");
        forest.setTreeData(7, "H");
        forest.addTreeChild(0, 1);
        forest.addTreeChild(0, 2);
        forest.addTreeChild(0, 3);
        forest.addTreeChild(1, 4);
        forest.addTreeChild(1, 5);
        forest.addTreeChild(5, 7);
        forest.addTreeChild(3, 6);
        /**
         *                 A
         *          B      C      D
         *      E     F         G
         *          H
         *
         */


        Node root = forest.toTranslateBinaryTree();
        //得到二叉树中序遍历的结果
        System.out.println("中序遍历的结果 :");
        inRootNode(root);
        System.out.println();
        //得到二叉树后序遍历的结果
        System.out.println("后序遍历的结果 :");
        postRootNode(root);
    }

}
