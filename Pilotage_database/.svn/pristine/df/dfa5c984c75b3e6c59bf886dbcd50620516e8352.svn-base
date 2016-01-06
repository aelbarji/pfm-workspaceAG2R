package pilotage.database.images;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Images;
import pilotage.admin.session.ParametreSession;

public class ImagesDatabaseService {
	public static Integer save(String nom, String type, File image) throws Exception{
		Session session = ParametreSession.getCurrentSession();
		
		try{
			InputStream stream = new FileInputStream(image);
			int length = (int)image.length();
			byte[] imageByteArray = new byte[length];
			stream.read(imageByteArray, 0, length);
			stream.close();
			
			Images im = new Images();
			im.setNom(nom);
			im.setType(type);
			im.setImage(imageByteArray);
			
			session.save(im);
			session.getTransaction().commit();
			
			return im.getId();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static Images get(Integer id){
		Session session = ParametreSession.getCurrentSession();
		Images image = (Images) session.createCriteria(Images.class)
									.add(Restrictions.eq("id", id))
									.uniqueResult();
		if(image != null)
			image.getImage();
		session.getTransaction().commit();
		return image;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Images> getAll(){
		Session session = ParametreSession.getCurrentSession();
		List<Images> imagesList = session.createCriteria(Images.class)
									.addOrder(Order.asc("nom"))
									.list();

		session.getTransaction().commit();
		return imagesList;
	}
}
