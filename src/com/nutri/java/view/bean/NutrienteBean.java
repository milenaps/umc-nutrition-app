package com.nutri.java.view.bean;

import java.util.List;

import javax.faces.event.ActionEvent;

import com.nutri.java.model.Nutriente;
import com.nutri.java.services.INutrienteService;

/**
 * ManageBean que encapsula as propriedades da entidade Nutriente na camada view
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class NutrienteBean {

	private Nutriente nutriente;
	private static INutrienteService nutrienteService;

	public Nutriente getNutriente() {
		return nutriente;
	}

	public void setNutriente(Nutriente nutriente) {
		this.nutriente = nutriente;
	}

	public INutrienteService getNutrienteService() {
		return nutrienteService;
	}

	@SuppressWarnings("static-access")
	public void setNutrienteService(INutrienteService nutrienteService) {
		this.nutrienteService = nutrienteService;
	}

	public void save(ActionEvent e) {
		if (nutriente.getId() == null) {
			nutrienteService.save(this.nutriente);
		} else {
			nutrienteService.update(this.nutriente);
		}
		this.nutriente = new Nutriente();
	}
	
	public void update(ActionEvent e) {
		this.nutriente = nutrienteService.findById(Integer.parseInt(e.getComponent().getAttributes().get("idNutriente").toString()));
	}

	public void delete(ActionEvent e) {
		this.nutriente = nutrienteService.findById(Integer.parseInt(e.getComponent().getAttributes().get("idNutriente").toString()));
		nutrienteService.delete(this.nutriente);
		this.nutriente = new Nutriente();
	}

	public List<Nutriente> getAll() {
		return nutrienteService.listAll();
	}
}
