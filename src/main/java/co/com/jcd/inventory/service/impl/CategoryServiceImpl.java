package co.com.jcd.inventory.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import co.com.jcd.inventory.response.CategoryResponseRest;
import co.com.jcd.inventory.service.ICategoryService;
import co.com.jcd.inventory.util.Constants;

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
					Constants.COD_OK.getValor(), generateDate());
		} catch(DataAccessException ex) {
			response.setMetadata(ex.getMessage(),Constants.COD_ERROR.getValor() 
					, generateDate());
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	private String generateDate() {
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = 
        		DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return currentLocalDateTime.format(dateTimeFormatter);        
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
			} else {
				response.setMetadata("Categoria no encontrada",Constants.COD_ERROR.getValor() 
						, generateDate());
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch(DataAccessException ex) {
			response.setMetadata(ex.getMessage(),Constants.COD_ERROR.getValor() 
					, generateDate());
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
