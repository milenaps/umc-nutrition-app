package com.nutri.java.view.bean;

import java.util.List;

import javax.faces.event.ActionEvent;

import com.nutri.java.model.Atividade;
import com.nutri.java.model.FrequenciaAtividade;
import com.nutri.java.services.IAtividadeService;
import com.nutri.java.services.IFrequenciaAtividadeService;

/**
 * ManageBean que encapsula as propriedades da entidade Atividade na camada view
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class AtividadeBean {

	private Atividade atividade;
	private static IAtividadeService atividadeService;
	private static IFrequenciaAtividadeService frequenciaAtividadeService;

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public IAtividadeService getAtividadeService() {
		return atividadeService;
	}

	@SuppressWarnings("static-access")
	public void setAtividadeService(IAtividadeService atividadeService) {
		this.atividadeService = atividadeService;
	}

	public IFrequenciaAtividadeService getFrequenciaAtividadeService() {
		return frequenciaAtividadeService;
	}

	@SuppressWarnings("static-access")
	public void setFrequenciaAtividadeService(
			IFrequenciaAtividadeService frequenciaAtividadeService) {
		this.frequenciaAtividadeService = frequenciaAtividadeService;
	}

	public void save(ActionEvent e) {
		if (atividade.getId() == null) {
			atividadeService.save(this.atividade);
		} else {
			atividadeService.update(this.atividade);
		}
		this.atividade = new Atividade();
	}
	
	public void update(ActionEvent e) {
		this.atividade = atividadeService.findById(Integer.parseInt(e.getComponent().getAttributes().get("idAtividade").toString()));
	}

	public void delete(ActionEvent e) {
		this.atividade = atividadeService.findById(Integer.parseInt(e.getComponent().getAttributes().get("idAtividade").toString()));
		atividadeService.delete(this.atividade);
		this.atividade = new Atividade();
	}

	public List<Atividade> getAll() {
		return atividadeService.listAll();
	}

	public static List<Atividade> getAtividades() {
		return atividadeService.listAll();
	}

	public static List<FrequenciaAtividade> getFrequencias() {
		return frequenciaAtividadeService.listAll();
	}

}
