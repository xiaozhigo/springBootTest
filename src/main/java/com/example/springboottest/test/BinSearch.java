package com.example.springboottest.test;

/**
 * 二分查询
 */
public class BinSearch {

    public static void main(String[] args) {
        int key = 1;
        int[] srcArray = {1,2,3,4,5,6,7,8,9,10,11};
        int mid;
        int start = 0;
        int end = srcArray.length - 1;
        while(start <= end){
          mid = (end - start)/2 +start;
          if(key < srcArray[mid]){
              end = mid - 1;
          }else if(key > srcArray[mid]){
              start = mid + 1;
          }else{
              System.out.println("查找到的值为"+(mid+1));
              break;
          }
        }
    }
}
