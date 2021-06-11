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

import com.residencia.dell.entities.Categories;
import com.residencia.dell.services.CategoriesService;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
    private CategoriesService categoriesService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Categories> findById(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(categoriesService.findById(id), 
				headers, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Categories>> findAll() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(categoriesService.findAll(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public Long count() {
		return categoriesService.Count();
	}
	
	@PostMapping("/categories")
	public ResponseEntity<Categories> save(@RequestBody Categories categories){
		HttpHeaders headers = new HttpHeaders();
		
		if(null != categoriesService.save(categories))
			return new ResponseEntity<Categories>(categoriesService.save(categories), headers, HttpStatus.OK);
		else
			return new ResponseEntity<Categories>(categoriesService.save(categories), headers, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/")
    public Categories update(@RequestBody Categories categories){
       return categoriesService.update(categories);
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Categories> delete(@PathVariable Integer id) {
		try {
			categoriesService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}