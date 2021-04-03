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
import com.nutri.java.dao.IFrequenciaAtividadeDAO;
import com.nutri.java.model.FrequenciaAtividade;

/**
 * Implementa a interface de persistencia da entidade FrequenciaAtividade
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IFrequenciaAtividadeDAO
 */
public class FrequenciaAtividadeDAOImpl extends
		BaseDAOImpl<FrequenciaAtividade> implements IFrequenciaAtividadeDAO {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 2594859137298439797L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(AlimentoDAOImpl.class);
	
	@Override
	public FrequenciaAtividade findById(int id) {
		logger.info("Procurando pela frequencia de atividade de id = "+id);
		FrequenciaAtividade f = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select frequenciaadiaria from frequenciaatividade where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				f = new FrequenciaAtividade();
				f.setId(id);
				f.setFrequenciaDiaria(rs.getString("frequenciadiaria"));
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na busca de frequencia de atividade por id: " + e.getMessage(), e.getCause());
		}
		
		return f;
	}

	@Override
	public List<FrequenciaAtividade> findByName(String name) {
		return null;
	}

	@Override
	public void delete(FrequenciaAtividade entity) {
	}

	@Override
	public List<FrequenciaAtividade> listAll() {
		logger.info("Listando todos as frequencias");
		
		FrequenciaAtividade f = null;
		List<FrequenciaAtividade> frequencias = new ArrayList<FrequenciaAtividade>();
		
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select id, frequenciadiaria from frequenciaatividade ");
			
			stmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				f = new FrequenciaAtividade();
				f.setId(rs.getInt("id"));
				f.setFrequenciaDiaria(rs.getString("frequenciadiaria"));
				
				frequencias.add(f);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na listagem de frequencias: " + e.getMessage(), e.getCause());
		}
		
		return frequencias;
	}

	@Override
	public FrequenciaAtividade save(FrequenciaAtividade entity) {
		return null;
	}

	@Override
	public void update(FrequenciaAtividade entity) {
	}
}
