package com.nutri.java.view.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.nutri.java.model.GrupoAlimentar;
import com.nutri.java.services.IGrupoAlimentarService;

/**
 * ManageBean que encapsula as propriedades da entidade GrupoAlimentar na camada
 * view
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class GrupoAlimentarBean {

	private GrupoAlimentar grupoAlimentar;
	private static IGrupoAlimentarService grupoAlimentarService;
	
	private List<GrupoAlimentar> grupos;

	public GrupoAlimentar getGrupoAlimentar() {
		return grupoAlimentar;
	}

	public void setGrupoAlimentar(GrupoAlimentar grupoAlimentar) {
		this.grupoAlimentar = grupoAlimentar;
	}

	public IGrupoAlimentarService getGrupoAlimentarService() {
		return grupoAlimentarService;
	}

	@SuppressWarnings("static-access")
	public void setGrupoAlimentarService(
			IGrupoAlimentarService grupoAlimentarService) {
		this.grupoAlimentarService = grupoAlimentarService;
	}

	public void setGrupos(List<GrupoAlimentar> grupos) {
		this.grupos = grupos;
	}

	public List<GrupoAlimentar> getGrupos() {
		return grupos;
	}

	public List<SelectItem> getAllGrupos() {
		List<GrupoAlimentar> allGrupos = grupoAlimentarService.listAll();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (GrupoAlimentar grupo : allGrupos) {
			items.add(new SelectItem(grupo.getId(), grupo.getNome()));
		}
		return items;
	}

	public void save(ActionEvent e) {
		if (grupoAlimentar.getId() == null) {
			grupoAlimentarService.save(this.grupoAlimentar);
		} else {
			grupoAlimentarService.update(this.grupoAlimentar);
		}
		this.grupoAlimentar = new GrupoAlimentar();
	}

	public void update(ActionEvent e) {
		this.grupoAlimentar = grupoAlimentarService.findById(Integer.parseInt(e.getComponent().getAttributes().get("idGrupo").toString()));
	}

	public void delete(ActionEvent e) {
		this.grupoAlimentar = grupoAlimentarService.findById(Integer.parseInt(e.getComponent().getAttributes().get("idGrupo").toString()));
		grupoAlimentarService.delete(this.grupoAlimentar);
		this.grupoAlimentar = new GrupoAlimentar();
	}

	public List<GrupoAlimentar> getAll() {
		if (this.grupos == null) {
			this.grupos = grupoAlimentarService.listAll();
		}
		return this.grupos;
	}

	public static GrupoAlimentar findById(Integer id) {
		return grupoAlimentarService.findById(id);
	}

	public List<GrupoAlimentar> findByName(String name) {
		return grupoAlimentarService.findByName(name);
	}
}
