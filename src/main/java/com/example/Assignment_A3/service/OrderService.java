package com.example.Assignment_A3.service;

import com.example.Assignment_A3.model.Cart;
import com.example.Assignment_A3.model.Client;
import com.example.Assignment_A3.model.Order;
import com.example.Assignment_A3.model.User;
import com.example.Assignment_A3.model.dto.OrderDTO;
import com.example.Assignment_A3.repository.CartRepository;
import com.example.Assignment_A3.repository.ClientRepository;
import com.example.Assignment_A3.repository.OrderRepository;
import com.example.Assignment_A3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order toEntity(OrderDTO orderDTO){
        Optional<User> user = userRepository.findById(orderDTO.getIdCashier());
        Optional<Client> client = clientRepository.findById(orderDTO.getIdClient());
        Optional<Cart> cart = cartRepository.findById(orderDTO.getIdCart());

        return Order.builder()
                .user(user.get())
                .client(client.get())
                .cart(cart.get())
                .date(orderDTO.getDate())
                .totalPrice(orderDTO.getTotalPrice())
                .status(orderDTO.getStatus())
                .build();
    }

    public static OrderDTO fromEntity(Order order){
        return OrderDTO.builder()
                .idOrder(order.getIdOrder())
                .idCashier(order.getUser().getIdUser())
                .idClient(order.getClient().getIdClient())
                .idCart(order.getCart().getIdCart())
                .date(order.getDate())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }

    public boolean checkPresence(int idOrder){

        boolean exists = false;

        List<Order> orders = (List<Order>) orderRepository.findAll();
        for(Order o:orders){
            if(o.getIdOrder() == idOrder){
                exists = true;
            }
        }
        return exists;

    }

    public List<OrderDTO> getAllOrders(){
        List<Order> a = (List<Order>) orderRepository.findAll();
        return a.stream().map(OrderService::fromEntity).collect(Collectors.toList());
    }

    public List<Order> getAllOrdersss(){
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(o-> {

            orders.add(o);

        });
        return orders;
    }

    public String getOrderById(int id){
        var o = orderRepository.findById(id);
        if(o.isPresent()) {
            List<Order> orders = (List<Order>) orderRepository.findAll();
            for (Order oo : orders) {
                if (fromEntity(oo).getIdOrder() == id) {
                    return fromEntity(oo).toString();
                }
            }
        }
        return null;

    }

    public String createOrder(OrderDTO orderDTO){

       Order order = toEntity(orderDTO);
       order.setTotalPrice(order.getCart().getPrice());
       orderRepository.save(order);
       order.getClient().setLoyalty(order.getClient().getLoyalty()+1);
       clientRepository.save(order.getClient());
       return "Order was created successfully!";
    }

    public String deleteOrder(int id){
        if(checkPresence(id)){
        orderRepository.deleteById(id);
        return "Order was deleted successfully!";
        }else{
            return "Order does not exist!";
        }
    }




}
