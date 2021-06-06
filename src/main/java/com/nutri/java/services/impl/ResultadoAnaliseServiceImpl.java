package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.IResultadoAnaliseDAO;
import com.nutri.java.model.ResultadoAnalise;
import com.nutri.java.services.IResultadoAnaliseService;

/**
 * Implementa a interface de serviços da entidade ResultadoAnalise
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IResultadoAnaliseService
 */
public class ResultadoAnaliseServiceImpl implements IResultadoAnaliseService {

	private IResultadoAnaliseDAO resultadoAnaliseDAO;

	public void setResultadoAnaliseDAO(IResultadoAnaliseDAO resultadoAnaliseDAO) {
		this.resultadoAnaliseDAO = resultadoAnaliseDAO;
	}

	public void delete(ResultadoAnalise entity) {
		this.resultadoAnaliseDAO.delete(entity);
	}

	public ResultadoAnalise get(Serializable id) {
		return this.resultadoAnaliseDAO.get(id);
	}

	public List<ResultadoAnalise> listAll() {
		return this.resultadoAnaliseDAO.listAll();
	}

	public List<ResultadoAnalise> listAll(int first, int max) {
		return this.resultadoAnaliseDAO.listAll();
	}

	public ResultadoAnalise save(ResultadoAnalise entity) {
		return this.resultadoAnaliseDAO.save(entity);
	}

	public void update(ResultadoAnalise entity) {
		this.resultadoAnaliseDAO.update(entity);
	}

	public ResultadoAnalise findById(int id) {
		return this.resultadoAnaliseDAO.findById(id);
	}

	public List<ResultadoAnalise> findByName(String name) {
		return this.resultadoAnaliseDAO.findByName(name);
	}
	
	public List<ResultadoAnalise> listByPaciente(int paciente) {
		return this.resultadoAnaliseDAO.listByPaciente(paciente);
	}

	// public List<ResultadoAnalise> findByExample(ResultadoAnalise entity) {
	// return this.resultadoAnaliseDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(ResultadoAnalise entity) {
	// return this.resultadoAnaliseDAO.findByExamplePageCount(entity);
	// }

	// public ResultadoAnalise findOneByExample(ResultadoAnalise entity) {
	// return this.resultadoAnaliseDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.resultadoAnaliseDAO.listAllPageCount();
	// }

	// public List<ResultadoAnalise> listByExample(ResultadoAnalise entity, int
	// first,
	// int max) {
	// return this.resultadoAnaliseDAO.listByExample(entity, first, max);
	// }

	// public ResultadoAnalise load(Serializable id) {
	// return this.resultadoAnaliseDAO.load(id);
	// }

	// public void rebind(ResultadoAnalise entity) {
	// this.resultadoAnaliseDAO.rebind(entity);
	// }

	// public List<ResultadoAnalise> loadAll() {
	// return this.resultadoAnaliseDAO.loadAll();
	// }

}
