package com.residencia.dell.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.dell.entities.Inventory;
import com.residencia.dell.repositories.InventoryRepository;


@Service
public class InventoryService {
	
	@Autowired
	public InventoryRepository inventoryRepository;
	
	public Inventory findById(Integer id){
		return inventoryRepository.findById(id).get() ;
	}
	
	public List<Inventory> findAll(){
		return inventoryRepository.findAll();
	}
	
	public Long Count() {
		return inventoryRepository.count();
	}
	
	public Inventory save(Inventory inventory){
		Inventory novaInventory= inventoryRepository.save(inventory);
		return  novaInventory;
	}
	
	public Inventory update (Inventory inventory) {
		return inventoryRepository.save(inventory);
	}
	
	public boolean delete(Integer id){
		  if(id != null) {
			  inventoryRepository.deleteById(id);
			  return true;
		  }
		  else {
			  return false;
		  }
	}
}

