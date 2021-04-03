package com.nutri.java.view.bean;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.nutri.java.model.Avaliacao;
import com.nutri.java.model.Cardapio;
import com.nutri.java.model.ItemCardapio;
import com.nutri.java.services.ICardapioService;

public class CardapioBean {

	private Avaliacao avaliacao;
	private Cardapio cardapio;
	private ItemCardapio itemCardapio;
	private static ICardapioService cardapioService;
	private PacienteBean pacienteBean;
	private NutricionistaBean nutricionistaBean;
	private GrupoAlimentarBean grupoAlimentarBean;
	
	public Cardapio getCardapio() {
		if (this.cardapio.getItens() == null && this.nutricionistaBean.getNutricionista() != null && this.nutricionistaBean.getNutricionista().getId() != null) {
			this.cardapio = this.findByNutricionista(this.nutricionistaBean.getNutricionista().getId());
			if (this.cardapio == null) {
				this.cardapio = new Cardapio();
			}
		}
		if (this.cardapio.getItens() == null && this.pacienteBean.getPaciente() != null && this.pacienteBean.getPaciente().getId() != null) {
			this.cardapio = this.findByPaciente(this.pacienteBean.getPaciente().getId());
			if (this.cardapio == null) {
				this.cardapio = new Cardapio();
			}
		}
		return this.cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public ItemCardapio getItemCardapio() {
		return itemCardapio;
	}

	public void setItemCardapio(ItemCardapio itemCardapio) {
		this.itemCardapio = itemCardapio;
	}

	public ICardapioService getCardapioService() {
		return cardapioService;
	}

	@SuppressWarnings("static-access")
	public void setCardapioService(ICardapioService cardapioService) {
		this.cardapioService = cardapioService;
	}

	public void setPacienteBean(PacienteBean pacienteBean) {
		this.pacienteBean = pacienteBean;
	}

	public PacienteBean getPacienteBean() {
		return this.pacienteBean;
	}

	public void setNutricionistaBean(NutricionistaBean nutricionistaBean) {
		this.nutricionistaBean = nutricionistaBean;
	}
	
	public NutricionistaBean getNutricionistaBean() {
		return this.nutricionistaBean;
	}
	
	public void setGrupoAlimentarBean(GrupoAlimentarBean grupoAlimentarBean) {
		this.grupoAlimentarBean = grupoAlimentarBean;
	}

	public GrupoAlimentarBean getGrupoAlimentarBean() {
		return grupoAlimentarBean;
	}

	public boolean getListaVazia() {
		if (this.cardapio.getItens() != null && this.cardapio.getItens().size() == 0) {
			return true;
		}
		return false;
	}
	
	public void atualizarCardapio(ActionEvent e) {
		this.itemCardapio.setAlimento(AlimentoBean.findById(Integer.parseInt(e.getComponent().getAttributes().get("idAlimento").toString())));
		this.itemCardapio.setQuantidade(Double.parseDouble(e.getComponent().getAttributes().get("quantidade").toString()));
		if (this.cardapio.getItens() == null) {
			this.cardapio.setItens(new ArrayList<ItemCardapio>());
		}
		this.cardapio.getItens().add(this.itemCardapio);
		this.itemCardapio = new ItemCardapio();
		new HistoricoAlimentarBean();
	}
	
	public boolean isCardapioValido() {
		return this.avaliacao.analisarCardapio(this.pacienteBean.getPaciente(), this.cardapio, this.grupoAlimentarBean.getAll());
	}
	
	public void save(ActionEvent e) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		
    	if (this.isCardapioValido()) {
    		if (this.pacienteBean.getPaciente().getId() != null) {
    			this.cardapio.setPaciente(this.pacienteBean.getPaciente());
    		}
    		if (this.nutricionistaBean.getNutricionista().getId() != null) {
    			this.cardapio.setNutricionista(this.nutricionistaBean.getNutricionista());
    		}
    		cardapioService.save(this.cardapio);
    		this.cardapio = new Cardapio();
    		
    		msg.setSummary("O seu cardápio foi salvo com sucesso!");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			ctx = FacesContext.getCurrentInstance();
			ctx.addMessage("CardapioSalvo", msg);
    	} else {
			msg.setSummary("O seu cardápio não foi salvo pois não está balanceado, altere-o adicionando alimentos pertencentes a todos os grupos alimentares e clique para montá-lo novamente.");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			ctx = FacesContext.getCurrentInstance();
			ctx.addMessage("CardapioIrregular", msg);
    	}
	}
	
	private Cardapio findByNutricionista(int nutricionista) {
		return cardapioService.findByNutricionista(nutricionista);
	}
	
	private Cardapio findByPaciente(int paciente) {
		return cardapioService.findByPaciente(paciente);
	}
}
