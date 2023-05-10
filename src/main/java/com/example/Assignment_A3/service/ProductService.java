package com.example.Assignment_A3.service;

import com.example.Assignment_A3.model.Product;
import com.example.Assignment_A3.model.Role;
import com.example.Assignment_A3.model.User;
import com.example.Assignment_A3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean checkPresence(int idProduct){

        boolean exists = false;

        List<Product> products = (List<Product>) productRepository.findAll();
        for(Product p:products){
            if(p.getIdProduct() == idProduct){
                exists = true;
            }
        }
        return exists;

    }

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(p-> products.add(p));
        return products;
    }

    public Optional<Product> getProductById(int id){
        var p = productRepository.findById(id);
        if(p.isPresent()){
            return productRepository.findById(id);
        }else{
            return null;
        }

    }

    public String addProduct(Product product){
        if(!checkPresence(product.getIdProduct())){
            if(product.getStockAvailable() >=1){
                productRepository.save(product);
                return "A new product with id "+product.getIdProduct()+" was created!";
            }else{
                return "Cannot add the product because the stock is below 1!";
            }

        }else{
            return "A product with id " + product.getIdProduct()+" was already created";
        }

    }

    public String updateProduct(Product product){

        var p = productRepository.findById(product.getIdProduct());
        if(p.isPresent()){
            productRepository.save(product);
            return "Product with id " + product.getIdProduct() +" was updated successfully!";
        }else{
            return "Product not found!";
        }
    }

    public String deleteProduct(int id){
        var p = productRepository.findById(id);
        if(p.isPresent()){
            productRepository.deleteById(id);
            return "Product with id " + id + " was successfully deleted!";
        }else{
            return "Product not found!";
        }
    }



}
