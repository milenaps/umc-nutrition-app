package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.nutri.java.dao.IPacienteDAO;
import com.nutri.java.model.HistoricoAlimentar;
import com.nutri.java.model.Paciente;
import com.nutri.java.services.IPacienteService;

/**
 * Implementa a interface de serviços da entidade Paciente
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IPacienteService
 */
public class PacienteServiceImpl implements IPacienteService {

	private IPacienteDAO pacienteDAO;

	public void setPacienteDAO(IPacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public void delete(Paciente entity) {
		this.pacienteDAO.delete(entity);
	}

	public Paciente get(Serializable id) {
		return this.pacienteDAO.get(id);
	}

	public List<Paciente> listAll() {
		return this.pacienteDAO.listAll();
	}

	public List<Paciente> listAll(int first, int max) {
		return this.pacienteDAO.listAll();
	}

	public Paciente save(Paciente entity) {
		return this.pacienteDAO.save(entity);
	}

	public void update(Paciente entity) {
		this.pacienteDAO.update(entity);
	}

	public Paciente findById(int id) {
		return this.pacienteDAO.findById(id);
	}

	public List<Paciente> findByName(String name) {
		return this.pacienteDAO.findByName(name);
	}

	public List<HistoricoAlimentar> listHistoricosAlimentares(int paciente) {
		return this.pacienteDAO.listHistoricosAlimentares(paciente);
	}
	
	public List<HistoricoAlimentar> listHistoricosByPeriod(int paciente,
			Date dataDe, Date dataAte) {
		return this.pacienteDAO.listHistoricosByPeriod(paciente, dataDe, dataAte);
	}

	public Paciente findByUserId(int usuario) {
		return this.pacienteDAO.findByUserId(usuario);
	}

	// public List<Paciente> findByExample(Paciente entity) {
	// return this.pacienteDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(Paciente entity) {
	// return this.pacienteDAO.findByExamplePageCount(entity);
	// }

	// public Paciente findOneByExample(Paciente entity) {
	// return this.pacienteDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.pacienteDAO.listAllPageCount();
	// }

	// public List<Paciente> listByExample(Paciente entity, int first,
	// int max) {
	// return this.pacienteDAO.listByExample(entity, first, max);
	// }

	// public Paciente load(Serializable id) {
	// return this.pacienteDAO.load(id);
	// }

	// public void rebind(Paciente entity) {
	// this.pacienteDAO.rebind(entity);
	// }

	// public List<Paciente> loadAll() {
	// return this.pacienteDAO.loadAll();
	// }

}
