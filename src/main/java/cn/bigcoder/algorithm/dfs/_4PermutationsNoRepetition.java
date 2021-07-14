package cn.bigcoder.algorithm.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/permutations-ii/
 *
 * @author: Jindong.Tian
 * @date: 2021-07-13
 **/
public class _4PermutationsNoRepetition {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 1, 2, 2, 3}));

    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(result, nums, new ArrayList<>(nums.length), new boolean[nums.length], 0);
        return result;
    }

    /**
     * @param result  dfs结果
     * @param nums    拿去元素的池子
     * @param cache   缓存已经选中的元素
     * @param visited 已经拿取过的元素
     * @param k
     */
    public static void dfs(List<List<Integer>> result, int[] nums, List<Integer> cache, boolean[] visited, int k) {
        if (k >= nums.length) {
            result.add(new ArrayList<>(cache));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // visited[i]保证一个元素只选取一次；i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]保证如果当前元素与前一个相同的情况下，只有前面的元素被选中了才能选择当前元素
            if (visited[i] || i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            cache.add(k, nums[i]);
            visited[i] = true;
            dfs(result, nums, cache, visited, k + 1);
            cache.remove(k);
            visited[i] = false;
        }
    }
}
