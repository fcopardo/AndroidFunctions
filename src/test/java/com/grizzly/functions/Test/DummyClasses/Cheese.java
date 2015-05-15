package com.grizzly.functions.Test.DummyClasses;

/**
 * Created by fpardo on 5/15/15.
 */
public class Cheese {

    private int a = 0;
    private String b = "";

    public void printMe(){
        System.out.println("I am "+b+" and Im the number:"+a);
    }

    public String getMe(){
        return b+String.valueOf(a);
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
