package cn.bigcoder.algorithm.util;

import java.util.Arrays;

/**
 * @author: Jindong.Tian
 * @date: 2021-07-07
 **/
public class ArrayUtils {
    public static void print(byte[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}
