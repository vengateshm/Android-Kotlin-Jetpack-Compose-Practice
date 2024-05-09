package dev.vengateshm.java_practice.programs;

import java.util.Arrays;

public class WhatIsOutput1 {
    public static void main(String[] args) {
        int[] arr = {5, 2, 7, 1, 8};
        Arrays.sort(arr,1,4);
        System.out.println(Arrays.toString(arr));
        System.out.println(Math.floorDiv(arr[0],arr[2]));
    }
}
