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
import com.nutri.java.dao.IMedidaDAO;
import com.nutri.java.dao.INutrienteDAO;
import com.nutri.java.dao.IPatologiaDAO;
import com.nutri.java.model.Alimento;
import com.nutri.java.model.Categoria;
import com.nutri.java.model.Composicao;
import com.nutri.java.model.GrupoAlimentar;
import com.nutri.java.model.Nutriente;
import com.nutri.java.model.Patologia;

/**
 * Implementa a interface de persistencia da entidade Patologia
 * 
 * @author Milena Santos
 * @version 1.0
 *
 * @see BaseDAOImpl
 * @see IPatologiaDAO
 */
public class PatologiaDAOImpl extends BaseDAOImpl<Patologia> implements IPatologiaDAO {

	private IMedidaDAO medidaDAO;
	
	private ICategoriaDAO categoriaDAO;
	
	private IGrupoAlimentarDAO grupoAlimentarDAO;
	
	private INutrienteDAO nutrienteDAO;
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -7111416948747817788L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(PatologiaDAOImpl.class);
	
	public IMedidaDAO getMedidaDAO() {
		return medidaDAO;
	}

	public void setMedidaDAO(IMedidaDAO medidaDAO) {
		this.medidaDAO = medidaDAO;
	}

	public ICategoriaDAO getCategoriaDAO() {
		return categoriaDAO;
	}

	public void setCategoriaDAO(ICategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	public IGrupoAlimentarDAO getGrupoAlimentarDAO() {
		return grupoAlimentarDAO;
	}

	public void setGrupoAlimentarDAO(IGrupoAlimentarDAO grupoAlimentarDAO) {
		this.grupoAlimentarDAO = grupoAlimentarDAO;
	}

	public void setNutrienteDAO(INutrienteDAO nutrienteDAO) {
		this.nutrienteDAO = nutrienteDAO;
	}

	public INutrienteDAO getNutrienteDAO() {
		return nutrienteDAO;
	}

	public Patologia findById(int id) {
		return null;
	}

	public List<Patologia> findByName(String name) {
		return null;
	}
	
	/**
	 * Lista as restricoes de alimentos da patologia informada
	 * 
	 * @param patologia
	 *            int
	 * @return A lista
	 */
	private List<Alimento> listRestrAlimentos(int patologia) {
		logger.info("Listando as restricoes de alimentos para a patologia de id = "+patologia);
		Alimento alimento = null;
		List<Alimento> alimentos = new ArrayList<Alimento>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.id, a.nome, a.categoria, a.medida from alimento a, patologia p, ");
			sql.append(" alimento_patologia ap where a.id=ap.alimento and p.id=ap.patologia and p.id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, patologia);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				alimento = new Alimento();
				alimento.setId(rs.getInt("id"));
				alimento.setNome(rs.getString("nome"));
				alimento.setCategoria(this.categoriaDAO.findById(rs.getInt("categoria")));
				alimento.setMedida(this.medidaDAO.findById(rs.getInt("medida")));
				
				PreparedStatement nutrienteStmt = null;
				List<Composicao> nutrientes = new ArrayList<Composicao>();
				Composicao c = null;
				
				StringBuffer nutrienteStr = new StringBuffer();
				nutrienteStr.append(" select id, nutriente, quantidade from ");
				nutrienteStr.append(" alimento_nutriente where alimento=? ");
				
				nutrienteStmt = con.prepareStatement(nutrienteStr.toString());
				nutrienteStmt.setInt(1, alimento.getId());
				ResultSet rset = nutrienteStmt.executeQuery();
				
				while (rset.next()) {
					c = new Composicao();
					c.setId(rset.getInt("id"));
					c.setNutriente(this.nutrienteDAO.findById(rset.getInt("nutriente")));
					c.setQuantidade(rset.getDouble("quantidade"));
					
					nutrientes.add(c);
				}
				jdbcDao.closeConnection(null, nutrienteStmt, rset);
				
				if (nutrientes.size() > 0) {
					alimento.setNutrientes(nutrientes);
				}
				
				alimentos.add(alimento);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na listagem de restricoes de alimentos: " + e.getMessage(), e.getCause());
		}
		
		return alimentos;
	}
	
	/**
	 * Lista as restricoes de categorias de alimentos da patologia informada
	 * 
	 * @param patologia
	 *            int
	 * @return A lista
	 */
	private List<Categoria> listRestrCategorias(int patologia) {
		logger.info("Listando as restricoes de categorias para a patologia de id = "+patologia);
		Categoria categ = null;
		List<Categoria> categs = new ArrayList<Categoria>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select c.id, c.nome, c.grupo from categoria c, patologia p, categoria_patologia cp ");
			sql.append(" where c.id=cp.categoria and p.id=cp.patologia and p.id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, patologia);
			
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
			logger.error("Erro na listagem de restricoes de categorias: " + e.getMessage(), e.getCause());
		}
		
		return categs;
	}

	/**
	 * Lista as restricoes de grupos alimentares da patologia informada
	 * 
	 * @param patologia
	 *            int
	 * @return A lista
	 */
	private List<GrupoAlimentar> listRestrGrupos(int patologia) {
		logger.info("Listando as restricoes de grupos para a patologia de id = "+patologia);
		GrupoAlimentar grupo = null;
		List<GrupoAlimentar> grupos = new ArrayList<GrupoAlimentar>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select g.id, g.nome from grupoalimentar g, patologia p, grupo_patologia gp ");
			sql.append(" where g.id=gp.grupo and p.id=gp.patologia and p.id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, patologia);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				grupo = new GrupoAlimentar();
				grupo.setId(rs.getInt("id"));
				grupo.setNome(rs.getString("nome"));
				
				grupos.add(grupo);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na listagem de restricoes de grupos alimentares: " + e.getMessage(), e.getCause());
		}
		
		return grupos;
	}

	/**
	 * Lista as restricoes de nutrientes da patologia informada
	 * 
	 * @param patologia
	 *            int
	 * @return A lista
	 */
	private List<Nutriente> listRestrNutrientes(int patologia) {
		logger.info("Listando as restricoes de nutrientes para a patologia de id = "+patologia);
		Nutriente nutriente = null;
		List<Nutriente> nutrientes = new ArrayList<Nutriente>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select n.id, n.nome from nutriente n, patologia p, nutriente_patologia np ");
			sql.append(" where n.id=np.nutriente and p.id=np.patologia and p.id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, patologia);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				nutriente = new Nutriente();
				nutriente.setId(rs.getInt("id"));
				nutriente.setNome(rs.getString("nome"));
				
				nutrientes.add(nutriente);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na listagem de restricoes de nutrientes: " + e.getMessage(), e.getCause());
		}
		
		return nutrientes;
	}

	@Override
	public void delete(Patologia entity) {
	}

	@Override
	public List<Patologia> listAll() {
		logger.info("Listando todos as patologias");
		
		Patologia p = null;
		List<Patologia> patologias = new ArrayList<Patologia>();
		
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select id, nome, descricao from patologia ");
			
			stmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				p = new Patologia();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setRestrAlimentos(this.listRestrAlimentos(p.getId()));
				p.setRestrCategorias(this.listRestrCategorias(p.getId()));
				p.setRestrGrupos(this.listRestrGrupos(p.getId()));
				p.setRestrNutrientes(this.listRestrNutrientes(p.getId()));
				
				patologias.add(p);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na listagem de patologias: " + e.getMessage(), e.getCause());
		}
		
		return patologias;
	}

	@Override
	public Patologia save(Patologia entity) {
		return null;
	}

	@Override
	public void update(Patologia entity) {
	}
}
