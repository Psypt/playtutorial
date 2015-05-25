package pt.ubiquity.playtutorial.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pt.ubiquity.playtutorial.exeception.NotFoundException;
import pt.ubiquity.playtutorial.model.Tenant;


@org.springframework.stereotype.Repository
public class TenantDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional(readOnly = false)
	public Tenant add(Tenant tenent) {
		if (tenent.getUuid() == null) {
			tenent.setUuid(UUID.randomUUID().toString());
		}
		getCurrentSession().save(tenent);
		return tenent;
	}

	@Transactional(readOnly = false)
	public Tenant update(Tenant tenent) throws NotFoundException {
		pt.ubiquity.playtutorial.model.Tenant aux = getById(tenent.getUuid());
		aux.setName(tenent.getName());
		aux.setBirthdate(tenent.getBirthdate());
		aux.setAddress(tenent.getAddress());
		getCurrentSession().update(aux);
		return tenent;
	}

	@Transactional(readOnly = true)
	public Tenant getById(String id) throws NotFoundException {
		Criteria criteria = getCurrentSession().createCriteria(Tenant.class);
		criteria.add(Restrictions.eq("uuid", id));
		Tenant result = (Tenant) criteria.uniqueResult();
		if (result == null) {
			throw new NotFoundException("Id doesn't exist.");
		} else {
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Tenant> getAll() {
		return getCurrentSession().createCriteria(Tenant.class).list();
	}

	@Transactional(readOnly = false)
	public void delete(String id) throws NotFoundException {
		getCurrentSession().delete(getById(id));
	}

	public static byte[] getSha512(String value) {
		try {
			return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
