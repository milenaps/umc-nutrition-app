package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.IGrupoAlimentarDAO;
import com.nutri.java.model.GrupoAlimentar;
import com.nutri.java.services.IGrupoAlimentarService;

/**
 * Implementa a interface de serviços da entidade GrupoAlimentar
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IGrupoAlimentarService
 */
public class GrupoAlimentarServiceImpl implements IGrupoAlimentarService {

	private IGrupoAlimentarDAO grupoAlimentarDAO;

	public void setGrupoAlimentarDAO(IGrupoAlimentarDAO grupoAlimentarDAO) {
		this.grupoAlimentarDAO = grupoAlimentarDAO;
	}

	public void delete(GrupoAlimentar entity) {
		this.grupoAlimentarDAO.delete(entity);
	}

	public GrupoAlimentar get(Serializable id) {
		return this.grupoAlimentarDAO.get(id);
	}

	public List<GrupoAlimentar> listAll() {
		return this.grupoAlimentarDAO.listAll();
	}

	public List<GrupoAlimentar> listAll(int first, int max) {
		return this.grupoAlimentarDAO.listAll();
	}

	public GrupoAlimentar save(GrupoAlimentar entity) {
		return this.grupoAlimentarDAO.save(entity);
	}

	public void update(GrupoAlimentar entity) {
		this.grupoAlimentarDAO.update(entity);
	}

	public GrupoAlimentar findById(int id) {
		return this.grupoAlimentarDAO.findById(id);
	}

	public List<GrupoAlimentar> findByName(String name) {
		return this.grupoAlimentarDAO.findByName(name);
	}

	// public List<GrupoAlimentar> findByExample(GrupoAlimentar entity) {
	// return this.grupoAlimentarDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(GrupoAlimentar entity) {
	// return this.grupoAlimentarDAO.findByExamplePageCount(entity);
	// }

	// public GrupoAlimentar findOneByExample(GrupoAlimentar entity) {
	// return this.grupoAlimentarDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.grupoAlimentarDAO.listAllPageCount();
	// }

	// public List<GrupoAlimentar> listByExample(GrupoAlimentar entity, int
	// first,
	// int max) {
	// return this.grupoAlimentarDAO.listByExample(entity, first, max);
	// }

	// public GrupoAlimentar load(Serializable id) {
	// return this.grupoAlimentarDAO.load(id);
	// }

	// public void rebind(GrupoAlimentar entity) {
	// this.grupoAlimentarDAO.rebind(entity);
	// }

	// public List<GrupoAlimentar> loadAll() {
	// return this.grupoAlimentarDAO.loadAll();
	// }

}
