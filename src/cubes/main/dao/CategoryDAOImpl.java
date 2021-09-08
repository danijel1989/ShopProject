package cubes.main.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Category> getCategoryList() {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Category> categories = session.createQuery("from Category", Category.class).getResultList();
		
		return categories;
	}

	
	@Override
	public void saveCategory(Category category) {
		
	Session session = sessionFactory.getCurrentSession();
	
	session.saveOrUpdate(category);
		
		
	}

	
	@Override
	public Category getCategoryById(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Category cat = session.get(Category.class, id);
		
		return cat;
	}

	
	@Override
	public void deleteCategory(int id) {
		
		//PRVI NACIN - get podatak pa onda metoda za brisanje
		Session session = sessionFactory.getCurrentSession();
//		Category cat = session.get(Category.class, id);
//		session.delete(cat);
		
		//DRUGI NACIN - pravimo objekat Query i upit unutar njega pa setParameter i executeUpdate()
		Query query = session.createQuery("delete from Category where id=:id");
		query.setParameter("id", id); //Naziv "id" mora da se poklapa sa nazivom promenljive u upitu nakon dve tacke - linija iznad
		query.executeUpdate();
		
		
	}

   
	@Override
	public List<Category> getCategoryListForMainPage() {

    	Session session = sessionFactory.getCurrentSession();
    	
    	List<Category> list = session.createQuery("from Category c where c.homepage = 1", Category.class).getResultList();
		
		
		return list;
	}


	
	
		
	

}
