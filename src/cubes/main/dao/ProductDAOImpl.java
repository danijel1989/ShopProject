package cubes.main.dao;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Product> getProductList() {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Product> list = session.createQuery("from Product", Product.class).getResultList();
		
		
		return list;
	}
	
	
	@Transactional
	@Override
	public List<Product> getProductListWithStickers() {
		
		// otvaramo sesiju kako bismo uspostavili komunikaciju sa bazom
		Session session = sessionFactory.getCurrentSession();
		
		//pravimo listu proizvoda koju dobijamo putem Query-ja
		List<Product> list = session.createQuery("from Product", Product.class).getResultList();
		
		//uz FOR-EACH petlju prodjemo kroz sve proizvode a u svakoj interakciji
		// pozivamo metodu Hibernate.initialize() kako bismo aktivirali pripremu stikera
		// jer je default-ni fetch type LAZY zbog ManyToMany anotacije
		// ovim postizemo da svaki proizvod bude ucitan i popunjen njegovim stikerima
		
		for(Product p : list) {
			Hibernate.initialize(p.getStickers());
		}
		
		return list;
		
	}
	
	
	@Transactional
	@Override
	public List<Product> getProductListForMainPage() {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Product> list = session.createQuery("from Product p where p.homepage = 1",Product.class).getResultList();
		
		for(Product p : list) {
			Hibernate.initialize(p.getStickers());
		}
		
		return list;
	}

	@Transactional
	@Override
	public void saveProduct(Product product) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(product);
		
		
	}

	@Transactional
	@Override
	public void deleteProduct(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		//DRUGI NACIN - pravimo objekat Query i upit unutar njega pa setParameter i executeUpdate()
				Query query = session.createQuery("delete from Product where id=:id");
				query.setParameter("id", id); //Naziv "id" mora da se poklapa sa nazivom promenljive u upitu - linija iznad
				query.executeUpdate();
		
		
		
	}

	@Transactional
	@Override
	public Product getProductById(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Product p = session.get(Product.class, id);
		
		Hibernate.initialize(p.getStickers());
		
		return p;
	}

	@Transactional
	@Override
	public List<Product> getProductList(Integer category, Integer price, Integer[] stickers) {
		
		Session session = sessionFactory.getCurrentSession();
		
//		if(category == null || price == null) {
//			return getProductList();
//		}
		
		//kreiram query string u zavisnosti od parametara
		String queryString = "";
		
		if(stickers != null) {
			queryString = "select distinct p from Product p left join p.stickers s ";	
		}
		else {
		    queryString = "from Product p ";	
		}
		
		//category
		if(category != null && category>0) {
			queryString = queryString + "where p.category.id = :categoryID";
		}
		
		//price
		if(price != null && price>0) {
			
			if(!queryString.contains("where")) {
				queryString = queryString + " where ";
			}
			
			else {
				queryString = queryString+ " and ";
			}
			
			queryString = queryString + " p.price < :price";
		}
		
     	//stickers
		if(stickers!=null && stickers.length > 0) {
			
			if(!queryString.contains("where")) {
				
				queryString = queryString + "where";
				
			}
			
			else {
				
				queryString = queryString + " and ";
			}
			
			queryString = queryString + " s.id in (:stickers)";
		}
			
		
		System.out.println(queryString);
		
		
		Query query = session.createQuery(queryString);
		
		//dodajem parametre za query
		if(category != null && category>0) {
			query.setParameter("categoryID", category);
		}
		
		if(price != null && price >0) {
			query.setParameter("price", (double)(price*5000));
		}
		
		if(stickers != null && stickers.length>0) {
	
				query.setParameterList("stickers", stickers);
			
		}
		
		List<Product> products = query.getResultList();
		
		for(Product p : products) {
			Hibernate.initialize(p.getStickers());
		}
		
		return products;
	}

	

//	@Transactional
//	@Override
//	public List<Product> getProductList(String text) {
//		
//		Session session = sessionFactory.getCurrentSession();
//		
//		String stringQuery = "from Product p where p.title like :text or p.description like :text";
//		
//	    Query query = session.createQuery(stringQuery);
//	    query.setParameter("text", "%" + text + "%");
//		
//		
//		return query.getResultList();
//	}
	
	@Transactional
	@Override
	public List<Product> getProductList(String text) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Product.class);
		
		Criterion criteriaTitle = Restrictions.like("title", "%"+text+"%");
		Criterion criteriaDescription = Restrictions.like("description", "%"+text+"%");
		
		LogicalExpression orExpression = Restrictions.or(criteriaTitle, criteriaDescription);
		
		criteria.add(orExpression);
		
		List<Product> products = criteria.list();
		
		for(Product p : products) {
			Hibernate.initialize(p.getStickers());
		}
		
		return products;
	}

	
	

	

}
