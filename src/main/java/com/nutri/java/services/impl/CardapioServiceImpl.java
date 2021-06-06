package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.ICardapioDAO;
import com.nutri.java.model.Cardapio;
import com.nutri.java.services.ICardapioService;

public class CardapioServiceImpl implements ICardapioService {

	private ICardapioDAO cardapioDAO;
	
	public ICardapioDAO getCardapioDAO() {
		return cardapioDAO;
	}

	public void setCardapioDAO(ICardapioDAO cardapioDAO) {
		this.cardapioDAO = cardapioDAO;
	}

	@Override
	public void delete(Cardapio entity) {
		this.cardapioDAO.delete(entity);
	}

	@Override
	public Cardapio findById(int id) {
		return this.cardapioDAO.findById(id);
	}

	@Override
	public List<Cardapio> findByName(String name) {
		return this.cardapioDAO.findByName(name);
	}

	@Override
	public Cardapio get(Serializable id) {
		return this.cardapioDAO.get(id);
	}

	@Override
	public List<Cardapio> listAll() {
		return this.cardapioDAO.listAll();
	}

	@Override
	public List<Cardapio> listAll(int first, int max) {
		return this.cardapioDAO.listAll(first, max);
	}

	@Override
	public Cardapio save(Cardapio entity) {
		return this.cardapioDAO.save(entity);
	}

	@Override
	public void update(Cardapio entity) {
		this.cardapioDAO.update(entity);
	}

	public Cardapio findByNutricionista(int nutricionista) {
		return this.cardapioDAO.findByNutricionista(nutricionista);
	}
	
	public Cardapio findByPaciente(int paciente) {
		return this.cardapioDAO.findByPaciente(paciente);
	}
}
