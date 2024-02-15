package co.com.jcd.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.jcd.inventory.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long>{

}
