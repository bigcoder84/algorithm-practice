package cn.bigcoder.algorithm.dp;

import cn.bigcoder.algorithm.util.ArrayUtils;

import java.util.Arrays;

/**
 * 最长括号
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 *
 * @author: Jindong.Tian
 * @date: 2021-07-07
 **/
public class _4LongestBracket {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("))))((()(("));
    }

    public static int longestValidParentheses(String s) {
        int length = s.length();
        int maxLength = 0;
        char[] chars = s.toCharArray();
        byte[][] map = new byte[length][length];

        for (int i = 1; i < length; i = i + 2) {
            for (int j = 0; j + i < length; j++) {
                int start = j;
                int end = start + i;
                //长度为2
                if (i == 1) {
                    if (chars[start] == '(' && chars[end] == ')') {
                        map[start][end] = 1;
                        maxLength = end - start + 1;
                    }
                }else{
                    if (chars[start] == '(' && chars[end] == ')' && map[start+1][end-1] == 1) {
                        map[start][end] = 1;
                        maxLength = end - start + 1;
                    }
                }
            }
        }
        ArrayUtils.print(map);
        return maxLength;
    }

}
