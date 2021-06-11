package com.residencia.dell.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.dell.entities.Categories;
import com.residencia.dell.repositories.CategoriesRepository;


@Service
public class CategoriesService {
	
	@Autowired
	public CategoriesRepository categoriesRepository;
	
	public Categories findById(Integer id){
		return categoriesRepository.findById(id).get() ;
	}
	
	public List<Categories> findAll(){
		return categoriesRepository.findAll();
	}
	
	public Long Count() {
		return categoriesRepository.count();
	}
	
	public Categories save(Categories categories){
		Categories novaCategories= categoriesRepository.save(categories);
		return  novaCategories;
	}
	
	public Categories update (Categories categories) {
		return categoriesRepository.save(categories);
	}
	
	public boolean delete(Integer id){
		  if(id != null) {
			  categoriesRepository.deleteById(id);
			  return true;
		  }
		  else {
			  return false;
		  }
	}
}

