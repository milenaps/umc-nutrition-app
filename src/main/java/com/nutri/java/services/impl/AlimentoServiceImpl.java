package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.IAlimentoDAO;
import com.nutri.java.model.Alimento;
import com.nutri.java.services.IAlimentoService;

/**
 * Implementa a interface de serviços da entidade Alimento
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IAlimentoService
 */
public class AlimentoServiceImpl implements IAlimentoService {

	private IAlimentoDAO alimentoDAO;

	public void setAlimentoDAO(IAlimentoDAO alimentoDAO) {
		this.alimentoDAO = alimentoDAO;
	}

	public void delete(Alimento entity) {
		this.alimentoDAO.delete(entity);
	}

	public Alimento get(Serializable id) {
		return this.alimentoDAO.get(id);
	}

	public List<Alimento> listAll() {
		return this.alimentoDAO.listAll();
	}

	public List<Alimento> listAll(int first, int max) {
		return this.alimentoDAO.listAll();
	}

	public void update(Alimento entity) {
		this.alimentoDAO.update(entity);
	}

	public Alimento save(Alimento entity) {
		return this.alimentoDAO.save(entity);
	}

	public Alimento findById(int id) {
		return this.alimentoDAO.findById(id);
	}

	public List<Alimento> findByName(String name) {
		return this.alimentoDAO.findByName(name);
	}

	public List<Alimento> listPorCateg(int categ) {
		return this.alimentoDAO.listPorCateg(categ);
	}

	// public List<Alimento> findByExample(Alimento entity) {
	// return this.alimentoDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(Alimento entity) {
	// return this.alimentoDAO.findByExamplePageCount(entity);
	// }

	// public Alimento findOneByExample(Alimento entity) {
	// return this.alimentoDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.alimentoDAO.listAllPageCount();
	// }

	// public List<Alimento> listByExample(Alimento entity, int first,
	// int max) {
	// return this.alimentoDAO.listByExample(entity, first, max);
	// }

	// public Alimento load(Serializable id) {
	// return this.alimentoDAO.load(id);
	// }

	// public void rebind(Alimento entity) {
	// this.alimentoDAO.rebind(entity);
	// }

	// public List<Alimento> loadAll() {
	// return this.alimentoDAO.loadAll();
	// }

}
