package com.nutri.java.view.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.nutri.java.model.Avaliacao;
import com.nutri.java.model.HistoricoAlimentar;
import com.nutri.java.model.Paciente;
import com.nutri.java.model.ResultadoAnalise;
import com.nutri.java.services.IResultadoAnaliseService;

public class AvaliacaoBean {

	private Avaliacao avaliacao;
	private IResultadoAnaliseService resultadoAnaliseService;
	private PacienteBean pacienteBean;
	private GrupoAlimentarBean grupoAlimentarBean;

	private String posicaoRanking;
	
	private Date historicoDe;
	private Date historicoAte;

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public IResultadoAnaliseService getResultadoAnaliseService() {
		return resultadoAnaliseService;
	}

	public void setResultadoAnaliseService(
			IResultadoAnaliseService resultadoAnaliseService) {
		this.resultadoAnaliseService = resultadoAnaliseService;
	}

	public void setPacienteBean(PacienteBean pacienteBean) {
		this.pacienteBean = pacienteBean;
	}

	public PacienteBean getPacienteBean() {
		return pacienteBean;
	}

	public void setGrupoAlimentarBean(GrupoAlimentarBean grupoAlimentarBean) {
		this.grupoAlimentarBean = grupoAlimentarBean;
	}

	public GrupoAlimentarBean getGrupoAlimentarBean() {
		return grupoAlimentarBean;
	}

	public void setHistoricoDe(Date historicoDe) {
		this.historicoDe = historicoDe;
	}

	public Date getHistoricoDe() {
		return historicoDe;
	}

	public void setHistoricoAte(Date historicoAte) {
		this.historicoAte = historicoAte;
	}

	public Date getHistoricoAte() {
		return historicoAte;
	}

	public void setPosicaoRanking(String posicaoRanking) {
		this.posicaoRanking = posicaoRanking;
	}

	public String getPosicaoRanking() {
		return posicaoRanking;
	}

	/**
	 * Valida as informacoes para realizacao da analise da alimentacao
	 * 
	 * @return A pagina atualizada
	 */
	@SuppressWarnings("deprecation")
	public String analisarAlimentacao() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		// Valida o periodo informado para analise
		if (historicoDe.after(historicoAte)) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary("A data inicial deve ser menor ou igual à final para efetuar a análise");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			ctx.addMessage("PeriodoInvalido", msg);
		}
		// Data inicial mais de 1 ano menor que data final
		else if (historicoDe.getMonth() < historicoAte.getMonth()
				&& historicoDe.getYear() < historicoAte.getYear()) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary("Atualmente só é possível efetuar a análise da alimentação de um período inferior ou igual a 1 ano");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			ctx.addMessage("PeriodoInvalido", msg);
		}
		// Periodo valido para analisar
		else {
			this.analisarAlimentacao(this.pacienteBean.getPaciente());
		}
		return null;
	}

	/**
	 * Realiza a rotina de analise da alimentacao
	 * 
	 * @param paciente
	 * 			Paciente
	 */
	private void analisarAlimentacao(Paciente paciente) {
		
		// Popula uma lista de historicos do paciente por periodo
		List<HistoricoAlimentar> lista = PacienteBean.listHistoricosByPeriod(paciente.getId(), historicoDe, historicoAte);
		
		// XXX Definir as validacoes necessarias
		this.avaliacao.analisarAlimentacao(paciente, lista, this.grupoAlimentarBean.getAll(), historicoDe, historicoAte);
		
		if (this.avaliacao.getResultadoAnalise().getInformativo() == null) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage();
			msg.setSummary("O período que você informou não contém registros de refeições armazenadas em seu histórico.");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			ctx.addMessage("SemHistoricosNoPeriodo", msg);
		} else {
			// Salva o resultado da análise realizada
			this.resultadoAnaliseService.save(this.avaliacao.getResultadoAnalise());
			
			switch (this.avaliacao.getResultadoAnalise().getPosicaoRanking()) {
				case 0: this.posicaoRanking = " Inconcebível"; break;
				case 1: this.posicaoRanking = " Péssima"; break;
				case 2: this.posicaoRanking = " Ruim"; break;
				case 3: this.posicaoRanking = " Regular"; break;
				case 4: this.posicaoRanking = " Boa"; break;
				case 5: this.posicaoRanking = " Excelente"; break;
			}
		}
	}

	/**
	 * Recupera os resultados de analise do paciente
	 * 
	 * @return Uma lista de resultados de analise
	 */
	public List<ResultadoAnalise> getResultadosDeAnalise() {
		return this.resultadoAnaliseService.listByPaciente(this.pacienteBean.getPaciente().getId());
	}
	
	public boolean getNoResultsAvailable() {
		if (this.getResultadosDeAnalise() != null && this.getResultadosDeAnalise().size() == 0) {
			return true;
		}
		return false;
	}
	
	private ResultadoAnalise findById(int id) {
		return this.resultadoAnaliseService.findById(id);
	}
	
	public String detalharResultado() {
		this.findById(Integer
					.parseInt(FacesContext.getCurrentInstance()
							.getExternalContext().getRequestParameterMap().get(
									"idResultado").toString()));
		return "detalhesDeResultado";
	}
	
	/**
	 * Metodo existente para o caso de futuramente ser necessaria
	 * a criacao de validacoes para visualizar resultados, como exemplo,
	 * exibir mensagem de que nao ha resultados para visualizar ao
	 * inves de entrar na tela contendo nenhum conteudo
	 * 
	 * @return A pagina de listagem de resultados de analise
	 */
	public String listarResultados() {
		return "listaResultados";
	}
}
