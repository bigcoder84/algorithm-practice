package cn.bigcoder.algorithm.alibaba;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Jindong.Tian
 * @date: 2021-07-13
 **/
public class Cache {
    private int capacity;
    private Map<String, Node> innerCache;
    private Node head;
    private Node tail;
    private int size = 0;

    static class Node {
        String key;
        Integer value;
        Node next;
        Node pre;

        public Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public Cache(int capacity) {
        this.capacity = capacity;
        this.innerCache = new HashMap<>(capacity);
        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public Integer get(String key) {
        Node node = innerCache.get(key);
        if (node == null) {
            return null;
        }
        removeNode(node);
        insertHead(node);
        return node.value;
    }

    public void put(String key, Integer value) {
        Node oldNode = innerCache.get(key);
        if (oldNode != null) {
            removeNode(oldNode);
            insertHead(oldNode);
            oldNode.value = value;
            return;
        }
        if (size >= capacity) {
            //淘汰
            removeNode(this.tail.pre);
            innerCache.remove(this.tail.pre.key);
            return;
        }
        Node node = new Node(key, value);
        innerCache.put(key, node);
        insertHead(node);
        size++;
    }

    private void insertHead(Node node) {
        Node next = this.head.next;
        this.head.next = node;
        node.next = next;
        next.pre = node;
        node.pre = this.head;
    }

    private void removeNode(Node node){
        Node pre = node.pre;
        pre.next = node.next;
        node.next.pre = pre;
    }
}
