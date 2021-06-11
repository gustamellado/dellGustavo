package com.residencia.dell.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.dell.entities.Customers;
import com.residencia.dell.repositories.CustomersRepository;


@Service
public class CustomersService {
	
	@Autowired
	public CustomersRepository customersRepository;
	
	public Customers findById(Integer id){
		return customersRepository.findById(id).get() ;
	}
	
	public List<Customers> findAll(){
		return customersRepository.findAll();
	}
	
	public Long Count() {
		return customersRepository.count();
	}
	
	public Customers save(Customers customers){
		Customers novaCustomers= customersRepository.save(customers);
		return  novaCustomers;
	}
	
	public Customers update (Customers customers) {
		return customersRepository.save(customers);
	}
	
	public boolean delete(Integer id){
		  if(id != null) {
			  customersRepository.deleteById(id);
			  return true;
		  }
		  else {
			  return false;
		  }
	    }
}
