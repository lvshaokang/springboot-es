package com.lsk.es.example01.util;

import java.util.Arrays;

/**
 * TODO
 *
 * @author lsk
 * @class_name SortApp
 * @date 2020-07-22
 */
public class SortApp {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 6, 5, 4};

        quickSort(nums,0,5);


        System.out.println(Arrays.toString(nums));
    }

    public static void quickSort(int[] nums, int l, int r) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int p = serach(nums, l, r);

        quickSort(nums, l, p-1);
        quickSort(nums, p+1,r);
    }

    private static int serach(int[] nums, int l, int r) {
        // 指定基准值
        int p = nums[r];

        int i = l, j = r;

        while (i < j) {
            while (i < j && p <= nums[j]) {
                j--;
            }

            while (i < j && p >= nums[i]) {
                i++;
            }

            if (i < j) {
                swap(nums, i, j);
            }
        }

        swap(nums, i, j);

        return r;
    }

   /* private static void quickSort(int[] a, int head, int tail) {

        int low = head;
        int high = tail;


        int pivot = a[low];
        if (low < high) {

            while (low<high) {
                while (low < high && pivot <= a[high]) high--;
                a[low] = a[high];
                while (low < high && pivot >= a[low]) low++;
                a[high]=a[low];
            }
            a[low] = pivot;

            if(low>head+1) quickSort(a,head,low-1);
            if(high<tail-1) quickSort(a,high+1,tail);
        }

    }*/

//    public static void swap(int[] arr, int i, int j) {
//        if (i != j) {
//            int temp = arr[i];
//            arr[i] = arr[j];
//            arr[j] = temp;
//        }
//    }

    private static void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }



}
