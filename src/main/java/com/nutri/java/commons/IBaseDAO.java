package com.nutri.java.commons;

import java.io.Serializable;
import java.util.List;

/**
 * Interface de persistencia com Hibernate
 * 
 * @author Marcio Barroso
 * @author Milena Santos
 * @version 2.0
 *
 * @param <T>
 */
public interface IBaseDAO<T> {

	/**
	 * Salva os dados do elemento representado pela entidade informada, via
	 * hibernate
	 * 
	 * @param entity
	 *            T
	 * @return O elemento salvo
	 */
	public T save(T entity);

	/**
	 * Recupera o elemento da entidade que possui o id informado
	 * 
	 * @param id
	 *            Serializable
	 * @return O elemento
	 */
	public T get(Serializable id);

	/**
	 * Lista todas as ocorrencias de uma entidade
	 * 
	 * @return A lista
	 */
	public List<T> listAll();

	/**
	 * Lista todos os elementos dados o indice desejado e o numero maximo de
	 * ocorrencias a listar
	 * 
	 * @param first
	 *            int
	 * @param max
	 *            int
	 * @return A lista
	 */
	public List<T> listAll(final int first, final int max);

	/**
	 * Atualiza os dados do elemento representado pela entidade informada via
	 * hibernate
	 * 
	 * @param entity
	 *            T
	 */
	public void update(T entity);

	/**
	 * Deleta o elemento representado pela entidade informada
	 * 
	 * @param entity
	 *            T
	 */
	public void delete(T entity);

	/**
	 * Procura pela entidade que possui o id informado
	 * 
	 * @param id
	 *            int
	 * @return A entidade
	 */
	public T findById(int id);

	/**
	 * Procura por entidades que possuem o nome informado ou parte dele
	 * 
	 * @param name
	 *            String
	 * @return A lista de elementos
	 */
	public List<T> findByName(String name);

	// public T load(Serializable id);

	// public List<T> findByExample(final T entity);

	// public T findOneByExample(final T entity);

	// public List<T> listByExample(final T entity, final int first, final int
	// max);

	// public int listAllPageCount();

	// public int findByExamplePageCount(final T entity);

	// public void rebind(T entity);

	// public List<T> loadAll();
}