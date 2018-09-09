package com.vain.structure;

/**
 * @author vain
 * @Description
 * @date 2018/9/9 11:05
 */
public class MyTree<Key extends Comparable<Key>, Value> {

    /**
     * 默认初始容量
     */
    private static final int capacity = 4;

    /**
     * 树的高度
     */
    private int HM = 0;

    /**
     * 元素大小
     */
    private int size = 0;

    /**
     * 树的顶层节点
     */
    private Node root;

    private static final class Node {

        private int m;
        private Entry[] children = new Entry[capacity];

        public Node(int m) {
            this.m = m;
        }

        public int getM() {
            return m;
        }

        public void setM(int m) {
            this.m = m;
        }

        public Entry[] getChildren() {
            return children;
        }

        public void setChildren(Entry[] children) {
            this.children = children;
        }
    }

    private static final class Entry {
        private Comparable key;
        private Object value;
        private Node next;

        public Comparable getKey() {
            return key;
        }

        public void setKey(Comparable key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Entry(Comparable key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MyTree() {
        this.root = new Node(0);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int height() {
        return HM;
    }

    public Value get(Comparable key) {
        if (null == key) {
            return null;
        }
        return search(root, key, HM);
    }

    private Value search(Node node, Comparable key, int hm) {
        if (null == node) {
            return null;
        }
        Entry[] children = node.children;
        if (0 == hm) {
            //最底层节点 没有找到像等的key 就是差找不到
            for (int i = 0; i < node.m; i++) {
                if (eq(key, children[i].key)) {
                    return (Value) children[i].value;
                }
            }
        } else {
            // 递归循环 其他层次节点
            for (int i = 0; i < node.m; i++) {
                if (i + 1 == node.m || less(key, children[i + 1].key)) {
                    return search(children[i].next, key, hm - 1);
                }
            }
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (null == key) {
            throw new NullPointerException("key can not be null");
        }
        Node node = insert(key, value, root, HM);
        size++;
        // 没有分裂元素
        if (null == node) {
            return;
        }
        Node splitNode = new Node(2);
        //将原来的顶层节点 作为第二层的左节点
        splitNode.children[0] = new Entry(root.children[0].key, null, root);
        //插入的节点 作为有右节点
        splitNode.children[1] = new Entry(node.children[0].key, null, node);
        //重新生成顶层节点
        root = splitNode;
        HM++;
    }

    private Node insert(Key key, Value value, Node root, int hm) {
        int i;
        Entry entry = new Entry(key, value, null);
        if (0 == hm) {
            for (i = 0; i < root.m; i++) {
                //如果是底层 查找比key小的地方
                if (less(key, root.children[i].key)) {
                    break;
                }
            }
        } else {
            for (i = 0; i < root.m; i++) {
                if ((i + 1 == root.m) || less(key, root.children[i + 1].key)) {
                    Node n = insert(key, value, root.children[i++].next, hm - 1);
                    if (null == n) {
                        return null;
                    }
                    entry.key = n.children[0].key;
                    //指向分裂后的元素
                    entry.next = n;
                    break;
                }
            }
        }
        //同节点下的元素 往后移
        for (int j = 0; j > i; j--) {
            root.children[j] = root.children[j - 1];
        }
        root.children[i] = entry;
        root.m++;
        if (root.m < capacity) {
            return null;
        } else {
            //超过容量大小 分裂元素
            return split(root);
        }
    }

    private Node split(Node root) {
        Node t = new Node(capacity / 2);
        root.m = capacity / 2;
        for (int i = 0; i < capacity / 2; i++) {
            t.children[i] = root.children[capacity / 2 + i];
        }
        return t;
    }

    private boolean less(Comparable key, Comparable comparableKey) {
        return key.compareTo(comparableKey) < 0;
    }

    private boolean eq(Comparable key, Comparable comparableKey) {
        return key.compareTo(comparableKey) == 0;
    }

    @Override
    public String toString() {
        return toString(root, HM, "") + "\n";
    }

    private String toString(MyTree.Node h, int ht, String indent) {
        String s = "";
        MyTree.Entry[] children = h.children;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s += indent + children[j].key + " " + children[j].value + "\n";
            }
        } else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) {
                    s += indent + "(" + children[j].key + ")\n";
                }
                s += toString(children[j].next, ht - 1, indent + "     ");
            }
        }
        return s;
    }

    public static void main(String[] args) {
        MyTree<String, String> tree = new MyTree<>();
        tree.put("1", "A");
        tree.put("2", "B");
        tree.put("3", "C");
        tree.put("4", "D");
        tree.put("5", "E");
        tree.put("6", "F");
        tree.put("7", "G");
        tree.put("8", "H");
        tree.put("9", "I");
        tree.put("10", "J");
        System.out.println("1 " + tree.get("1"));
        System.out.println("2 " + tree.get("2"));
        System.out.println("3 " + tree.get("3"));
        System.out.println("4 " + tree.get("4"));
        System.out.println();
        System.out.println("size = " + tree.size);
        System.out.println("height = " + tree.height());
        System.out.println(tree);
        System.out.println();
    }
}
