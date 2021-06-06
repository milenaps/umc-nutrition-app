package com.nutri.java.model;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

/**
 * Implementa regras de negocio da aplicacao
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class Avaliacao {

	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(Avaliacao.class);

	private ResultadoAnalise resultadoAnalise;

	public void setResultadoAnalise(ResultadoAnalise resultadoAnalise) {
		this.resultadoAnalise = resultadoAnalise;
	}

	public ResultadoAnalise getResultadoAnalise() {
		return resultadoAnalise;
	}

	/**
	 * Realiza as rotinas de analise da avaliacao nutricional do paciente
	 * 
	 * @param paciente
	 *            Paciente
	 */
	public void analisarAlimentacao(Paciente paciente,
			List<HistoricoAlimentar> lista, List<GrupoAlimentar> grupos,
			Date dataDe, Date dataAte) {

		// Quantidade total de historicos alimentares
		int qtdHistoricos = lista.size();
		
		// Quantidade de grupos alimentares
		int qtdGrupos = grupos.size();
		
		// Obtem o consumo desejado de acordo com o perfil do paciente
		// adequadamente
		double[] consumoDesejavel = this.analisarPerfilPaciente(paciente,
				grupos);

		// Obtem em quantos dias foram armazenadas refeicoes dado o periodo
		// setado
		int dias = 1;
		double qtdConsumidaTotal = 0;
		for (int i = 0; i < qtdHistoricos; i++) {
			Date data = lista.get(i).getData();
			if (i > 0 && !data.equals(lista.get(i - 1).getData())) {
				dias++;
			}

			// Recupera a quantidade total em gramas de todos os alimentos
			// consumidos no periodo
			qtdConsumidaTotal += lista.get(i).getQuantidade();
		}

		/*
		 * XXX Como melhoria, esquematizar a separacao das refeicoes por horarios para obter resultados mais favoraveis da avaliacao
		 */

		// Tira a media de alimentos consumidos em cada grupo alimentar por dia
		double[] consumoReal = new double[qtdGrupos];

		for (int i = 0; i < qtdHistoricos; i++) {
			for (int j = 0; j < qtdGrupos; j++) {
				if (lista.get(i).getAlimento().getCategoria()
						.getGrupoAlimentar().getId().equals(
								grupos.get(j).getId())) {
					consumoReal[j] += (lista.get(i).getQuantidade() * 100)
							/ qtdConsumidaTotal;
				}
			}
		}

		/*
		 * Compara o consumo real no periodo com o consumo recomendado pelo
		 * perfil do paciente
		 */
		double[] consumoComparacao = new double[qtdGrupos];
		StringBuilder informativo = new StringBuilder("Você consumiu:\n");
		int posicaoRanking = 0;
		for (int i = 0; i < qtdGrupos; i++) {
			consumoComparacao[i] = consumoReal[i] - consumoDesejavel[i];

			if (consumoComparacao[i] < 0) {
				informativo.append("Poucos(as) " + grupos.get(i).getNome()
						+ ";\n");
				posicaoRanking--;
			} else if (consumoComparacao[i] > 5) {
				informativo.append(grupos.get(i).getNome() + " em excesso;\n");
				posicaoRanking--;
			} else if (consumoComparacao[i] > 0) {
				informativo.append("Uma quantia razoável de "
						+ grupos.get(i).getNome() + ";\n");
				posicaoRanking++;
			}
		}

		/*
		 * Popula os resultados da analise
		 */
		this.resultadoAnalise.setDataInicial(dataDe);
		this.resultadoAnalise.setDataFinal(dataAte);
		this.resultadoAnalise.setPaciente(paciente);
		this.resultadoAnalise.setInformativo(this.resultadoAnalise
				.getInformativo() + informativo.toString());
		this.resultadoAnalise.setPosicaoRanking((5 + posicaoRanking) / 100);
	}
	
	/**
	 * Realiza as rotinas de analise do cardapio montado pelo paciente
	 * 
	 * @param paciente
	 * 			Paciente
	 * @param cardapio
	 * 			Cardapio
	 * @param grupos
	 * 			List
	 */
	public boolean analisarCardapio(Paciente paciente,
			Cardapio cardapio, List<GrupoAlimentar> grupos) {

		// Quantidade de itens do cardapio
		int qtdItensCardapio = cardapio.getItens().size();
		
		// Quantidade de grupos alimentares
		int qtdGrupos = grupos.size();
		
		// Obtem o consumo desejavel do paciente de acordo com seu perfil
		double[] consumoDesejavel = this.analisarPerfilPaciente(paciente,
				grupos);

		/*
		 * TODO Analisar as refeicoes juntamente com o periodo indicado no cardapio (almoço, jantar, etc)
		 */

		// Tira a media de alimentos consumidos em cada grupo alimentar
		double[] consumoReal = new double[qtdGrupos];

		for (int i = 0; i < qtdItensCardapio; i++) {
			for (int j = 0; j < qtdGrupos; j++) {
				if (cardapio.getItens().get(i).getAlimento().getCategoria()
						.getGrupoAlimentar().getId().equals(
								grupos.get(j).getId())) {
					consumoReal[j] += cardapio.getItens().get(i).getQuantidade();
				}
			}
		}

		/*
		 * Compara o consumo real no periodo com o consumo recomendado pelo
		 * perfil do paciente
		 */
		double[] consumoComparacao = new double[qtdGrupos];
		int posicaoRanking = 0;
		for (int i = 0; i < qtdGrupos; i++) {
			consumoComparacao[i] = consumoReal[i] - consumoDesejavel[i];
			if (consumoComparacao[i] < 0) {
				posicaoRanking--;
			} else if (consumoComparacao[i] > 5) {
				posicaoRanking--;
			} else if (consumoComparacao[i] >= 0) {
				posicaoRanking++;
			}
		}

		return posicaoRanking >= 3;
	}

	/**
	 * Analisa o perfil do paciente retornando o consumo ideal de cada grupo alimentar
	 * 
	 * @param paciente
	 * 			Paciente
	 * @param grupos
	 * 			List<GrupoAlimentar>
	 * @return O consumo desejável
	 */
	public double[] analisarPerfilPaciente(Paciente paciente,
			List<GrupoAlimentar> grupos) {

		// Quantidade de grupos
		int qtdGrupos = grupos.size();
		
		// Variavel para guardar o consumo diario requerido dependendo do perfil
		double[] consumoDiarioRecomendado = new double[qtdGrupos];

		if (paciente.getId() != null) {
			String perfil = paciente.getPerfil().getPerfil();
	
			double IMC = paciente.getPeso()
					/ (paciente.getAltura() * paciente.getAltura());
	
			// TODO Logica para obter a taxa de metabolismo basal do paciente
	
			// Adulto x Ativo x Carnivoro
			if (perfil.equals("H_ADU_ATI_CAR") || perfil.equals("M_ADU_ATI_CAR")) {
				// TODO Analise de perfil [Adulto + Ativo + Carnivoro]
			}
			// Adulto x Sedentario x Carnivoro
			else if (perfil.equals("H_ADU_SED_CAR")
					|| perfil.equals("M_ADU_SED_CAR")) {
				// TODO Analise de perfil [Adulto + Sedentario + Carnivoro]
			}
			// Adulto x Ativo x Vegetariano
			else if (perfil.equals("H_ADU_ATI_VEG")
					|| perfil.equals("M_ADU_ATI_VEG")) {
				// TODO Analise de perfil [Adulto + Ativo + Vege]
			}
			// Adulto x Sedentario x Vegetariano
			else if (perfil.equals("H_ADU_SED_VEG")
					|| perfil.equals("M_ADU_SED_VEG")) {
				// TODO Analise de perfil [Adulto + Sedentario + Vege]
			}
			// Crianca
			else {
				logger.info("A analise de perfil para criancas ainda nao eh coberta pela aplicacao");
	
				FacesContext ctx = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage();
				msg.setSummary("A analise de perfil para criancas ainda nao eh coberta pela aplicacao");
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				ctx.addMessage("AnalisePerfilCriancas", msg);
	
				return null;
			}
			
			// Adicionando informacoes relativas ao perfil atual do paciente
			String informativo = "Seu IMC é de " + IMC;
			if (IMC < 18.5) {
				informativo += ", e indica que você está abaixo do seu peso ideal. Procure apoio médico especializado o mais breve possível.\n";
			} else if (IMC > 40) {
				informativo += ". Recomendamos que você procure o médico endocrinologista de sua confiança com urgência.\n";
			} else if (IMC > 25) {
				informativo += ", e indica que você precisa adotar uma alimentação mais saudável. Sugerimos que você procure um nutricionista de sua confiança.\n";
			} else {
				informativo += ". Parabéns! Continue se alimentando de forma saudável e atente para as observações a seguir.\n";
			}
			this.resultadoAnalise.setInformativo(informativo);
		}
		
		// FIXME Tirar o loop depois que implementar a analise de perfil efetivamente
		for (int i = 0; i < qtdGrupos; i++) {
			consumoDiarioRecomendado[i] = 1;
		}

		return consumoDiarioRecomendado;
	}
}
