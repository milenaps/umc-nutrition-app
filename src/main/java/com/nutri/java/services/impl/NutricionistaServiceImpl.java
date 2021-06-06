package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.INutricionistaDAO;
import com.nutri.java.model.Nutricionista;
import com.nutri.java.services.INutricionistaService;

/**
 * Implementa a interface de serviços da entidade Nutricionista
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see INutricionistaService
 */
public class NutricionistaServiceImpl implements INutricionistaService {

	private INutricionistaDAO nutricionistaDAO;

	public void setNutricionistaDAO(INutricionistaDAO nutricionistaDAO) {
		this.nutricionistaDAO = nutricionistaDAO;
	}

	public void delete(Nutricionista entity) {
		this.nutricionistaDAO.delete(entity);
	}

	public Nutricionista get(Serializable id) {
		return this.nutricionistaDAO.get(id);
	}

	public List<Nutricionista> listAll() {
		return this.nutricionistaDAO.listAll();
	}

	public List<Nutricionista> listAll(int first, int max) {
		return this.nutricionistaDAO.listAll();
	}

	public Nutricionista save(Nutricionista entity) {
		return this.nutricionistaDAO.save(entity);
	}

	public void update(Nutricionista entity) {
		this.nutricionistaDAO.update(entity);
	}
	
	public Nutricionista findById(int id) {
		return this.nutricionistaDAO.findById(id);
	}
	
	public List<Nutricionista> findByName(String name) {
		return this.nutricionistaDAO.findByName(name);
	}
	
	public Nutricionista findByUserId(int usuario) {
		return this.nutricionistaDAO.findByUserId(usuario);
	}
	
	public List<Nutricionista> listForApproval() {
	    return this.nutricionistaDAO.listForApproval();
	}

	// public List<Nutricionista> findByExample(Nutricionista entity) {
	// return this.nutricionistaDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(Nutricionista entity) {
	// return this.nutricionistaDAO.findByExamplePageCount(entity);
	// }

	// public Nutricionista findOneByExample(Nutricionista entity) {
	// return this.nutricionistaDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.nutricionistaDAO.listAllPageCount();
	// }

	// public List<Nutricionista> listByExample(Nutricionista entity, int first,
	// int max) {
	// return this.nutricionistaDAO.listByExample(entity, first, max);
	// }

	// public Nutricionista load(Serializable id) {
	// return this.nutricionistaDAO.load(id);
	// }

	// public void rebind(Nutricionista entity) {
	// this.nutricionistaDAO.rebind(entity);
	// }

	// public List<Nutricionista> loadAll() {
	// return this.nutricionistaDAO.loadAll();
	// }

}
