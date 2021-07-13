package cn.bigcoder.algorithm.bfs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 *
 * @author: Jindong.Tian
 * @date: 2021-07-13
 **/
public class _1PopulatingNextRightPointersInEachNode {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        List<Node> cache = new LinkedList<>();
        cache.add(root);
        do {
            //连接当前层所有元素
            connectList(cache);
            List<Node> parentList = cache;
            cache = new LinkedList<>();
            //获取下一层元素
            processChildList(parentList, cache);
        } while (cache.size() > 0);
        return root;
    }

    /**
     * 连接列表中的所有节点
     * @param nodes
     */
    public void connectList(List<Node> nodes) {
        if (nodes == null || nodes.size() <= 1) {
            return;
        }
        int i = 0;
        Node pre = null;
        Iterator<Node> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            if (i == 0) {
                pre = iterator.next();
                i++;
                continue;
            }
            Node next = iterator.next();
            pre.next = next;
            pre = next;

        }
    }

    /**
     * 获取下一层元素
     * @param parentList
     * @param cache
     */
    public void processChildList(List<Node> parentList, List<Node> cache) {
        if (parentList == null || parentList.size() <= 0) {
            return;
        }
        Iterator<Node> iterator = parentList.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            holdNodeChild(node, cache);
        }
    }

    public void holdNodeChild(Node node, List<Node> cache) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            cache.add(node.left);
        }
        if (node.right != null) {
            cache.add(node.right);
        }
    }
}
