package com.nutri.java.view.bean;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.nutri.java.model.Categoria;
import com.nutri.java.services.ICategoriaService;

/**
 * ManageBean que encapsula as propriedades da entidade Categoria na camada view
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class CategoriaBean {

	private Categoria categoria;
	private static ICategoriaService categoriaService;
	private List<Categoria> categorias;
	private Integer idGrupo;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public ICategoriaService getCategoriaService() {
		return categoriaService;
	}

	@SuppressWarnings("static-access")
	public void setCategoriaService(ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public String pesqPorGrupo() {
		String retorno = null;
		try {
			categorias = categoriaService.listPorGrupo(Integer
					.parseInt(FacesContext.getCurrentInstance()
							.getExternalContext().getRequestParameterMap().get(
									"idGrupo").toString()));
			retorno = "listaCategorias";
		} catch (Exception e) {
			e.printStackTrace();
			retorno = "aplicacao";
		}
		return retorno;
	}

	public void save(ActionEvent e) {
		this.categoria.setGrupoAlimentar(GrupoAlimentarBean.findById(this.idGrupo));
		if (categoria.getId() == null) {
			categoriaService.save(this.categoria);
		} else {
			categoriaService.update(this.categoria);
		}
		this.categoria = new Categoria();
		this.idGrupo = null;
	}

	public void update(ActionEvent e) {
		this.categoria = categoriaService.findById(Integer.parseInt(e.getComponent().getAttributes().get("idCategoria").toString()));
		this.idGrupo = this.categoria.getGrupoAlimentar().getId();
	}

	public void delete(ActionEvent e) {
		this.categoria = categoriaService.findById(Integer.parseInt(e.getComponent().getAttributes().get("idCategoria").toString()));
		categoriaService.delete(this.categoria);
		this.categoria = new Categoria();
	}

	public List<Categoria> getAll() {
		return categoriaService.listAll();
	}
}
