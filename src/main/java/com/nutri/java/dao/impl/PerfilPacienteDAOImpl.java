package com.nutri.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.nutri.java.commons.IJdbcDAO;
import com.nutri.java.commons.impl.BaseDAOImpl;
import com.nutri.java.commons.impl.JdbcDAOImpl;
import com.nutri.java.dao.IPerfilPacienteDAO;
import com.nutri.java.model.PerfilPaciente;

/**
 * Implementa a interface de persistencia da entidade PerfilPaciente
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IPerfilPacienteDAO
 */
public class PerfilPacienteDAOImpl extends BaseDAOImpl<PerfilPaciente>
		implements IPerfilPacienteDAO {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -714860440108645374L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(UsuarioDAOImpl.class);
	
	@Override
	public PerfilPaciente findById(int id) {
		logger.info("Procurando pelo perfil de paciente de id = "+id);
		PerfilPaciente p = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select perfil from perfilpaciente where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				p = new PerfilPaciente();
				p.setId(id);
				p.setPerfil(rs.getString("perfil"));
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na busca de perfil de paciente por id: " + e.getMessage(), e.getCause());
		}
		
		return p;
	}

	@Override
	public List<PerfilPaciente> findByName(String name) {
		return null;
	}

	@Override
	public void delete(PerfilPaciente entity) {
	}

	@Override
	public List<PerfilPaciente> listAll() {
		return null;
	}

	@Override
	public PerfilPaciente save(PerfilPaciente entity) {
		return null;
	}

	@Override
	public void update(PerfilPaciente entity) {
	}
}
