package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.IFrequenciaAtividadeDAO;
import com.nutri.java.model.FrequenciaAtividade;
import com.nutri.java.services.IFrequenciaAtividadeService;

/**
 * Implementa a interface de serviços da entidade FrequenciaAtividade
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IFrequenciaAtividadeService
 */
public class FrequenciaAtividadeServiceImpl implements
		IFrequenciaAtividadeService {

	private IFrequenciaAtividadeDAO frequenciaAtividadeDAO;

	public void setFrequenciaAtividadeDAO(
			IFrequenciaAtividadeDAO frequenciaAtividadeDAO) {
		this.frequenciaAtividadeDAO = frequenciaAtividadeDAO;
	}

	public void delete(FrequenciaAtividade entity) {
		this.frequenciaAtividadeDAO.delete(entity);
	}

	public FrequenciaAtividade get(Serializable id) {
		return this.frequenciaAtividadeDAO.get(id);
	}

	public List<FrequenciaAtividade> listAll() {
		return this.frequenciaAtividadeDAO.listAll();
	}

	public List<FrequenciaAtividade> listAll(int first, int max) {
		return this.frequenciaAtividadeDAO.listAll();
	}

	public FrequenciaAtividade save(FrequenciaAtividade entity) {
		return this.frequenciaAtividadeDAO.save(entity);
	}

	public void update(FrequenciaAtividade entity) {
		this.frequenciaAtividadeDAO.update(entity);
	}

	public FrequenciaAtividade findById(int id) {
		return this.frequenciaAtividadeDAO.findById(id);
	}

	@Override
	public List<FrequenciaAtividade> findByName(String name) {
		return this.frequenciaAtividadeDAO.findByName(name);
	}

	// public List<FrequenciaAtividade> findByExample(FrequenciaAtividade
	// entity) {
	// return this.frequenciaAtividadeDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(FrequenciaAtividade entity) {
	// return this.frequenciaAtividadeDAO.findByExamplePageCount(entity);
	// }

	// public FrequenciaAtividade findOneByExample(FrequenciaAtividade entity) {
	// return this.frequenciaAtividadeDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.frequenciaAtividadeDAO.listAllPageCount();
	// }

	// public List<FrequenciaAtividade> listByExample(FrequenciaAtividade
	// entity, int first,
	// int max) {
	// return this.frequenciaAtividadeDAO.listByExample(entity, first, max);
	// }

	// public FrequenciaAtividade load(Serializable id) {
	// return this.frequenciaAtividadeDAO.load(id);
	// }

	// public void rebind(FrequenciaAtividade entity) {
	// this.frequenciaAtividadeDAO.rebind(entity);
	// }

	// public List<FrequenciaAtividade> loadAll() {
	// return this.frequenciaAtividadeDAO.loadAll();
	// }

}
