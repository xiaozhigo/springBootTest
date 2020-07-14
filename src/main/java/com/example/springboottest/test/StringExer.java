package com.example.springboottest.test;

public class StringExer {
    String str = new String("good");
    String str1 = "good";
    char[] ch = {'t','e','s','t'};

    public void change(String str,char ch[]) {
        str = "test o";
        str1 = "test1 o";
        ch[0] = 'b';
        System.out.println(str);
    }

    public static void main(String[] args) {
        StringExer stringExer = new StringExer();
        stringExer.change(stringExer.str,stringExer.ch);
        System.out.println(stringExer.str);
        System.out.println(stringExer.str1);
        System.out.println(stringExer.ch);
    }
}
