package com.nutri.java.view.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.nutri.java.model.Alimento;
import com.nutri.java.model.Medida;
import com.nutri.java.services.IAlimentoService;
import com.nutri.java.services.IMedidaService;

/**
 * ManageBean que encapsula as propriedades da entidade Alimento na camada view
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class AlimentoBean {

	private Alimento alimento;
	private static IAlimentoService alimentoService;
	private static IMedidaService medidaService;
	
	private HtmlSelectOneMenu grupoBinding;
	
	private List<Alimento> alimentos;
	private List<Medida> allMedidas;
	
	private String nomeAlimento;
	private int idMedida;
	private int idCateg;

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public IAlimentoService getAlimentoService() {
		return alimentoService;
	}

	@SuppressWarnings("static-access")
	public void setAlimentoService(IAlimentoService alimentoService) {
		this.alimentoService = alimentoService;
	}

	public IMedidaService getMedidaService() {
		return medidaService;
	}

	@SuppressWarnings("static-access")
	public void setMedidaService(IMedidaService medidaService) {
		this.medidaService = medidaService;
	}

	public String getNomeAlimento() {
		return nomeAlimento;
	}

	public void setNomeAlimento(String nomeAlimento) {
		this.nomeAlimento = nomeAlimento;
	}

	public void setIdCateg(int idCateg) {
		this.idCateg = idCateg;
	}

	public int getIdCateg() {
		return idCateg;
	}

	public void setIdMedida(int idMedida) {
		this.idMedida = idMedida;
	}

	public int getIdMedida() {
		return idMedida;
	}

	public void setGrupoBinding(HtmlSelectOneMenu grupoBinding) {
		this.grupoBinding = grupoBinding;
	}

	public HtmlSelectOneMenu getGrupoBinding() {
		return grupoBinding;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}
	
	public boolean getListaVazia() {
		if (this.getAlimentos() != null && this.getAlimentos().size() == 0) {
			return true;
		}
		return false;
	}

	public List<SelectItem> getAllMedidas() {
		allMedidas = medidaService.listAll();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Medida m : allMedidas) {
			items.add(new SelectItem(m.getId(), m.getNome()));
		}
		return items;
	}

	public String save() {
		if (alimento.getId() == null) {
			alimentoService.save(this.alimento);
		} else {
			alimentoService.update(this.alimento);
		}
		this.alimento = new Alimento();
		return null;
	}

	public String delete() {
		alimentoService.delete(this.alimento);
		return null;
	}

	public static Alimento findById(Integer id) {
		return alimentoService.findById(id);
	}

	public List<Alimento> findByName(String name) {
		return alimentoService.findByName(name);
	}

	public String pesqPorCateg() {
		String retorno = null;
		try {
			this.alimentos = alimentoService.listPorCateg(Integer
					.parseInt(FacesContext.getCurrentInstance()
							.getExternalContext().getRequestParameterMap().get(
									"idCateg").toString()));
			retorno = "listaAlimentos";
		} catch (Exception e) {
			e.printStackTrace();
			retorno = "aplicacao";
		}
		return retorno;
	}

	public String pesqPorFiltro() {
		String retorno = null;
		try {
			alimentos = this.findByName(getNomeAlimento());
			this.nomeAlimento = null;
			retorno = "listaAlimentos";
		} catch (Exception e) {
			this.nomeAlimento = null;
			e.printStackTrace();
			retorno = "aplicacao";
		}
		return retorno;
	}
	
	// XXX Dar um jeito neste e nos outros combos da app
	public void carregarCategorias(ValueChangeEvent e) {
		System.out.println("AlimentoBean.carregarCategorias()");
	}
}
