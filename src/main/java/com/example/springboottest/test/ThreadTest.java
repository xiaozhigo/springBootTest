package com.example.springboottest.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
    public static void main(String[] args) {
        Account account = new Account();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i = 1;i < 10;i++){
            executorService.execute(new AddMoneyThread(account,1));
        }
        executorService.shutdown();
        System.out.println("-----------"+account.getBalance()+"----------");
    }
}

class Account{
    private ReentrantLock lock = new ReentrantLock();
    private volatile Double balance = 0.0;
    public void deposit(Double money){
         lock.lock();
         try{
             Double newBalance = balance + money;
             try{
                 Thread.sleep(100);
             }catch (InterruptedException ex){
                 ex.printStackTrace();
             }
             balance = newBalance;
             System.out.println(balance);
         }finally {
             lock.unlock();
         }
    }

    public double getBalance(){
        return balance;
    }
}

class  AddMoneyThread implements Runnable{

    private Account account;
    private double money;

    AddMoneyThread(Account account, double money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
            account.deposit(money);
    }
}
