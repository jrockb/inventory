package co.com.jcd.inventory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.jcd.inventory.dao.ICategoryDao;
import co.com.jcd.inventory.model.Category;
import co.com.jcd.inventory.request.CategoriaRequest;
import co.com.jcd.inventory.response.CategoryResponseRest;
import co.com.jcd.inventory.service.ICategoryService;
import co.com.jcd.inventory.util.Constants;
import co.com.jcd.inventory.util.InventoryUtils;
import co.com.jcd.inventory.util.ValidatorConstraintUtil;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	private ICategoryDao categoryDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {
		CategoryResponseRest response = new CategoryResponseRest();		
		try {
			List<Category> category = (List<Category>) categoryDao.findAll();
			response.getCategroyResponse().setCategory(category);
			response.setMetadata(Constants.OK.getValor(),
					Constants.COD_OK.getValor(), InventoryUtils.generateDate());
		} catch(DataAccessException ex) {
			response.setMetadata(ex.getMessage(),Constants.COD_ERROR.getValor() 
					, InventoryUtils.generateDate());
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> listCat = new ArrayList<>();
		try {
			Optional<Category> catFindId = categoryDao.findById(id);
			if(catFindId.isPresent()) {
				listCat.add(catFindId.get());
				response.getCategroyResponse().setCategory(listCat);
				response.setMetadata(Constants.OK.getValor(),
						Constants.COD_OK.getValor(), InventoryUtils.generateDate());
			} else {
				response.setMetadata("Categoria no encontrada",Constants.COD_ERROR.getValor() 
						, InventoryUtils.generateDate());
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch(DataAccessException ex) {
			response.setMetadata(ex.getMessage(),Constants.COD_ERROR.getValor() 
					, InventoryUtils.generateDate());
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> createCategory(CategoriaRequest req) {
		CategoryResponseRest response = new CategoryResponseRest();
		ValidatorConstraintUtil.validRequest(req);
		List<Category> listCat = new ArrayList<>();
		try {
			Category catGuardar = new Category();
			catGuardar.setName(req.getName());
			catGuardar.setDescription(req.getDescription());
			Category catGuardada = categoryDao.save(catGuardar);
			listCat.add(catGuardada);
			response.getCategroyResponse().setCategory(listCat);
			response.setMetadata(Constants.OK.getValor(),
					Constants.COD_OK.getValor(), InventoryUtils.generateDate());
		} catch(DataAccessException ex) {
			response.setMetadata(ex.getMessage(),Constants.COD_ERROR.getValor() 
					, InventoryUtils.generateDate());
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> updateCategory(CategoriaRequest req, Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		ValidatorConstraintUtil.validRequest(req);
		List<Category> listCat = new ArrayList<>();
		try {
			Optional<Category> categorySearch = categoryDao.findById(id);
			if(categorySearch.isPresent()) {
				categorySearch.get().setName(req.getName());
				categorySearch.get().setDescription(req.getDescription());
				Category categoryToUpdate = categoryDao.save(categorySearch.get());
				if(categoryToUpdate != null) {
					listCat.add(categoryToUpdate);
					response.getCategroyResponse().setCategory(listCat);
					response.setMetadata(Constants.OK.getValor(),
							Constants.COD_OK.getValor(), InventoryUtils.generateDate());
				} else {
					response.setMetadata("Categoria no actualizada",Constants.COD_ERROR.getValor() 
							, InventoryUtils.generateDate());
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				response.setMetadata("Categoria no encontrada",Constants.COD_ERROR.getValor() 
						, InventoryUtils.generateDate());
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch(DataAccessException ex) {
			response.setMetadata(ex.getMessage(),Constants.COD_ERROR.getValor() 
					, InventoryUtils.generateDate());
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}	

}
