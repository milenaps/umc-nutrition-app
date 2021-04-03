package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.IHistoricoAlimentarDAO;
import com.nutri.java.model.HistoricoAlimentar;
import com.nutri.java.services.IHistoricoAlimentarService;

/**
 * Implementa a interface de serviços da entidade HistoricoAlimentar
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IHistoricoAlimentarService
 */
public class HistoricoAlimentarServiceImpl implements
		IHistoricoAlimentarService {

	private IHistoricoAlimentarDAO historicoAlimentarDAO;

	public void setHistoricoAlimentarDAO(
			IHistoricoAlimentarDAO historicoAlimentarDAO) {
		this.historicoAlimentarDAO = historicoAlimentarDAO;
	}

	public void delete(HistoricoAlimentar entity) {
		this.historicoAlimentarDAO.delete(entity);
	}

	public HistoricoAlimentar get(Serializable id) {
		return this.historicoAlimentarDAO.get(id);
	}

	public List<HistoricoAlimentar> listAll() {
		return this.historicoAlimentarDAO.listAll();
	}

	public List<HistoricoAlimentar> listAll(int first, int max) {
		return this.historicoAlimentarDAO.listAll();
	}

	public HistoricoAlimentar save(HistoricoAlimentar entity) {
		return this.historicoAlimentarDAO.save(entity);
	}

	public void update(HistoricoAlimentar entity) {
		this.historicoAlimentarDAO.update(entity);
	}

	public HistoricoAlimentar findById(int id) {
		return this.historicoAlimentarDAO.findById(id);
	}

	public List<HistoricoAlimentar> findByName(String name) {
		return this.historicoAlimentarDAO.findByName(name);
	}

	// public List<HistoricoAlimentar> findByExample(HistoricoAlimentar entity)
	// {
	// return this.historicoAlimentarDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(HistoricoAlimentar entity) {
	// return this.historicoAlimentarDAO.findByExamplePageCount(entity);
	// }

	// public HistoricoAlimentar findOneByExample(HistoricoAlimentar entity) {
	// return this.historicoAlimentarDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.historicoAlimentarDAO.listAllPageCount();
	// }

	// public List<HistoricoAlimentar> listByExample(HistoricoAlimentar entity,
	// int first,
	// int max) {
	// return this.historicoAlimentarDAO.listByExample(entity, first, max);
	// }

	// public HistoricoAlimentar load(Serializable id) {
	// return this.historicoAlimentarDAO.load(id);
	// }

	// public void rebind(HistoricoAlimentar entity) {
	// this.historicoAlimentarDAO.rebind(entity);
	// }

	// public List<HistoricoAlimentar> loadAll() {
	// return this.historicoAlimentarDAO.loadAll();
	// }

}
