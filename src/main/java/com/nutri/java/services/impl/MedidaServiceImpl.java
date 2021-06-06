package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.IMedidaDAO;
import com.nutri.java.model.Medida;
import com.nutri.java.services.IMedidaService;

/**
 * Implementa a interface de serviços da entidade Medida
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IMedidaService
 */
public class MedidaServiceImpl implements IMedidaService {

	private IMedidaDAO medidaDAO;

	public void setMedidaDAO(IMedidaDAO medidaDAO) {
		this.medidaDAO = medidaDAO;
	}

	public void delete(Medida entity) {
		this.medidaDAO.delete(entity);
	}

	public Medida get(Serializable id) {
		return this.medidaDAO.get(id);
	}

	public List<Medida> listAll() {
		return this.medidaDAO.listAll();
	}

	public List<Medida> listAll(int first, int max) {
		return this.medidaDAO.listAll();
	}

	public Medida save(Medida entity) {
		return this.medidaDAO.save(entity);
	}

	public void update(Medida entity) {
		this.medidaDAO.update(entity);
	}

	public Medida findById(int id) {
		return this.medidaDAO.findById(id);
	}

	public List<Medida> findByName(String name) {
		return this.medidaDAO.findByName(name);
	}
	// public List<Medida> findByExample(Medida entity) {
	// return this.medidaDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(Medida entity) {
	// return this.medidaDAO.findByExamplePageCount(entity);
	// }

	// public Medida findOneByExample(Medida entity) {
	// return this.medidaDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.medidaDAO.listAllPageCount();
	// }

	// public List<Medida> listByExample(Medida entity, int first,
	// int max) {
	// return this.medidaDAO.listByExample(entity, first, max);
	// }

	// public Medida load(Serializable id) {
	// return this.medidaDAO.load(id);
	// }

	// public void rebind(Medida entity) {
	// this.medidaDAO.rebind(entity);
	// }

	// public List<Medida> loadAll() {
	// return this.medidaDAO.loadAll();
	// }

}
