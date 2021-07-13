package cn.bigcoder.algorithm.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * @author: Jindong.Tian
 * @date: 2021-07-06
 **/
public class _2BracketsGenerate {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(4).stream().sorted(String::compareTo).collect(Collectors.toList()));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dp("", n, n, result);
        return result;
    }

    public static void dp(String str, int ln, int rn, List<String> result) {
        if (ln == 0 && rn == 0) {
            result.add(str);
            return;
        }
        if (ln > 0) {
            dp(str + "(", ln - 1, rn, result);
        }
        //如果左括号消费个数和右括号一样，则只能使用左
        if (ln < rn) {
            dp(str + ")", ln, rn - 1, result);
        }
    }
}
