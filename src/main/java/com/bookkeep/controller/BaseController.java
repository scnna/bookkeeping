package com.bookkeep.controller;

import javax.servlet.http.HttpSession;

/**
 * controller
 */

public class BaseController {
    //
    public Object getUserId(HttpSession session) {
        return session.getAttribute("userId");
    }

    public Object getUserName(HttpSession session) {
        return session.getAttribute("userName");
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
//        int a = bisectionMethod(arr, 18);
//        System.out.println(a);
//
//        int[] arr1 = new int[]{13, 213, 23, 12, 32, 45, 345, 123, 34, 23, 4, 123, 345, 345, 3, 213};
//        int[] result = bubbleSort(arr1);
//        System.out.println(result);

        int sum = monkey(1);
        System.out.println(sum);

    }

    /*
     * @author scn
     * @date 2018/3/13 12:39
     * @param [arr, a]
     * @return int
     * @Descripte 二分法查找
    */
    public static int bisectionMethod(int[] arr, int a) {
        int model = arr.length / 2;
        while (a != arr[model]) {

            if (a < arr[model]) {
                model = model / 2;
            } else {
                model = (arr.length - model) / 2 + model;
            }
        }
        return model;
    }

    /**
     * @param
     * @return int[]
     * @author scn
     * @date 2018/3/13 13:10
     * @Descripte 冒泡排序
     */
    public static int[] bubbleSort(int[] arr) {
        int model;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    model = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = model;
                }
            }
        }
        return arr;
    }

    public static int monkey(int day) {
        if (day == 10) {
            return 1;
        }
        return 2 * monkey(day + 1) + 2;
    }
}
