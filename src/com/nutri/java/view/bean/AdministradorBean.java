package com.nutri.java.view.bean;

import java.util.List;

import javax.faces.event.ActionEvent;

import com.nutri.java.model.Administrador;
import com.nutri.java.services.IAdministradorService;

/**
 * ManageBean que encapsula as propriedades da entidade Administrador na camada
 * view
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class AdministradorBean {

	private Administrador administrador;
	private static IAdministradorService administradorService;

	public Administrador getAdministrador() {
		if (this.administrador.getId() == null) {
			if (UsuarioBean.usuarioLogado != null && UsuarioBean.usuarioLogado.getId() != null) {
				this.administrador = this.findByUserId();
				if (this.administrador == null) {
					this.administrador = new Administrador();
				}
			}
		}
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public IAdministradorService getAdministradorService() {
		return administradorService;
	}

	@SuppressWarnings("static-access")
	public void setAdministradorService(
			IAdministradorService administradorService) {
		this.administradorService = administradorService;
	}

	public String save() {
		if (administrador.getId() == null) {
			administradorService.save(this.administrador);
		} else {
			administradorService.update(this.administrador);
		}
		this.administrador = new Administrador();
		return null;
	}

	public String delete() {
		administradorService.delete(this.administrador);
		return null;
	}

	public List<Administrador> getAll() {
		return administradorService.listAll();
	}

	public Administrador findById() {
//		return this.administradorService.findById(Integer.parseInt(String
//				.valueOf(this.administradorId)));
		return null;
	}

	public List<Administrador> findByName() {
//		return this.administradorService.findByName(String
//				.valueOf(this.administradorName));
		return null;
	}
	
	private Administrador findByUserId() {
		return administradorService.findByUserId(UsuarioBean.usuarioLogado.getId());
	}
	
	public void aprovarNutricionista(ActionEvent event) {
    	String current = event.getComponent().getAttributes().get("idNut").toString();
    	administradorService.aprovarNutricionista(Integer.parseInt(current));
	}
}
