package cubes.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.dao.CategoryDAO;
import cubes.main.entity.Category;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Transactional
	@Override
	public List<Category> getCategoryList() {
		//KOD KOJI NE TREBA DA BUDE NI U KONTROLERU ALI TAKODJE NI U DAO PAKETIMA I KLASAMA
		return categoryDAO.getCategoryList();
	}

	@Transactional
	@Override
	public void saveCategory(Category category) {
		categoryDAO.saveCategory(category);
	}

	@Transactional
	@Override
	public Category getCategoryById(int id) {
		return categoryDAO.getCategoryById(id);
	}

	@Transactional
	@Override
	public void deleteCategory(int id) {
		categoryDAO.deleteCategory(id);
	}

	@Transactional
	@Override
	public List<Category> getCategoryListForMainPage() {
		
		return categoryDAO.getCategoryListForMainPage();
	}

}
