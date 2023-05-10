package com.example.Assignment_A3.stateDesignPattern;

public class ReceivedState implements PackageState {
    @Override
    public void prev(Package pkg) {
        pkg.setState(new DeliveredState());
    }

    @Override
    public void next(Package pkg) {
        System.out.println("This package is already received by the client");
    }

    @Override
    public String printStatus() {
        return "The package was delivered by the client" ;
    }
}
