package com.example.Assignment_A3;

import com.example.Assignment_A3.model.Order;
import com.example.Assignment_A3.repository.OrderRepository;
import com.example.Assignment_A3.service.observer.Subject;
import org.apache.commons.csv.CSVFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CSVFileGenerator extends Subject {

    @Autowired
    private OrderRepository orderRepository;

    protected CSVFileGenerator() throws IOException {
    }

    public void issueAnInvoice(List<Order> orders, int idOrder, Writer writer){
        try{
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            int ok=0;
            for(Order o:orders){
                if(o.getIdOrder() == idOrder){
                    ok=1;
                    o.setStatus("delivered");
                    orderRepository.save(o);
                    notifyObservers(1,o.invoice(),o.getIdOrder());
                    printer.printRecord(o.toString());
                }
            }
            if(ok==0){
                printer.printRecord("No order with id " + idOrder + " was found!");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
