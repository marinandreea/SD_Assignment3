package com.example.Assignment_A3.service.observer;

import com.example.Assignment_A3.stateDesignPattern.Package;
import org.springframework.security.core.parameters.P;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ObserverWrite implements IObserverWrite{

    // id = 1 => cashier issues an invoice
    // id = 2 =? admin activates a promotion
    @Override
    public void writeToFile(int id, String s, int orderId, FileWriter file) throws IOException {


        if(id == 1){

            StringBuilder stringBuilder = new StringBuilder();


            Package pkg = new Package();

            stringBuilder.append("\n1. ").append(pkg.printStatus()).append("\n");

            pkg.nextState();
            stringBuilder.append("2. ").append(pkg.printStatus()).append("\n");

            pkg.nextState();
            stringBuilder.append("3. ").append(pkg.printStatus()).append("\n");

            pkg.nextState();
            pkg.printStatus();

            file.write(s);
            file.write(stringBuilder.toString());
            file.close();
        }

    }


}
