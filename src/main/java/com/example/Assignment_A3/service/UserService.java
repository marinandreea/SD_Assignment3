package com.example.Assignment_A3.service;

import com.example.Assignment_A3.model.Role;
import com.example.Assignment_A3.model.User;
import com.example.Assignment_A3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public boolean checkPresence(int idUser){

        boolean exists = false;

        List<User> users = (List<User>) userRepository.findAll();
        for(User u:users){
            if(u.getIdUser() == idUser){
                exists = true;
            }
        }
        return exists;

    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(u-> {
            if(u.getRole().equals(Role.CASHIER)){
                users.add(u);
            }
        });
        return users;
    }

    public Optional<User> getUserById(int id){
        var u = userRepository.findById(id);
        if(u.isPresent()){
            return userRepository.findById(id);
        }else{
            return null;
        }

    }

    public String addUser(User user){
        if(!checkPresence(user.getIdUser())){
            String encP = user.encryptedPassword(user.getPassword());
            user.setPassword(encP);
            user.setRoles(Role.CASHIER);
            userRepository.save(user);
            return "A new user with id "+user.getIdUser()+" was created!";

        }else{
            return "A user with id " + user.getIdUser()+" was already created";
        }

    }

    public String updateUser(User user){

        var u = userRepository.findById(user.getIdUser());
        if(u.isPresent()){
            userRepository.save(user);
            return "User with id " + user.getIdUser() +" was updated successfully!";
        }else{
            return "User not found!";
        }
    }

    public String deleteUser(int id){
        var u = userRepository.findById(id);
        if(u.isPresent()){
            userRepository.deleteById(id);
            return "User with id " + id + " was successfully deleted!";
        }else{
            return "User not found!";
        }
    }
}
