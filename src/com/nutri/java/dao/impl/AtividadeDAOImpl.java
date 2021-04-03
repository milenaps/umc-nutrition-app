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
import com.nutri.java.dao.IAtividadeDAO;
import com.nutri.java.model.Atividade;

/**
 * Implementa a interface de persistencia da entidade Atividade
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IAtividadeDAO
 */
public class AtividadeDAOImpl extends BaseDAOImpl<Atividade> implements IAtividadeDAO {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -5253424388671191529L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(AlimentoDAOImpl.class);
	
	@Override
	public Atividade findById(int id) {
		logger.info("Procurando a atividade de id = " + id);
		Atividade atividade = null;

		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select nome from atividade where id=? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				atividade = new Atividade();
				atividade.setId(id);
				atividade.setNome(rs.getString("nome"));
			}

			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na procura de atividade por id: " + e.getMessage(), e.getCause());
		}

		return atividade;
	}

	@Override
	public List<Atividade> findByName(String name) {
		return null;
	}

	@Override
	public void delete(Atividade entity) {
		logger.info("Deletando a atividade \"" + entity.getNome() + "\"");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" delete from atividade where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, entity.getId());
			
			stmt.executeUpdate();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro na deleção da atividade: " + e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Atividade> listAll() {
		logger.info("Listando todas as atividades");
		
		Atividade a = null;
		List<Atividade> atividades = new ArrayList<Atividade>();
		
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select id, nome from atividade ");
			
			stmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				a = new Atividade();
				a.setId(rs.getInt("id"));
				a.setNome(rs.getString("nome"));
				
				atividades.add(a);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na listagem de atividades: " + e.getMessage(), e.getCause());
		}
		
		return atividades;
	}

	@Override
	public Atividade save(Atividade entity) {
		logger.info("Inserindo uma nova atividade");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into atividade values (?, ?); ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, getNumEntities(entity));
			stmt.setString(2, entity.getNome());
			
			stmt.execute();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro no salvamento da atividade: " + e.getMessage(), e.getCause());
		}
		
		return entity;
	}

	@Override
	public void update(Atividade entity) {
		logger.info("Atualizando a atividade \"" + entity.getNome() + "\"");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update atividade set nome=? where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, entity.getNome());
			stmt.setInt(2, entity.getId());
			
			stmt.executeUpdate();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro na atualizacao da atividade: " + e.getMessage(), e.getCause());
		}
	}
}
