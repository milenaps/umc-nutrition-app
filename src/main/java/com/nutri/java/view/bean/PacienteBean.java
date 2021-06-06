package com.nutri.java.view.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.nutri.java.model.Atividade;
import com.nutri.java.model.FrequenciaAtividade;
import com.nutri.java.model.HistoricoAlimentar;
import com.nutri.java.model.Paciente;
import com.nutri.java.services.IPacienteService;

/**
 * ManageBean que encapsula as propriedades da entidade Paciente na camada view
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class PacienteBean {

	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(UsuarioBean.class);
	
	private Paciente paciente;
	private static IPacienteService pacienteService;

	private List<Atividade> allAtividades;
	private List<FrequenciaAtividade> allFrequencias;

	public Paciente getPaciente() {
		if (this.paciente.getId() == null) {
			if (UsuarioBean.usuarioLogado != null && UsuarioBean.usuarioLogado.getId() != null) {
				this.paciente = this.findByUserId();
				if (this.paciente == null) {
					this.paciente = new Paciente();
				}
			}
		}
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public IPacienteService getPacienteService() {
		return pacienteService;
	}

	@SuppressWarnings("static-access")
	public void setPacienteService(IPacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	public List<Atividade> getAllAtividades() {
		this.allAtividades = AtividadeBean.getAtividades();
		return this.allAtividades;
	}

	public List<SelectItem> getAllFrequencias() {
		this.allFrequencias = AtividadeBean.getFrequencias();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (FrequenciaAtividade f : allFrequencias) {
			items.add(new SelectItem(f.getId(), f.getFrequenciaDiaria()));
		}
		return items;
	}
	
	public List<HistoricoAlimentar> getAllHistoricos() {
		return getAllHistories(this.paciente.getId());
	}

	public String inserirAtividade() {
//		this.paciente.setAtividades(atividades)
//		categorias = categoriaService.listPorGrupo(Integer
//				.parseInt(FacesContext.getCurrentInstance()
//						.getExternalContext().getRequestParameterMap().get(
//								"idAtividade").toString()));
//		Atividade a = new Atividade();
//		a = this.atividadeService.findById(Integer.parseInt(FacesContext.getCurrentInstance()
//						.getExternalContext().getRequestParameterMap().get(
//								"idAtividade").toString()));
//		a.setFrequencia(frequencia)
//		this.paciente.getAtividades().add()
		logger.info("PacienteBean.inserirAtividade() --> Passou neste metodo");
		return null;
	}
	
	public boolean getNoActivities() {
		return this.paciente.getAtividades().size() > 0;
	}
	
	private Paciente findByUserId() {
		return pacienteService.findByUserId(UsuarioBean.usuarioLogado.getId());
	}
	
	public static List<HistoricoAlimentar> listHistoricosByPeriod(int paciente, Date dataDe, Date dataAte) {
		return pacienteService.listHistoricosByPeriod(paciente, dataDe, dataAte);
	}
	
	private static List<HistoricoAlimentar> getAllHistories(int paciente) {
		return pacienteService.listHistoricosAlimentares(paciente);
	}
	
	public boolean getHasHistories() {
		if (this.getAllHistoricos().size() > 0) {
			return true;
		}
		return false;
	}
	
	public void atualizarRepositorio(ActionEvent e) {
		if (true) {
			//
		}
	}
	
	public void save() {
		if (paciente.getId() == null) {
			pacienteService.save(this.paciente);
		} else if (paciente != null) {
			pacienteService.update(this.paciente);
		}
		this.paciente = new Paciente();
	}

	public void delete() {
		pacienteService.delete(this.paciente);
	}
}
