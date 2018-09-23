package com.vain.structure;

import com.vain.structure.Forest.Node;

import java.util.*;

/**
 * @author vain
 * @Description
 * @date 2018/9/22 21:54
 */
public class ReverseForest {
    private Node root;
    private int nodeCount;
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private Map<Node, Integer> indexMap = new HashMap<>();
    private TreeNode[] tree;


    class TreeNode {
        String data;
        Queue<Integer> firstChild;

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

        public void addChild(int child) {
            firstChild.add(child);
        }
    }


    static Node createBinaryTree(String[] array, int index) {
        Node node = null;
        if (index < array.length) {
            node = new Node(array[index]);
            node.left = createBinaryTree(array, index * 2 + 1);
            node.right = createBinaryTree(array, index * 2 + 2);
        }
        return node;
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

    public ReverseForest(Node root) {
        this.root = root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        nodeMap.put(this.nodeCount, root);
        indexMap.put(root, this.nodeCount++);
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (null != node.getLeft()) {
                q.add(node.getLeft());
                nodeMap.put(this.nodeCount, node.getLeft());
                indexMap.put(node.getLeft(), this.nodeCount++);
            }
            if (null != node.getRight()) {
                q.add(node.getRight());
                nodeMap.put(this.nodeCount, node.getRight());
                indexMap.put(node.getRight(), this.nodeCount++);
            }
            tree = new TreeNode[this.nodeCount];
            for (int i = 0; i < nodeMap.entrySet().size(); i++) {
                tree[i] = new TreeNode();
                tree[i].data = nodeMap.get(i).getData();
            }
        }
    }

    public void addTreeChild(int index, int childIndex) {
        if (tree[index].firstChild == null) {
            tree[index].firstChild = new LinkedList<>();
        }
        tree[index].firstChild.add(childIndex);
    }

    public TreeNode[] toTranslateTree() {
        for (int i = 0; i < this.nodeCount; i++) {
            Node node = nodeMap.get(i);
            node = node.getLeft();
            if (null != node) {
                addTreeChild(i, indexMap.get(node));
                while (node.getRight() != null) {
                    addTreeChild(i, indexMap.get(node.getRight()));
                    node = node.getRight();
                }
            }
        }
        return this.tree;
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
        Node root = forest.toTranslateBinaryTree();

        TreeNode[] tree = new ReverseForest(root).toTranslateTree();
        //得到二叉树中序遍历的结果
        System.out.println("中序遍历的结果 :");
        inRootNode(root);
        //得到二叉树后序遍历的结果
        System.out.println("后序遍历的结果 :");
        postRootNode(root);
        for (int i = 0; i < tree.length; i++) {
            System.out.print(i + (tree[i].data) + " ： ");
            Queue<Integer> q = tree[i].firstChild;
            if (q != null) {
                Iterator iterator = q.iterator();
                q.forEach(integer -> System.out.print(iterator.next() + " "));
            } else {
                System.out.print("无孩子节点");
            }
            System.out.println();
        }
    }
}
