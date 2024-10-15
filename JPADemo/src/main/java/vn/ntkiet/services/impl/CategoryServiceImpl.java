package vn.ntkiet.services.impl;

import java.util.List;

import vn.ntkiet.dao.impl.CategoryDaoImpl;
import vn.ntkiet.entity.Category;
import vn.ntkiet.services.ICategoryService;
import vn.ntkiet.dao.ICategoryDao;

public class CategoryServiceImpl implements ICategoryService{

	public ICategoryDao cateDao = new CategoryDaoImpl(); 
	@Override
	public void insert(Category category) {
		cateDao.insert(category);
		
	}

	@Override
	public void update(Category category) {
		cateDao.update(category);
		
	}

	@Override
	public void delete(int categoryId) throws Exception {
		cateDao.delete(categoryId);
	}

	@Override
	public Category findById(int categoryId) {
		return cateDao.findById(categoryId);
	}

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public List<Category> findByCategoryName(String categoryName) {
		return cateDao.findByCategoryName(categoryName);
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return cateDao.count() ;
	}
	
}
