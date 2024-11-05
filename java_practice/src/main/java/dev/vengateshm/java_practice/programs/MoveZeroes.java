package dev.vengateshm.java_practice.programs;

import java.util.Arrays;

public class MoveZeroes {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveZeroes1(new int[]{1, 0, 3, 0, 4, 5, 0})));
        System.out.println(Arrays.toString(moveZeroes2(new int[]{1, 0, 3, 0, 4, 5, 0})));
        System.out.println(Arrays.toString(moveZeroes3(new int[]{1, 0, 3, 0, 4, 5, 0})));
    }

    public static int[] moveZeroes1(int[] arr) {
        int index = 0;
        for (int i : arr) {
            if (i != 0) {
                arr[index++] = i;
            }
        }
        while (index < arr.length) {
            arr[index++] = 0;
        }
        return arr;
    }

    public static int[] moveZeroes2(int[] arr) {
        int[] nums = new int[arr.length];
        int index = 0;
        for (int i : arr) {
            if (i != 0) {
                nums[index++] = i;
            }
        }

        System.arraycopy(nums, 0, arr, 0, arr.length);

        return nums;
    }

    public static int[] moveZeroes3(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] == 0) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }
        }
        return arr;
    }
}
