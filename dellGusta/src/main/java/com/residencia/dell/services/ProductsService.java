package com.residencia.dell.services;

import com.residencia.dell.entities.Products;
import com.residencia.dell.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    @Autowired
    public ProductsRepository productsRepository;

    public Products findById(Integer id){
        return productsRepository.findById(id).get();
    }

    public List<Products> findAll(){
        return productsRepository.findAll();
    }

    public Long count(){
        return productsRepository.count();
    }

    public Products save(Products product){
        Products newProduct = productsRepository.save(product);
        return newProduct;
    }

    public Products update(Products product){
        return productsRepository.save(product);
    }

    public boolean delete (Integer id) {
        if (id != null) {
            productsRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
