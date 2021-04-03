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
import com.nutri.java.dao.IMedidaDAO;
import com.nutri.java.model.Medida;

/**
 * Implementa a interface de persistencia da entidade Medida
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IMedidaDAO
 */
public class MedidaDAOImpl extends BaseDAOImpl<Medida> implements IMedidaDAO {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -8790492792148009647L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(UsuarioDAOImpl.class);
	
	@Override
	public Medida findById(int id) {
		logger.info("Procurando pela medida de id = "+id);
		Medida m = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select nome from medida where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				m = new Medida();
				m.setId(id);
				m.setNome(rs.getString("nome"));
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na busca de medida por id: " + e.getMessage(), e.getCause());
		}
		
		return m;
	}

	@Override
	public List<Medida> findByName(String name) {
		return null;
	}

	@Override
	public void delete(Medida entity) {
	}

	@Override
	public List<Medida> listAll() {
		logger.info("Listando todas as medidas");
		
		Medida m = null;
		List<Medida> medidas = new ArrayList<Medida>();
		
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select id, nome from medida ");
			
			stmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				m = new Medida();
				m.setId(rs.getInt("id"));
				m.setNome(rs.getString("nome"));
				
				medidas.add(m);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na listagem de medidas: " + e.getMessage(), e.getCause());
		}
		
		return medidas;
	}

	@Override
	public Medida save(Medida entity) {
		return null;
	}

	@Override
	public void update(Medida entity) {
	}
}
