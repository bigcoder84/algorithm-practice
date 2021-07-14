package cn.bigcoder.algorithm.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/permutations/submissions/
 * @author: Jindong.Tian
 * @date: 2021-07-13
 **/
public class _3Permutations {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));

    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, nums, 0);
        return result;
    }

    public static void dfs(List<List<Integer>> result, int[] nums, int k) {
        if (k >= nums.length) {
            result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = k; i < nums.length; i++) {
            swap(nums, k, i);
            dfs(result, nums, k + 1);
            swap(nums, k, i);
        }
    }

    public static void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }
}
