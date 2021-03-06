package cn.bigcoder.algorithm.dp;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：coins = [1], amount = 2
 * 输出：2
 * <p>
 * 提示：
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 * <p>
 * 零钱对换
 * 题目：https://leetcode-cn.com/problems/coin-change/
 * <p>
 * 题解：https://github.com/labuladong/fucking-algorithm/blob/master/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E7%B3%BB%E5%88%97/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E8%AF%A6%E8%A7%A3%E8%BF%9B%E9%98%B6.md
 *
 * @author: Jindong.Tian
 * @date: 2021-03-04
 **/
public class _1CoinChange {
    //==============================解法一==================================================

    /**
     * 无备忘录递归
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int count = coinChange(coins, amount - coins[i]);
            if (count < 0) {
                continue;
            }
            minCount = Math.min(minCount, count + 1);
        }
        return minCount;
    }

    @Test
    public void test1() {
        int[] coins = {5, 2, 1};
        int amount = 11;
        Assert.assertEquals(3, coinChange(coins, amount));
    }


    //==============================解法二=============================================

    /**
     * 带备忘录递归
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        return dp(coins, dp, amount);
    }

    public int dp(int[] coins, int[] dp, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (dp[amount] != Integer.MAX_VALUE) {
            return dp[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int residue = amount - coins[i];
            int dpCount = dp(coins, dp, residue);
            if (dpCount == -1) {
                continue;
            }
            res = Math.min(res, 1 + dpCount);
        }
        if (res == Integer.MAX_VALUE) {
            //无解
            dp[amount] = -1;
        } else {
            dp[amount] = res;
        }
        return dp[amount];
    }

    @Test
    public void test2() {
        int[] coins = {5, 2, 1};
        int amount = 11;
        Assert.assertEquals(3, coinChange2(coins, amount));
    }


    //=============================解法三=====================================

    /**
     * dp数组形式
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int tempAmount = 1; tempAmount <= amount; tempAmount++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] > tempAmount) {
                    continue;
                }
                if (dp[tempAmount - coins[j]] == Integer.MAX_VALUE) {
                    //此种方案凑不出来
                    continue;
                }
                dp[tempAmount] = Math.min(dp[tempAmount], dp[tempAmount - coins[j]] + 1);
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[amount];
    }

    @Test
    public void test3() {
        int[] coins = {2};
        int amount = 3;
        Assert.assertEquals(-1, coinChange3(coins, amount));
    }
}
