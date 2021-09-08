package cubes.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Sticker;

@Component
public class StickerDAOImpl implements StickerDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Sticker> getStickerList() {
		
		Session session = sessionFactory.getCurrentSession();
		List<Sticker> list = session.createQuery("from Sticker", Sticker.class).getResultList();
		
		return list;
	}

	@Transactional
	@Override
	public void saveSticker(Sticker sticker) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(sticker);
		
	}

	@Transactional
	@Override
	public Sticker getStickerById(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Sticker s = session.get(Sticker.class, id);
		
		return s;
	}

	@Transactional
	@Override
	public void deleteSticker(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Sticker s = session.get(Sticker.class, id);
		session.delete(s);
		
	}

}
