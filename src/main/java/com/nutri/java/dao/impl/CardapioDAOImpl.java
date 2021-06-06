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
import com.nutri.java.dao.ICardapioDAO;
import com.nutri.java.dao.INutricionistaDAO;
import com.nutri.java.dao.IPacienteDAO;
import com.nutri.java.model.Cardapio;
import com.nutri.java.model.ItemCardapio;

public class CardapioDAOImpl extends BaseDAOImpl<Cardapio> implements ICardapioDAO {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 6984240181632381243L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	private IAlimentoDAO alimentoDAO;
	
	private INutricionistaDAO nutricionistaDAO;
	
	private IPacienteDAO pacienteDAO;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(CardapioDAOImpl.class);

	public IAlimentoDAO getAlimentoDAO() {
		return alimentoDAO;
	}

	public void setAlimentoDAO(IAlimentoDAO alimentoDAO) {
		this.alimentoDAO = alimentoDAO;
	}

	public void setNutricionistaDAO(INutricionistaDAO nutricionistaDAO) {
		this.nutricionistaDAO = nutricionistaDAO;
	}

	public INutricionistaDAO getNutricionistaDAO() {
		return nutricionistaDAO;
	}

	public void setPacienteDAO(IPacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public IPacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	@Override
	public Cardapio findById(int id) {
		return null;
	}

	@Override
	public List<Cardapio> findByName(String name) {
		return null;
	}
	
	@Override
	public List<Cardapio> listAll() {
		return null;
	}

	@Override
	public Cardapio save(Cardapio entity) {
		logger.info("Inserindo um novo cardapio");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			int id = getNumEntities(entity);
			
			String sql = " insert into cardapio values (?); ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			
			if (entity.getPaciente() != null && entity.getPaciente().getId() != null) {
				sql = " insert into paciente_cardapio values (((select count(*) from paciente_cardapio) + 1), ?, ?) ";
				stmt = con.prepareStatement(sql.toString());
				stmt.setInt(2, id);
				stmt.setInt(1, entity.getPaciente().getId());
				stmt.execute();
			}
			
			if (entity.getNutricionista() != null && entity.getNutricionista().getId() != null) {
				sql = " insert into nutricionista_cardapio values (((select count(*) from nutricionista_cardapio) + 1), ?, ?) ";
				stmt = con.prepareStatement(sql.toString());
				stmt.setInt(2, id);
				stmt.setInt(1, entity.getNutricionista().getId());
				stmt.execute();
			}
			
			for (ItemCardapio item : entity.getItens()) {
				sql = " insert into alimento_cardapio values (((select count(*) from alimento_cardapio) + 1), ?, ?, ?) ";
				stmt = con.prepareStatement(sql.toString());
				stmt.setInt(1, item.getAlimento().getId());
				stmt.setInt(2, id);
				stmt.setDouble(3, item.getQuantidade());
				stmt.execute();
			}
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro no salvamento do cardapio: " + e.getMessage(), e.getCause());
		}
		
		return entity;
	}
	
	@Override
	public void update(Cardapio entity) {
	}
	
	@Override
	public void delete(Cardapio entity) {
	}

	@Override
	public Cardapio findByNutricionista(int nutricionista) {
		logger.info("Procurando pelo cardapio do nutricionista de id = " + nutricionista);
		Cardapio c = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select c.id, pc.paciente, nc.nutricionista from cardapio c, nutricionista n, paciente_cardapio pc, ");
			sql.append(" nutricionista_cardapio nc where c.id=nc.cardapio and nc.nutricionista=n.id and n.id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, nutricionista);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				c = new Cardapio();
				c.setId(rs.getInt("id"));
				c.setPaciente(this.pacienteDAO.findById(rs.getInt("paciente")));
				c.setNutricionista(this.nutricionistaDAO.findById(rs.getInt("nutricionista")));
				c.setItens(this.listItens(c.getId()));
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na busca de cardapio por nutricionista: " + e.getMessage(), e.getCause());
		}
		
		return c;
	}

	@Override
	public Cardapio findByPaciente(int paciente) {
		logger.info("Procurando pelo cardapio do paciente de id = " + paciente);
		Cardapio c = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select c.id, pc.paciente, nc.nutricionista from cardapio c, paciente p, paciente_cardapio pc, ");
			sql.append(" nutricionista_cardapio nc where c.id=pc.cardapio and pc.paciente=p.id and p.id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, paciente);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				c = new Cardapio();
				c.setId(rs.getInt("id"));
				c.setPaciente(this.pacienteDAO.findById(rs.getInt("paciente")));
				c.setNutricionista(this.nutricionistaDAO.findById(rs.getInt("nutricionista")));
				c.setItens(this.listItens(c.getId()));
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na busca de cardapio por paciente: " + e.getMessage(), e.getCause());
		}
		
		return c;
	}
	
	private List<ItemCardapio> listItens(int cardapio) {
		logger.info("Procurando pelos itens do cardapio de id = " + cardapio);
		ItemCardapio i = null;
		List<ItemCardapio> itens = new ArrayList<ItemCardapio>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select i.id, i.alimento, i.quantidade from cardapio c, alimento_cardapio i ");
			sql.append(" where c.id=i.cardapio and c.id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, cardapio);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				i = new ItemCardapio();
				i.setId(rs.getInt("id"));
				i.setAlimento(this.alimentoDAO.findById(rs.getInt("alimento")));
				i.setQuantidade(rs.getDouble("quantidade"));
				
				itens.add(i);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na busca de cardapio por paciente: " + e.getMessage(), e.getCause());
		}
		
		return itens;
	}
}
