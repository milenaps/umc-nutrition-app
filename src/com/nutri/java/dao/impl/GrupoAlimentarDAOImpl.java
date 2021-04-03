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
import com.nutri.java.dao.IGrupoAlimentarDAO;
import com.nutri.java.model.GrupoAlimentar;

/**
 * Implementa a interface de persistencia da entidade GrupoAlimentar
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IGrupoAlimentarDAO
 */
public class GrupoAlimentarDAOImpl extends BaseDAOImpl<GrupoAlimentar>
		implements IGrupoAlimentarDAO {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 972588722465134176L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;

	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger
			.getLogger(GrupoAlimentarDAOImpl.class);

	@Override
	public GrupoAlimentar findById(int id) {
		logger.info("Procurando o grupo alimentar de id = " + id);
		GrupoAlimentar grupo = null;

		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select nome from grupoalimentar where id=? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				grupo = new GrupoAlimentar();
				grupo.setId(id);
				grupo.setNome(rs.getString("nome"));
			}

			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na procura de grupo alimentar por id: " + e.getMessage(), e.getCause());
		}

		return grupo;
	}

	@Override
	public List<GrupoAlimentar> findByName(String name) {
		return null;
	}

	@Override
	public void delete(GrupoAlimentar entity) {
		logger.info("Deletando o grupo alimentar \"" + entity.getNome() + "\"");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" delete from grupoalimentar where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, entity.getId());
			
			stmt.executeUpdate();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro na deleção do grupo alimentar: " + e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<GrupoAlimentar> listAll() {
		logger.info("Listando todos os grupos alimentares");
		
		GrupoAlimentar g = null;
		List<GrupoAlimentar> grupos = new ArrayList<GrupoAlimentar>();
		
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select id, nome from grupoalimentar ");
			
			stmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				g = new GrupoAlimentar();
				g.setId(rs.getInt("id"));
				g.setNome(rs.getString("nome"));
				
				grupos.add(g);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na listagem de grupos alimentares: " + e.getMessage(), e.getCause());
		}
		
		return grupos;
	}

	@Override
	public GrupoAlimentar save(GrupoAlimentar entity) {
		logger.info("Inserindo um novo grupo alimentar");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into grupoalimentar values (?, ?); ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, getNumEntities(entity));
			stmt.setString(2, entity.getNome());
			
			stmt.execute();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro no salvamento do grupo alimentar: " + e.getMessage(), e.getCause());
		}
		
		return entity;
	}

	@Override
	public void update(GrupoAlimentar entity) {
		logger.info("Atualizando o grupo alimentar \"" + entity.getNome() + "\"");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update grupoalimentar set nome=? where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, entity.getNome());
			stmt.setInt(2, entity.getId());
			
			stmt.executeUpdate();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro na atualizacao do grupo alimentar: " + e.getMessage(), e.getCause());
		}
	}
}
