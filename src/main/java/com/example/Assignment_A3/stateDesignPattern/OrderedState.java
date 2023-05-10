package com.example.Assignment_A3.stateDesignPattern;

public class OrderedState implements PackageState{
    @Override
    public void prev(Package pkg) {

        System.out.println("The package is in its root state");
    }

    @Override
    public void next(Package pkg) {
        pkg.setState(new DeliveredState());
    }

    @Override
    public String printStatus() {
        return "Package ordered, not delivered to the office yet";
    }
}
