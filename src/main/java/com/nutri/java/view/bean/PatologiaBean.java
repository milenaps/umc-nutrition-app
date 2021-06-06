package com.nutri.java.view.bean;

import java.util.List;

import com.nutri.java.model.Patologia;
import com.nutri.java.services.IPatologiaService;

/**
 * ManageBean que encapsula as propriedades da entidade Patologia na camada view
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class PatologiaBean {

	private Patologia patologia;
	private static IPatologiaService patologiaService;

	public Patologia getPatologia() {
		return patologia;
	}

	public void setPatologia(Patologia patologia) {
		this.patologia = patologia;
	}

	public IPatologiaService getPatologiaService() {
		return patologiaService;
	}

	@SuppressWarnings("static-access")
	public void setPatologiaService(IPatologiaService patologiaService) {
		this.patologiaService = patologiaService;
	}

	public String save() {
		if (patologia.getId() == null) {
			patologiaService.save(this.patologia);
		} else {
			patologiaService.update(this.patologia);
		}
		this.patologia = new Patologia();
		return null;
	}

	public String delete() {
		patologiaService.delete(this.patologia);
		return null;
	}

	public List<Patologia> getAll() {
		return patologiaService.listAll();
	}
}
