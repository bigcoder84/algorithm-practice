package cn.bigcoder.algorithm.dp;

/**
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 *
 * @author: Jindong.Tian
 * @date: 2021-07-13
 **/
public class _5BinaryTreeMaximumPathSum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int MAX_SUM = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return MAX_SUM;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int rightMaxSum = Math.max(dfs(node.right), 0);
        int leftMaxSum = Math.max(dfs(node.left), 0);
        int maxSum = node.val + leftMaxSum + rightMaxSum;
        if (maxSum > MAX_SUM) {
            MAX_SUM = maxSum;
        }
        return node.val + Math.max(rightMaxSum, leftMaxSum);
    }
}