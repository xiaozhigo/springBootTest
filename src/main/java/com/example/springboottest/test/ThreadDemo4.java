package com.example.springboottest.test;

public class ThreadDemo4 {
    public static void main(String[] args) {


        class MyThread extends Thread{

            public MyThread(String name){
                super(name);
            }

            @Override
            public void run() {
                try {
                    int i=0;
                    while(!isInterrupted()){
                        Thread.sleep(100);
                        i++;
                        System.out.println(Thread.currentThread().getName()+ " ("+this.getState()+") loop "+i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()+ " ("+this.getState()+") catch InterruptedExecption");
                }

            }
        }


        try {

            //新建
            Thread t1 = new MyThread("t1");
            System.out.println(t1.getName()+" ("+t1.getState()+" ) is new.");

            System.out.println("luo1:"+t1.isInterrupted());
            //启动
            t1.start();
            System.out.println(t1.getName()+" ("+t1.getState()+" ) is started.");
            System.out.println("luo2:"+t1.isInterrupted());
            //主线程休眠300ms，然后主线程给t1发“中断”指令
            Thread.sleep(300);
            t1.interrupt();
            System.out.println("luo3:"+t1.isInterrupted());
            System.out.println(t1.getName()+" ("+t1.getState()+" ) is interrupted.");

            //主线程休眠300ms，然后查看t1的状态
            Thread.sleep(300);
            System.out.println(t1.getName()+" ("+t1.getState()+" ) is interrupted now .");


        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
