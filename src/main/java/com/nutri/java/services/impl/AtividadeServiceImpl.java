package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.IAtividadeDAO;
import com.nutri.java.model.Atividade;
import com.nutri.java.services.IAtividadeService;

/**
 * Implementa a interface de serviços da entidade Atividade
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IAtividadeService
 */
public class AtividadeServiceImpl implements IAtividadeService {

	private IAtividadeDAO atividadeDAO;

	public void setAtividadeDAO(IAtividadeDAO atividadeDAO) {
		this.atividadeDAO = atividadeDAO;
	}

	public void delete(Atividade entity) {
		this.atividadeDAO.delete(entity);
	}

	public Atividade get(Serializable id) {
		return this.atividadeDAO.get(id);
	}

	public List<Atividade> listAll() {
		return this.atividadeDAO.listAll();
	}

	public List<Atividade> listAll(int first, int max) {
		return this.atividadeDAO.listAll();
	}

	public Atividade save(Atividade entity) {
		return this.atividadeDAO.save(entity);
	}

	public void update(Atividade entity) {
		this.atividadeDAO.update(entity);
	}

	public Atividade findById(int id) {
		return this.atividadeDAO.findById(id);
	}

	public List<Atividade> findByName(String name) {
		return this.atividadeDAO.findByName(name);
	}

	// public List<Atividade> findByExample(Atividade entity) {
	// return this.atividadeDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(Atividade entity) {
	// return this.atividadeDAO.findByExamplePageCount(entity);
	// }

	// public Atividade findOneByExample(Atividade entity) {
	// return this.atividadeDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.atividadeDAO.listAllPageCount();
	// }

	// public List<Atividade> listByExample(Atividade entity, int first,
	// int max) {
	// return this.atividadeDAO.listByExample(entity, first, max);
	// }

	// public Atividade load(Serializable id) {
	// return this.atividadeDAO.load(id);
	// }

	// public void rebind(Atividade entity) {
	// this.atividadeDAO.rebind(entity);
	// }

	// public List<Atividade> loadAll() {
	// return this.atividadeDAO.loadAll();
	// }

}
