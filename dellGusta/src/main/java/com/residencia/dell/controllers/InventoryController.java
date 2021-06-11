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

import com.residencia.dell.entities.Inventory;
import com.residencia.dell.services.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
    private InventoryService inventoryService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Inventory> findById(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(inventoryService.findById(id), 
				headers, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Inventory>> findAll() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(inventoryService.findAll(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public Long count() {
		return inventoryService.Count();
	}
	
	@PostMapping("/inventory")
	public ResponseEntity<Inventory> save(@RequestBody Inventory inventory){
		HttpHeaders headers = new HttpHeaders();
		
		if(null != inventoryService.save(inventory))
			return new ResponseEntity<Inventory>(inventoryService.save(inventory), headers, HttpStatus.OK);
		else
			return new ResponseEntity<Inventory>(inventoryService.save(inventory), headers, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/update")
    public Inventory update(@RequestBody Inventory inventory){
       return inventoryService.update(inventory);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Inventory> delete(@PathVariable Integer id) {
		try {
			inventoryService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}