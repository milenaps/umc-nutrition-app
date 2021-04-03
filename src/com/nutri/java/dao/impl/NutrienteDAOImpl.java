package com.nutri.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nutri.java.commons.IJdbcDAO;
import com.nutri.java.commons.impl.BaseDAOImpl;
import com.nutri.java.commons.impl.JdbcDAOImpl;
import com.nutri.java.dao.INutrienteDAO;
import com.nutri.java.model.Nutriente;

/**
 * Implementa a interface de persistencia da entidade Nutriente
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see INutrienteDAO
 */
public class NutrienteDAOImpl extends BaseDAOImpl<Nutriente> implements
		INutrienteDAO {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1595142294318698496L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(AlimentoDAOImpl.class);
	
	@Override
	public Nutriente findById(int id) {
		logger.info("Procurando pelo nutriente de id = "+id);
		Nutriente n = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select nome from nutriente where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				n = new Nutriente();
				n.setId(id);
				n.setNome(rs.getString("nome"));
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na busca de nutriente por id: " + e.getMessage(), e.getCause());
		}
		
		return n;
	}

	@Override
	public List<Nutriente> findByName(String name) {
		return null;
	}

	@Override
	public void delete(Nutriente entity) {
		logger.info("Deletando o nutriente \"" + entity.getNome() + "\"");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" delete from nutriente where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, entity.getId());
			
			stmt.executeUpdate();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro na deleção do nutriente: " + e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Nutriente> listAll() {
		logger.info("Listando todos os nutrientes");
		
		Nutriente n = null;
		List<Nutriente> nutrientes = new ArrayList<Nutriente>();
		
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select id, nome from nutriente ");
			
			stmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				n = new Nutriente();
				n.setId(rs.getInt("id"));
				n.setNome(rs.getString("nome"));
				
				nutrientes.add(n);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na listagem de nutrientes: " + e.getMessage(), e.getCause());
		}
		
		return nutrientes;
	}

	@Override
	public Nutriente save(Nutriente entity) {
		logger.info("Inserindo um novo nutriente");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into nutriente values (?, ?); ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, getNumEntities(entity));
			stmt.setString(2, entity.getNome());
			
			stmt.execute();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro no salvamento do nutriente: " + e.getMessage(), e.getCause());
		}
		
		return entity;
	}

	@Override
	public void update(Nutriente entity) {
		logger.info("Atualizando o nutriente \"" + entity.getNome() + "\"");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update nutriente set nome=? where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, entity.getNome());
			stmt.setInt(2, entity.getId());
			
			stmt.executeUpdate();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro na atualizacao do nutriente: " + e.getMessage(), e.getCause());
		}
	}
}
