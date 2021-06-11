package com.residencia.dell.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.dell.entities.Customers;
import com.residencia.dell.services.CustomersService;

@RestController
@RequestMapping("/customers")
public class CustomersController {

	@Autowired
    private CustomersService customersService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Customers> findById(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(customersService.findById(id), 
				headers, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Customers>> findAll() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(customersService.findAll(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public Long count() {
		return customersService.Count();
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customers> save(@RequestBody Customers customers){
		HttpHeaders headers = new HttpHeaders();
		
		if(null != customersService.save(customers))
			return new ResponseEntity<Customers>(customersService.save(customers), headers, HttpStatus.OK);
		else
			return new ResponseEntity<Customers>(customersService.save(customers), headers, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/")
    public Customers update(@RequestBody Customers customers){
       return customersService.update(customers);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Customers> delete(@PathVariable Integer id) {
		try {
			customersService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}