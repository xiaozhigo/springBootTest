package com.example.springboottest.test;

public class AutoCloseableTest {

    public static void main(String[] args) {
        try(AutoCloseableDemo demo = new AutoCloseableDemo()){
            System.out.println("_________try内部________");
            throw new RuntimeException("抛出异常");
        } catch (Exception e) {
            System.out.println("____________异常信息___________");
            e.printStackTrace();
        }

    }


    static class AutoCloseableDemo implements AutoCloseable{

        @Override
        public void close() throws Exception {
            System.out.println("~~~~~~~~~关闭资源~~~~~~~~~");
        }
    }
}
