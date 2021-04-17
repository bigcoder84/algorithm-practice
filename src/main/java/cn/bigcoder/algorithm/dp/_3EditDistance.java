package cn.bigcoder.algorithm.dp;

/**
 * https://leetcode-cn.com/problems/edit-distance/
 *
 * @author: Jindong.Tian
 * @date: 2021-04-17
 **/
public class _3EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("", "a"));
    }

    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            throw new RuntimeException("参数不能为空");
        }
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        int cost;
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost);
            }
        }
        return dp[word1.length()][word2.length()];
    }

    private static int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }
}
