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

import com.residencia.dell.entities.Reorder;
import com.residencia.dell.services.ReorderService;

@RestController
@RequestMapping("/reorder")
public class ReorderController {

	@Autowired
    private ReorderService reorderService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Reorder> findById(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(reorderService.findById(id), 
				headers, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Reorder>> findAll() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(reorderService.findAll(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public Long count() {
		return reorderService.Count();
	}
	
	@PostMapping("/reorder")
	public ResponseEntity<Reorder> save(@RequestBody Reorder reorder){
		HttpHeaders headers = new HttpHeaders();
		
		if(null != reorderService.save(reorder))
			return new ResponseEntity<Reorder>(reorderService.save(reorder), headers, HttpStatus.OK);
		else
			return new ResponseEntity<Reorder>(reorderService.save(reorder), headers, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/")
    public Reorder update(@RequestBody Reorder reorder){
       return reorderService.update(reorder);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Reorder> delete(@PathVariable Integer id) {
		try {
			reorderService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}