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
import com.nutri.java.dao.IPerfilAcessoDAO;
import com.nutri.java.model.PerfilAcesso;

/**
 * Implementa a interface de persistencia da entidade PerfilAcesso
 * 
 * @author Milena Santos
 * @version 1.0
 *
 * @see BaseDAOImpl
 * @see IPerfilAcessoDAO
 */
public class PerfilAcessoDAOImpl extends BaseDAOImpl<PerfilAcesso> implements IPerfilAcessoDAO {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 366273554975075361L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(UsuarioDAOImpl.class);
	
	@Override
	public PerfilAcesso findById(int id) {
		return null;
	}

	@Override
	public List<PerfilAcesso> findByName(String name) {
		return null;
	}

	@Override
	public void delete(PerfilAcesso entity) {
	}

	@Override
	public List<PerfilAcesso> listAll() {
		return null;
	}

	@Override
	public PerfilAcesso save(PerfilAcesso entity) {
		return null;
	}

	@Override
	public void update(PerfilAcesso entity) {
	}

	@Override
	public List<PerfilAcesso> listPerfisPorLogin(int login) {
		logger.info("Pesquisando os perfis de acesso do login de id = "+login);
		PerfilAcesso p = null;
		List<PerfilAcesso> perfis = new ArrayList<PerfilAcesso>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select p.id, p.role from perfilacesso p, ");
			sql.append(" login l, login_perfilacesso lp where p.id=lp.role ");
			sql.append(" and l.id=lp.login and l.id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, login);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				p = new PerfilAcesso();
				p.setId(rs.getInt("id"));
				p.setRole(rs.getString("role"));
				
				perfis.add(p);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na busca de perfis de acesso por login: " + e.getMessage(), e.getCause());
		}
		
		return perfis;
	}
}
