package cn.bigcoder.algorithm.dfs;

import java.util.*;

/**
 * @author: Jindong.Tian
 * @date: 2021-06-21
 **/
public class _1ArrangementOfString {

    public static void main(String[] args) {
        String[] results = permutation("abc");
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static String[] permutation(String s) {
        char[] chars = s.toCharArray();
        List<String> result = new ArrayList<>();
        dp(chars, result, 0);
        return result.toArray(new String[result.size()]);
    }

    public static void dp(char[] arr, List<String> result, int x) {
        if (arr.length == x) {
            result.add(new String(arr));
            return;
        }
        Set<Character> cache = new HashSet<>();
        for (int i = x; i < arr.length; i++) {
            if (cache.contains(arr[i])) {
                continue;//已经交换过的字符不再进行交换，避免重复
            }
            cache.add(arr[i]);
            swap(arr, x, i);
            dp(arr, result, x + 1);
            swap(arr, x, i);
        }
    }

    private static void swap(char[] arr, int lIndex, int rIndex) {
        char tmp = arr[lIndex];
        arr[lIndex] = arr[rIndex];
        arr[rIndex] = tmp;
    }
}
