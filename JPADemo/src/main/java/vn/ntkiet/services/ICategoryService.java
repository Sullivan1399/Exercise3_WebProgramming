package vn.ntkiet.services;

import java.util.List;

import vn.ntkiet.entity.Category;

public interface ICategoryService {
	void insert(Category category);
	void update(Category category);
	void delete(int categoryId) throws Exception;
	Category findById(int categoryId);
	List<Category> findAll();
	List<Category> findByCategoryName(String categoryName);
	List<Category> findAll(int page, int pagesize);
	int count();
}
