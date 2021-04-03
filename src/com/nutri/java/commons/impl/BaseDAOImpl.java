package com.nutri.java.commons.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nutri.java.commons.IBaseDAO;

/**
 * Implementa a interface generica de persistencia com Hibernate
 * 
 * @author Marcio Barroso
 * @author Milena Santos
 * @version 2.0
 * 
 * @param <T>
 * @see HibernateDaoSupport
 * @see IBaseDAO
 * @see PropertySelector
 */
@Transactional(propagation = Propagation.REQUIRED)
public abstract class BaseDAOImpl<T> /*extends HibernateDaoSupport*/ implements
		IBaseDAO<T>, PropertySelector {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -756416053822078979L;

	/**
	 * Variavel para gerar log
	 */
	private static Logger logger = Logger.getLogger(BaseDAOImpl.class);

	/**
	 * Atributo que representa a entidade persistente
	 */
	private Class<T> persistentClass;

	/**
	 * Construtor da classe
	 */
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		super();
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		logger
				.debug("Building " + this.persistentClass.getSimpleName()
						+ "DAO");
	}

	/**
	 * @see IBaseDAO#delete(Object)
	 */
	public void delete(T entity) {
		logger.debug("Deleting entity " + this.persistentClass.getSimpleName());
//		getHibernateTemplate().delete(entity);
	}

	/**
	 * @see IBaseDAO#get(Serializable)
	 */
	public T get(Serializable id) {
		logger.debug("Loading the entity "
				+ this.persistentClass.getSimpleName() + " which pk is " + id);
		return /*(T) getHibernateTemplate().load(this.persistentClass, id)*/ null;
	}

	/**
	 * @see IBaseDAO#listAll()
	 */
	public List<T> listAll() {
		logger.debug("List all entities "
				+ this.persistentClass.getSimpleName());
		return /*getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(this.persistentClass))*/ null;
	}

	/**
	 * @see IBaseDAO#listAll(int, int)
	 */
	public List<T> listAll(int first, int max) {
		logger.debug("List all entities "
				+ this.persistentClass.getSimpleName()
				+ " using a offset. First row is " + first
				+ " and the range is " + max);
		return /*getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(this.persistentClass), first, max)*/ null;
	}

	/**
	 * @see IBaseDAO#save(Object)
	 */
	public T save(T entity) {
		logger.debug("Saving the entity "
				+ this.persistentClass.getSimpleName());
		return /*(T) getHibernateTemplate().save(entity)*/ null;
	}

	/**
	 * @see IBaseDAO#update(Object)
	 */
	public void update(T entity) {
		logger.debug("Updating the entity "
				+ this.persistentClass.getSimpleName());
		/*getHibernateTemplate().update(entity);*/
	}

	/**
	 * Informa se a propriedade esta apta a ser incluida
	 * 
	 * @param propertyValue
	 *            Object
	 * @param propertyName
	 *            String
	 * @param type
	 *            Type
	 * @return true ou false
	 */
	public boolean include(Object propertyValue, String propertyName, Type type) {
		if ((propertyValue != null) && (propertyValue instanceof String)) {
			return !"".equals(((String) propertyValue).trim());
		}
		return propertyValue != null;
	}

	/**
	 * Obtem o numero de registros da tabela representada pela entidade
	 * informada
	 * 
	 * @param entity
	 *            T
	 * @return O numero de entidades da tabela incrementado de 1
	 */
	public int getNumEntities(T entity) {
		logger.debug("Obtendo o número de registros da entidade "
				+ persistentClass.getSimpleName());
		int num = 1;
		JdbcDAOImpl jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select count(*) from "
					+ entity.getClass().getSimpleName().toLowerCase());
			stmt = con.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("count");
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
		}
		return num+1;
	}

	// @SuppressWarnings("unchecked")
	// public List<T> findByExample(T entity) {
	// logger.debug("Listing entity " + this.persistentClass.getSimpleName());
	// return getHibernateTemplate()
	// .findByCriteria(this.mountCriteria(entity));
	// }

	// public int findByExamplePageCount(T entity) {
	// logger.debug("Finding count of entity "
	// + this.persistentClass.getSimpleName());
	// final List<T> l = this.findByExample(entity);
	// final Integer i = new Integer(l.size());
	// return i.intValue();
	// }

	// public T findOneByExample(T entity) {
	// logger.debug("Finding entity " + this.persistentClass.getSimpleName()
	// + " per example");
	// List<T> list = this.findByExample(entity);
	// return list != null && !list.isEmpty() ? list.get(0) : null;
	// }

	// @SuppressWarnings("unchecked")
	// public List<T> listByExample(T entity, int first, int max) {
	// logger.debug("List all entities "
	// + this.persistentClass.getSimpleName()
	// + " per example and using a offset. First row is " + first
	// + " and the range is " + max);
	// return getHibernateTemplate().findByCriteria(
	// this.mountCriteria(entity), first, max);
	// }

	// public int listAllPageCount() {
	// logger.debug("Counting entities "
	// + this.persistentClass.getSimpleName() + " for paging");
	// final List<T> l = this.listAll();
	// final Integer i = new Integer(l.size());
	// return i.intValue();
	// }

	// public T load(Serializable id) {
	// logger.debug("Load an entity " + this.persistentClass.getSimpleName()
	// + " where its pk is " + id);
	// return (T) getHibernateTemplate().load(this.persistentClass, id);
	// }

	// public void rebind(T entity) {
	// logger.debug("Rebinding an entity "
	// + this.persistentClass.getSimpleName());
	// getHibernateTemplate().refresh(entity);
	// }

	/**
	 * Método auxiliar para criar a criteria a partir de um entity
	 * 
	 * @param entity
	 * @return DetachedCriteria
	 */
	// private DetachedCriteria mountCriteria(T entity) {
	// logger.debug("Mounting a dynamic criteria for entities "
	// + this.persistentClass.getSimpleName());
	// DetachedCriteria criteria = DetachedCriteria.forClass(
	// this.persistentClass).add(
	// Example.create(entity).enableLike(MatchMode.ANYWHERE)
	// .ignoreCase().setPropertySelector(this));
	// this.addOrderToCriteria(criteria);
	// this.addPropertiedToCriteria(criteria, entity);
	// return criteria;
	// }
	/**
	 * Este método deve ser implementado para ordenar uma lista
	 * 
	 * @param criteria
	 */
	// protected void addOrderToCriteria(DetachedCriteria criteria) {
	// }
	/**
	 * Este método deve ser implementado quando existe um atributo que é uma
	 * classe persistente
	 * 
	 * @param criteria
	 * @param example
	 */
	// protected void addPropertiedToCriteria(DetachedCriteria criteria, T
	// example) {
	// }
	// public List<T> loadAll() {
	// return getHibernateTemplate().loadAll(this.persistentClass);
	// }
}