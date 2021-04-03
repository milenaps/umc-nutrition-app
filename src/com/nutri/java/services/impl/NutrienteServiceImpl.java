package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.INutrienteDAO;
import com.nutri.java.model.Nutriente;
import com.nutri.java.services.INutrienteService;

/**
 * Implementa a interface de serviços da entidade Nutriente
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see INutrienteService
 */
public class NutrienteServiceImpl implements INutrienteService {

	private INutrienteDAO nutrienteDAO;

	public void setNutrienteDAO(INutrienteDAO nutrienteDAO) {
		this.nutrienteDAO = nutrienteDAO;
	}

	public void delete(Nutriente entity) {
		this.nutrienteDAO.delete(entity);
	}

	public Nutriente get(Serializable id) {
		return this.nutrienteDAO.get(id);
	}

	public List<Nutriente> listAll() {
		return this.nutrienteDAO.listAll();
	}

	public List<Nutriente> listAll(int first, int max) {
		return this.nutrienteDAO.listAll();
	}

	public Nutriente save(Nutriente entity) {
		return this.nutrienteDAO.save(entity);
	}

	public void update(Nutriente entity) {
		this.nutrienteDAO.update(entity);
	}

	public Nutriente findById(int id) {
		return this.nutrienteDAO.findById(id);
	}

	public List<Nutriente> findByName(String name) {
		return this.nutrienteDAO.findByName(name);
	}

	// public List<Nutriente> findByExample(Nutriente entity) {
	// return this.nutrienteDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(Nutriente entity) {
	// return this.nutrienteDAO.findByExamplePageCount(entity);
	// }

	// public Nutriente findOneByExample(Nutriente entity) {
	// return this.nutrienteDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.nutrienteDAO.listAllPageCount();
	// }

	// public List<Nutriente> listByExample(Nutriente entity, int first,
	// int max) {
	// return this.nutrienteDAO.listByExample(entity, first, max);
	// }

	// public Nutriente load(Serializable id) {
	// return this.nutrienteDAO.load(id);
	// }

	// public void rebind(Nutriente entity) {
	// this.nutrienteDAO.rebind(entity);
	// }

	// public List<Nutriente> loadAll() {
	// return this.nutrienteDAO.loadAll();
	// }

}
