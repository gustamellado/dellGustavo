package com.residencia.dell.controllers;

import com.residencia.dell.entities.Products;
import com.residencia.dell.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/{id}")
    public ResponseEntity<Products> findById(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(productsService.findById(id),headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Products>> findAll() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(productsService.findAll(),headers,HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return productsService.count();
    }


    @PostMapping("/save")
    public ResponseEntity<Products> save(@Valid @RequestBody Products products){
        //return produtoService.save(produto);
        HttpHeaders headers = new HttpHeaders();

        if(null != productsService.save(products))
            return new ResponseEntity<Products>(productsService.save(products), headers, HttpStatus.OK);
        else
            return new ResponseEntity<Products>(productsService.save(products), headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public Products update(@Valid @RequestBody Products products){

        return productsService.update(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Products> delete(@RequestParam Integer id){
        HttpHeaders headers = new HttpHeaders();
        boolean isRemoved = productsService.delete(id);
        if (isRemoved){
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);

        }

    }
}

