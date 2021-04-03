package com.nutri.java.view.bean;

import java.util.List;

import com.nutri.java.model.Nutricionista;
import com.nutri.java.services.INutricionistaService;

/**
 * ManageBean que encapsula as propriedades da entidade Nutricionista na camada
 * view
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class NutricionistaBean {

	private Nutricionista nutricionista;
	private static INutricionistaService nutricionistaService;

	public Nutricionista getNutricionista() {
		if (this.nutricionista.getId() == null) {
			if (UsuarioBean.usuarioLogado != null && UsuarioBean.usuarioLogado.getId() != null) {
				this.nutricionista = this.findByUserId();
				if (this.nutricionista == null) {
					this.nutricionista = new Nutricionista();
				}
			}
		}
		return nutricionista;
	}

	public void setNutricionista(Nutricionista nutricionista) {
		this.nutricionista = nutricionista;
	}

	public INutricionistaService getNutricionistaService() {
		return nutricionistaService;
	}

	@SuppressWarnings("static-access")
	public void setNutricionistaService(
			INutricionistaService nutricionistaService) {
		this.nutricionistaService = nutricionistaService;
	}

	public void save() {
		if (nutricionista.getId() == null) {
			nutricionistaService.save(this.nutricionista);
		} else {
			nutricionistaService.update(this.nutricionista);
		}
		this.nutricionista = new Nutricionista();
	}

	public void delete() {
		nutricionistaService.delete(this.nutricionista);
	}
	
	private Nutricionista findByUserId() {
		return nutricionistaService.findByUserId(UsuarioBean.usuarioLogado.getId());
	}
	
	public List<Nutricionista> getListForApproval() {
	    return nutricionistaService.listForApproval();
	}
	
	public boolean getListaVazia() {
		if (this.getListForApproval().size() > 0) {
			return false;
		}
		return true;
	}
}