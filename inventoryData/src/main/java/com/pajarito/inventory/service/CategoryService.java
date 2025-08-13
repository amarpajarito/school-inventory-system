package com.pajarito.inventory.service;
import com.pajarito.inventory.model.Category;
public interface CategoryService {
	Category[] getAll() throws Exception;
	Category get(Integer id) throws Exception;
	Category create(Category category) throws Exception;
	Category update(Category category) throws Exception;
	void delete(Integer id) throws Exception;
}
