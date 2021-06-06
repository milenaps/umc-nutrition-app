package com.nutri.java.dao.impl;

import java.util.List;

import com.nutri.java.commons.impl.BaseDAOImpl;
import com.nutri.java.dao.IHistoricoAlimentarDAO;
import com.nutri.java.model.HistoricoAlimentar;

/**
 * Implementa a interface de persistencia da entidade HistoricoAlimentar
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IHistoricoAlimentarDAO
 */
public class HistoricoAlimentarDAOImpl extends BaseDAOImpl<HistoricoAlimentar>
		implements IHistoricoAlimentarDAO {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -8858298763551601519L;

	@Override
	public HistoricoAlimentar findById(int id) {
		return null;
	}

	@Override
	public List<HistoricoAlimentar> findByName(String name) {
		return null;
	}

	@Override
	public void delete(HistoricoAlimentar entity) {
	}

	@Override
	public List<HistoricoAlimentar> listAll() {
		return null;
	}

	@Override
	public HistoricoAlimentar save(HistoricoAlimentar entity) {
		return null;
	}

	@Override
	public void update(HistoricoAlimentar entity) {
	}
}
