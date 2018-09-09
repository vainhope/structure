package com.vain.structure;

/**
 * @author vain
 * @Description 双向链表 无闭合
 * @date 2018/9/8 22:09
 */
public class MyLinkList<E> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Node previous;
        private Node next;
        private E value;

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node(Node previous, Node next, E value) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int length() {
        return size;
    }

    /**
     * 添加元素到头部
     *
     * @param value
     */
    public void addFirst(E value) {
        if (null == first) {
            //空链表
            Node node = new Node(null, null, value);
            this.first = node;
            this.last = node;
            size++;
        } else {
            Node node = new Node(null, this.first, value);
            //头元素的头指向新元素 替换头元素为新元素
            this.first.previous = node;
            this.first = node;
            size++;
        }
    }

    /**
     * 添加元素到尾部
     *
     * @param value
     */
    public void addLast(E value) {
        if (null == last) {
            Node node = new Node(null, null, value);
            this.last = node;
            this.first = node;
            size++;
        } else {
            Node node = new Node(this.last, null, value);
            this.last.next = node;
            this.last = node;
            size++;
        }
    }

    /**
     * 添加元素到指定元素前面
     *
     * @param element
     * @param value
     */
    public void addPrevious(E element, E value) {
        //循环 获取指定element的位置
        Node index = this.first;
        while (null != index) {
            if (index.value == element) {
                break;
            }
            index = index.next;
        }
        if (null == index) {
            return;
        }
        //element的头指向新元素
        if (null != index.previous) {
            Node node = new Node(index.previous, index, value);
            //element原来的上一个元素指向的下个元素 重新指向添加的元素
            index.previous.next = node;
            //element的上一个元素指向添加的元素
            index.previous = node;
        } else {
            addFirst(value);
        }

        size++;
    }

    public void addNext(E element, E value) {
        Node index = this.first;
        while (null != index) {
            if (index.value == element) {
                break;
            }
            index = index.next;
        }
        if (null == index) {
            return;
        }
        if (null != index.next) {
            //添加到element的下一个元素  就是新元素的头元素 是element的旧下一个元素 新元素的下个元素 指向这个旧元素
            Node node = new Node(index, index.next, value);
            index.next.previous = node;
            index.next = node;
        } else {
            addLast(value);
        }
        size++;
    }

    public void remove(E value) {
        Node index = this.first;
        while (null != index) {
            if (index.value == value) {
                break;
            }
            index = index.next;

        }
        if (null == index) {
            return;
        }
        if (null != index.previous && null != index.next) {
            index.previous.next = index.next;
            index.next.previous = index.previous;
        } else {
            //移除头
            if (null == index.previous) {
                index.next.previous = null;
                this.first = index.next;
            } else {
                //移除尾
                index.previous.next = null;
                this.last = index.previous;
            }

        }
        size--;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (null == first) {
            return "";
        }
        Node node = this.first;
        while (null != node) {
            stringBuffer.append(node.value + "->");
            node = node.next;
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        MyLinkList list = new MyLinkList();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        list.addLast("4");
        list.addFirst("0");
        list.addPrevious("0", "-1");
        list.addNext("4", "5");
        System.out.println(list.toString());
        System.out.println("链表的长度是" + list.length());
        list.remove("4");
        System.out.println(list.toString());
        System.out.println("链表的长度是" + list.length());
    }

}
