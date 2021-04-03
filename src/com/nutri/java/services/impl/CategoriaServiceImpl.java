package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.ICategoriaDAO;
import com.nutri.java.model.Categoria;
import com.nutri.java.services.ICategoriaService;

/**
 * Implementa a interface de serviços da entidade Categoria
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see ICategoriaService
 */
public class CategoriaServiceImpl implements ICategoriaService {

	private ICategoriaDAO categoriaDAO;

	public void setCategoriaDAO(ICategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	public void delete(Categoria entity) {
		this.categoriaDAO.delete(entity);
	}

	public Categoria get(Serializable id) {
		return this.categoriaDAO.get(id);
	}

	public List<Categoria> listAll() {
		return this.categoriaDAO.listAll();
	}

	public List<Categoria> listAll(int first, int max) {
		return this.categoriaDAO.listAll();
	}

	public Categoria save(Categoria entity) {
		return this.categoriaDAO.save(entity);
	}

	public void update(Categoria entity) {
		this.categoriaDAO.update(entity);
	}

	public Categoria findById(int id) {
		return this.categoriaDAO.findById(id);
	}

	public List<Categoria> findByName(String name) {
		return this.categoriaDAO.findByName(name);
	}

	public List<Categoria> listPorGrupo(int grupo) {
		return this.categoriaDAO.listPorGrupo(grupo);
	}

	// public List<Categoria> findByExample(Categoria entity) {
	// return this.categoriaDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(Categoria entity) {
	// return this.categoriaDAO.findByExamplePageCount(entity);
	// }

	// public Categoria findOneByExample(Categoria entity) {
	// return this.categoriaDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.categoriaDAO.listAllPageCount();
	// }

	// public List<Categoria> listByExample(Categoria entity, int first,
	// int max) {
	// return this.categoriaDAO.listByExample(entity, first, max);
	// }

	// public Categoria load(Serializable id) {
	// return this.categoriaDAO.load(id);
	// }

	// public void rebind(Categoria entity) {
	// this.categoriaDAO.rebind(entity);
	// }

	// public List<Categoria> loadAll() {
	// return this.categoriaDAO.loadAll();
	// }

}
