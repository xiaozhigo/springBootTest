package com.example.springboottest.test;

import com.example.springboottest.annotation.TestAnnotation;
import org.junit.Test;

public class Test1 {
    public static void main(String[] args) {
        int i = 2000;
        Integer j = 2000;
        Integer k = new Integer(2000);
        System.out.println(i==j);
        System.out.println(i==k);
        System.out.println(j==k);
    }

    @Test
    public void test1(){
        String str = "呼呼呵呵";
        int length = str.length();
        System.out.println(length);
    }

    /**
     * 多重for循环跳出测试
     */
    @Test
    public void test2(){
        int i =100;
        int j = 100;
        here:
        for(int i1 = 0;i1 < i;i1++){
            for(int j1 = 0;j1 < j;j1++){
                if(j1 == 10 && i1 == 10){
                    break here;
                }
                System.out.println("i1:"+i1+"----------------"+"j1:"+j1);
            }
        }
    }

    @Test
    public void test3(){
         String str1 = "哈哈";
         String str2 = "哈"+"哈";
         StringBuilder builder = new StringBuilder();
         String str3 = builder.append("哈哈").toString();
         String str4 = "123456";
         StringBuilder stringBuilder = new StringBuilder(str4);
         System.out.println(str1==str2);
         System.out.println(str1==str2.intern());
         System.out.println(str1==str3);
         System.out.println(str1==str3.intern());
         System.out.println(stringBuilder.reverse());
    }

    @Test
    public void test4(){
       String str = "123456";
        String reverse = reverse(str);
        System.out.println(str.substring(1));
        System.out.println(str.charAt(0));
        System.out.println(reverse);
    }

    public static String reverse(String originStr) {
        if(originStr == null || originStr.length() <= 1)
            return originStr;
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

    @Test
    public void test5(){
        String st = "";
        String str = "123456";
        String[] s = str.split("");
        for(int i = s.length-1;i >= 0;i--){
            String s1 = s[i];
            st+=s1;
        }
        System.out.println(st);
    }
}
