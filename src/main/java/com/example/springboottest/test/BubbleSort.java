package com.example.springboottest.test;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
       int tmp;
       int[] nums = {5,7,9,1,4,3};
       for(int i = 0;i < nums.length-1;i++){
           for(int j = nums.length - 1;j > i;j--){
               if(nums[j-1] > nums[j]){
                   tmp = nums[j - 1];
                   nums[j-1] = nums[j];
                   nums[j] = tmp;
               }
           }
       }
       for(int i = 0;i < nums.length;i++){
           System.out.println(nums[i]);
       }
    }
}
