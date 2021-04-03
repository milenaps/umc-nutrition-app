package com.nutri.java.dao;

import java.util.List;

import com.nutri.java.commons.IBaseDAO;
import com.nutri.java.commons.NutriException;
import com.nutri.java.model.Categoria;

/**
 * Interface de persistencia para a entidade Categoria
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IBaseDAO
 */
public interface ICategoriaDAO extends IBaseDAO<Categoria> {

	/**
	 * Lista categorias dado o grupo alimentar informado
	 * 
	 * @param grupo
	 *            Integer
	 * @return A lista
	 * @throws NutriException
	 *             Lancada se houver problemas de conexao
	 */
	public List<Categoria> listPorGrupo(int grupo);
}
