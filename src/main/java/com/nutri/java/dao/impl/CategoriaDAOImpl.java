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
import com.nutri.java.dao.ICategoriaDAO;
import com.nutri.java.dao.IGrupoAlimentarDAO;
import com.nutri.java.model.Categoria;

/**
 * Implementa a interface de persistencia da entidade Categoria
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see ICategoriaDAO
 */
public class CategoriaDAOImpl extends BaseDAOImpl<Categoria> implements ICategoriaDAO {
	
	private IGrupoAlimentarDAO grupoAlimentarDAO;
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 7814531564581763661L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(CategoriaDAOImpl.class);
	
	public void setGrupoAlimentarDAO(IGrupoAlimentarDAO grupoAlimentarDAO) {
		this.grupoAlimentarDAO = grupoAlimentarDAO;
	}

	public IGrupoAlimentarDAO getGrupoAlimentarDAO() {
		return grupoAlimentarDAO;
	}

	@Override
	public Categoria save(Categoria entity) {
		logger.info("Inserindo uma nova categoria");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into categoria values (?, ?, ?); ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, getNumEntities(entity));
			stmt.setString(2, entity.getNome());
			stmt.setInt(3, entity.getGrupoAlimentar().getId());
			
			stmt.execute();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro no salvamento da categoria: " + e.getMessage(), e.getCause());
		}
		
		return entity;
	}
	
	@Override
	public Categoria findById(int id) {
		logger.info("Procurando pela categoria de id = " + id);
		Categoria categ = null;
		jdbcDao = new JdbcDAOImpl();
		try {
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select nome, grupo from categoria where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				categ = new Categoria();
				categ.setId(id);
				categ.setNome(rs.getString("nome"));
				categ.setGrupoAlimentar(this.grupoAlimentarDAO.findById(rs.getInt("grupo")));
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.fatal("Erro na busca de categoria por id: " + e.getMessage(), e.getCause());
		}
		
		return categ;
	}

	@Override
	public List<Categoria> findByName(String name) {
		return null;
	}
	
	public List<Categoria> listPorGrupo(int grupo) {
		logger.info("Listando categorias pelo grupo de id = " + grupo);
		Categoria categ = null;
		List<Categoria> categs = new ArrayList<Categoria>();
		jdbcDao = new JdbcDAOImpl();
		try {
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select c.id, c.nome from categoria c, grupoalimentar g ");
			sql.append(" where c.grupo=g.id and g.id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, grupo);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				categ = new Categoria();
				categ.setId(rs.getInt("id"));
				categ.setNome(rs.getString("nome"));
				categ.setGrupoAlimentar(this.grupoAlimentarDAO.findById(grupo));
				
				categs.add(categ);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.fatal("Erro durante listagem de categorias por grupo: " + e.getMessage(), e.getCause());
		}
		
		return categs;
	}

	@Override
	public void delete(Categoria entity) {
		logger.info("Deletando a categoria \"" + entity.getNome() + "\"");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" delete from categoria where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, entity.getId());
			
			stmt.executeUpdate();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro na deleção da categoria: " + e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Categoria> listAll() {
		logger.info("Listando todas as categorias");
		Categoria categ = null;
		List<Categoria> categs = new ArrayList<Categoria>();
		jdbcDao = new JdbcDAOImpl();
		try {
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select id, nome, grupo from categoria ");
			
			stmt = con.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				categ = new Categoria();
				categ.setId(rs.getInt("id"));
				categ.setNome(rs.getString("nome"));
				categ.setGrupoAlimentar(this.grupoAlimentarDAO.findById(rs.getInt("grupo")));
				
				categs.add(categ);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.fatal("Erro na listagem de categorias: " + e.getMessage(), e.getCause());
		}
		
		return categs;
	}

	@Override
	public void update(Categoria entity) {
		logger.info("Atualizando a categoria \"" + entity.getNome() + "\"");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update categoria set nome=?, grupo=? where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, entity.getNome());
			stmt.setInt(2, entity.getGrupoAlimentar().getId());
			stmt.setInt(3, entity.getId());
			
			stmt.executeUpdate();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro na atualizacao da categoria: " + e.getMessage(), e.getCause());
		}
	}
}
