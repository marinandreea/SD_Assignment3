package com.example.Assignment_A3.stateDesignPattern;

public class DeliveredState implements PackageState{
    @Override
    public void prev(Package pkg) {

        pkg.setState(new OrderedState());
    }

    @Override
    public void next(Package pkg) {
        pkg.setState(new ReceivedState());
    }

    @Override
    public String printStatus() {
        return "Package delivered to the post office, not received yet";
    }
}
