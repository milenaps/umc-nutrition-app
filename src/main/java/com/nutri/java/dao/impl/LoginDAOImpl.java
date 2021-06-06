package com.nutri.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.nutri.java.commons.IJdbcDAO;
import com.nutri.java.commons.impl.BaseDAOImpl;
import com.nutri.java.commons.impl.JdbcDAOImpl;
import com.nutri.java.dao.ILoginDAO;
import com.nutri.java.dao.IPerfilAcessoDAO;
import com.nutri.java.model.Login;

/**
 * Implementa a interface de persistencia da entidade Login
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see ILoginDAO
 */
public class LoginDAOImpl extends BaseDAOImpl<Login> implements ILoginDAO {

	private IPerfilAcessoDAO perfilAcessoDAO;
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 8803905489865033001L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;

	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(NutricionistaDAOImpl.class);

	public IPerfilAcessoDAO getPerfilAcessoDAO() {
		return perfilAcessoDAO;
	}

	public void setPerfilAcessoDAO(IPerfilAcessoDAO perfilAcessoDAO) {
		this.perfilAcessoDAO = perfilAcessoDAO;
	}

	@Override
	public Login findById(int id) {
		logger.info("Procurando pelo login de id = "+id);
		
		Login l = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select login, senha from login where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				l = new Login();
				l.setId(id);
				l.setLogin(rs.getString("login"));
				l.setSenha(rs.getString("senha"));
				l.setPerfis(this.perfilAcessoDAO.listPerfisPorLogin(id));
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na busca de login por id: " + e.getMessage(), e.getCause());
		}
		
		return l;
	}

	@Override
	public List<Login> findByName(String name) {
		return null;
	}

	@Override
	public void delete(Login entity) {
	}

	@Override
	public List<Login> listAll() {
		return null;
	}

	@Override
	public Login save(Login entity) {
		return null;
	}

	@Override
	public void update(Login entity) {
	}
}
