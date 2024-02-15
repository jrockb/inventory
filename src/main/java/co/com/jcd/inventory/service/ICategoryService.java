package co.com.jcd.inventory.service;

import org.springframework.http.ResponseEntity;

import co.com.jcd.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	

}
