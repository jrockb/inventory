package co.com.jcd.inventory.service;

import org.springframework.http.ResponseEntity;

import co.com.jcd.inventory.request.CategoriaRequest;
import co.com.jcd.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	public ResponseEntity<CategoryResponseRest> createCategory(CategoriaRequest req);
	public ResponseEntity<CategoryResponseRest> updateCategory(CategoriaRequest req, Long id);
	public ResponseEntity<CategoryResponseRest> deleteCategory(Long id);


}
