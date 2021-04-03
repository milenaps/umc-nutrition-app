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
import com.nutri.java.dao.IAlimentoDAO;
import com.nutri.java.dao.ICategoriaDAO;
import com.nutri.java.dao.IMedidaDAO;
import com.nutri.java.dao.INutrienteDAO;
import com.nutri.java.model.Alimento;
import com.nutri.java.model.Composicao;

/**
 * Implementa a interface de persistencia da entidade Alimento
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IAlimentoDAO
 */
public class AlimentoDAOImpl extends BaseDAOImpl<Alimento> implements IAlimentoDAO {

	private IMedidaDAO medidaDAO;
	
	private ICategoriaDAO categoriaDAO;
	
	private INutrienteDAO nutrienteDAO;
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -763391151234641316L;
	
	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(AlimentoDAOImpl.class);

	public void setMedidaDAO(IMedidaDAO medidaDAO) {
		this.medidaDAO = medidaDAO;
	}

	public IMedidaDAO getMedidaDAO() {
		return medidaDAO;
	}

	public void setCategoriaDAO(ICategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	public ICategoriaDAO getCategoriaDAO() {
		return categoriaDAO;
	}

	public void setNutrienteDAO(INutrienteDAO nutrienteDAO) {
		this.nutrienteDAO = nutrienteDAO;
	}

	public INutrienteDAO getNutrienteDAO() {
		return nutrienteDAO;
	}

	@Override
	public Alimento findById(int id) {
		logger.info("Procurando o alimento com id = "+id);
		Alimento alimento = null;
		
		jdbcDao = new JdbcDAOImpl();
		try {
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select nome, medida, categoria from alimento where id = ? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				alimento = new Alimento();
				alimento.setId(id);
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
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro ao procurar alimento por id: " + e.getMessage(), e.getCause());
		}
		
		return alimento;
	}

	@Override
	public List<Alimento> findByName(String name) {
		logger.info("Procurando alimentos com nome proximo a "+name);
		Alimento alimento = null;
		List<Alimento> alimentos = new ArrayList<Alimento>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select id, nome, medida, categoria from alimento where upper(nome) like upper('%"+name+"%') ");
			
			stmt = con.prepareStatement(sql.toString());
			
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
			logger.error("Erro na pesquisa de alimentos por nome: " + e.getMessage(), e.getCause());
		}
		
		return alimentos;
	}
	
	public List<Alimento> listPorCateg(int categ) {
		logger.info("Listando alimentos pela categoria de id = "+categ);
		Alimento alimento = null;
		List<Alimento> alimentos = new ArrayList<Alimento>();
		
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.id, a.nome, a.categoria, a.medida from alimento a, ");
			sql.append(" categoria c where a.categoria=c.id and c.id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, categ);
			
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
			logger.error("Erro na listagem de alimentos por categoria: " + e.getMessage(), e.getCause());
		}
		
		return alimentos;
	}

	@Override
	public void delete(Alimento entity) {
	}

	@Override
	public List<Alimento> listAll() {
		logger.info("Listando todos os alimentos");
		
		Alimento a = null;
		List<Alimento> alimentos = new ArrayList<Alimento>();
		
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			String sql = " select id, nome, categoria, medida from alimento ";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				a = new Alimento();
				a.setId(rs.getInt("id"));
				a.setNome(rs.getString("nome"));
				a.setCategoria(this.categoriaDAO.findById(rs.getInt("categoria")));
				a.setMedida(this.medidaDAO.findById(rs.getInt("medida")));
				
				PreparedStatement nutrienteStmt = null;
				List<Composicao> nutrientes = new ArrayList<Composicao>();
				Composicao c = null;
				
				StringBuffer nutrienteStr = new StringBuffer();
				nutrienteStr.append(" select id, nutriente, quantidade from ");
				nutrienteStr.append(" alimento_nutriente where alimento=? ");
				
				nutrienteStmt = con.prepareStatement(nutrienteStr.toString());
				nutrienteStmt.setInt(1, a.getId());
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
					a.setNutrientes(nutrientes);
				}
				
				alimentos.add(a);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na listagem de alimentos: " + e.getMessage(), e.getCause());
		}
		
		return alimentos;
	}

	@Override
	public Alimento save(Alimento entity) {
		return null;
	}

	@Override
	public void update(Alimento entity) {
	}
}
