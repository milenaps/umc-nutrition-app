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
import com.nutri.java.dao.IAdministradorDAO;
import com.nutri.java.dao.ILoginDAO;
import com.nutri.java.model.Administrador;

/**
 * Implementa a interface de persistencia da entidade Administrador
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IAdministradorDAO
 */
public class AdministradorDAOImpl extends BaseDAOImpl<Administrador> implements IAdministradorDAO {

	private ILoginDAO loginDAO;
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -3506444219572328349L;
	
	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(AdministradorDAOImpl.class);
	
	public void setLoginDAO(ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public ILoginDAO getLoginDAO() {
		return loginDAO;
	}

	public Administrador findById(int id) {
		logger.info("Procurando o administrador de id = " + id);
		Administrador usu = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select u.nome, u.email, u.login from administrador a, usuario u ");
			sql.append(" where a.usuario=u.id and a.id=? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usu = new Administrador();
				usu.setId(id);
				usu.setNome(rs.getString("nome"));
				usu.setEmail(rs.getString("email"));
				usu.setLogin(this.loginDAO.findById(rs.getInt("login")));
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na procura de administrador por id: " + e.getMessage(), e.getCause());
		}
		
		return usu;
	}
	
	public List<Administrador> findByName(String name) {
		logger.info("Procurando administradores com nome igual a "+name);
		Administrador usu = null;
		List<Administrador> admins = new ArrayList<Administrador>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.id, u.nome, u.email, u.login from administrador a, usuario u ");
			sql.append(" where a.usuario=u.id and upper(u.nome) like upper('%"+name+"%') ");
			
			stmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				usu = new Administrador();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setEmail(rs.getString("email"));
				usu.setLogin(this.loginDAO.findById(rs.getInt("login")));
				
				admins.add((Administrador) usu);
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na pesquisa de administradores por nome: " + e.getMessage(), e.getCause());
		}
		
		return admins;
	}

	public Administrador findByUserId(int usuario) {
		logger.info("Procurando pelo administrador com id de usuario = " + usuario);
		Administrador usu = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select a.id, u.nome, u.email, u.login from administrador a, ");
			sql.append(" usuario u where a.usuario=u.id and u.id=? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, usuario);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usu = new Administrador();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setEmail(rs.getString("email"));
				usu.setLogin(this.loginDAO.findById(rs.getInt("login")));
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na pesquisa pelo administrador por id de usuario: "
					+ e.getMessage(), e.getCause());
		}
		
		return usu;
	}

	@Override
	public void delete(Administrador entity) {
	}

	@Override
	public List<Administrador> listAll() {
		logger.info("Listando todos os administradores");
		
		Administrador usu = null;
		List<Administrador> admins = new ArrayList<Administrador>();
		
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select u.id, u.nome, u.email, u.login from usuario u, ");
			sql.append(" administrador a where u.id=a.usuario ");
			
			stmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				usu = new Administrador();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setEmail(rs.getString("email"));
				usu.setLogin(this.loginDAO.findById(rs.getInt("login")));
				
				admins.add(usu);
			}
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na listagem de administradores: " + e.getMessage(), e.getCause());
		}
		
		return admins;
	}

	@Override
	public Administrador save(Administrador entity) {
		return null;
	}

	@Override
	public void update(Administrador entity) {
	}
	
	public void aprovarNutricionista(int nutricionista) {
    	logger.info("Aprovando o nutricionista de id = " + nutricionista);
    	try {
	    	jdbcDao = new JdbcDAOImpl();
	    	Connection conn = jdbcDao.getConnection();
	    	String sql = " update nutricionista set aprovado=1 where id=? ";
	    	PreparedStatement stmt = conn.prepareStatement(sql);
	    	stmt.setInt(1, nutricionista);
	    	stmt.executeUpdate();
    	} catch (Exception e) {
	    	logger.error("Erro ao aprovar o nutricionista de id = " + nutricionista);
    	}
	}
}
