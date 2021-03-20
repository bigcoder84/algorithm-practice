package cn.bigcoder.algorithm.dp;

import org.junit.Test;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 * <p>
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author: Jindong.Tian
 * @date: 2021-03-20
 **/
public class _2LongestPalindromicSubstring {
    @Test
    public void test() {
        System.out.println(longestPalindrome("babad"));
    }

    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLength = 1;
        int maxI = 0;
        for (int len = 0; len < s.length(); len++) {
            for (int i = 0; i + len < s.length(); i++) {
                if (len == 0) {
                    dp[i][i + len] = true;
                } else if (len == 1) {
                    if (s.charAt(i) == s.charAt(i + 1)) {
                        dp[i][i + len] = true;
                        if (len + 1 > maxLength) {
                            maxLength = len + 1;
                            maxI = i;
                        }
                    } else {
                        dp[i][i + len] = false;
                    }
                } else {
                    if (dp[i + 1][i + len - 1] && s.charAt(i) == s.charAt(i + len)) {
                        dp[i][i + len] = true;
                        if (len + 1 > maxLength) {
                            maxLength = len + 1;
                            maxI = i;
                        }
                    }
                }
            }
        }
        return s.substring(maxI, maxI + maxLength);
    }

}
