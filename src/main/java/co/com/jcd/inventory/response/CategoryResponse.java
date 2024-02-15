package co.com.jcd.inventory.response;

import java.util.List;

import co.com.jcd.inventory.model.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryResponse {
	
	private List<Category> category;

}
