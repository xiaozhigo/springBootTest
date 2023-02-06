package com.example.springboottest.adapter;

public class AdapterCompose implements Target{

    private Adaptee adaptee;

    public AdapterCompose(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void t1() {
        adaptee.a1();
    }

    @Override
    public void t2() {
        adaptee.a2();
    }

    @Override
    public void t3() {
        adaptee.a3();
    }
}
