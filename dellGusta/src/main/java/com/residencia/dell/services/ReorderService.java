package com.residencia.dell.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.dell.entities.Reorder;
import com.residencia.dell.repositories.ReorderRepository;


@Service
public class ReorderService {
	
	@Autowired
	public ReorderRepository reorderRepository;
	
	public Reorder findById(Integer id){
		return reorderRepository.findById(id).get() ;
	}
	
	public List<Reorder> findAll(){
		return reorderRepository.findAll();
	}
	
	public Long Count() {
		return reorderRepository.count();
	}
	
	public Reorder save(Reorder reorder){
		Reorder novaReorder= reorderRepository.save(reorder);
		return  novaReorder;
	}
	
	public Reorder update (Reorder reorder) {
		return reorderRepository.save(reorder);
	}
	
	public boolean delete(Integer id){
		  if(id != null) {
			  reorderRepository.deleteById(id);
			  return true;
		  }
		  else {
			  return false;
		  }
	}
}

