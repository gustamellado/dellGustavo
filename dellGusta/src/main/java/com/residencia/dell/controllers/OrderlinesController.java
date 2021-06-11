package com.residencia.dell.controllers;



import com.residencia.dell.entities.Orderlines;

import com.residencia.dell.services.OrderlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderlines")
public class OrderlinesController {
    @Autowired
    private OrderlinesService orderlinesService;

    @GetMapping("/{id}")
    public ResponseEntity<Orderlines> findById(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(orderlinesService.findById(id),headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Orderlines>> findAll() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(orderlinesService.findAll(),headers,HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return orderlinesService.count();
    }


    @PostMapping("/save")
    public ResponseEntity<Orderlines> save(@RequestBody Orderlines orderlines){
        HttpHeaders headers = new HttpHeaders();

        if(null != orderlinesService.save(orderlines))
            return new ResponseEntity<Orderlines>(orderlinesService.save(orderlines), headers, HttpStatus.OK);
        else
            return new ResponseEntity<Orderlines>(orderlinesService.save(orderlines), headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public Orderlines update(@RequestBody Orderlines orderlines){

        return orderlinesService.update(orderlines);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Orderlines> delete(@RequestParam Integer id){
        HttpHeaders headers = new HttpHeaders();
        boolean isRemoved = orderlinesService.delete(id);
        if (isRemoved){
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);

        }

    }

}
