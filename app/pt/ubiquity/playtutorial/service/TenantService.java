package pt.ubiquity.playtutorial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pt.ubiquity.playtutorial.dao.TenantDao;
import pt.ubiquity.playtutorial.exeception.NotFoundException;
import pt.ubiquity.playtutorial.model.Tenant;

@org.springframework.stereotype.Service
public class TenantService {
	
	@Autowired(required=true)
	private TenantDao tenentDao;
	
	public Tenant add(Tenant tenent){
		return tenentDao.add(tenent);
	}
	
	@Transactional(readOnly=true)
	public Tenant getById(String id) throws NotFoundException{
		return tenentDao.getById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Tenant> getAll(){
		return tenentDao.getAll();
	}
	
	@Transactional(readOnly=false)
	public void delete(String id) throws NotFoundException{
		tenentDao.delete(id);
	}
	
	@Transactional(readOnly=false)
	public void update(Tenant tenent) throws NotFoundException{
		tenentDao.update(tenent);
	}
} 
