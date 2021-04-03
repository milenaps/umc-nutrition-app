package com.nutri.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.nutri.java.commons.IJdbcDAO;
import com.nutri.java.commons.impl.BaseDAOImpl;
import com.nutri.java.commons.impl.JdbcDAOImpl;
import com.nutri.java.dao.IPacienteDAO;
import com.nutri.java.dao.IResultadoAnaliseDAO;
import com.nutri.java.model.ResultadoAnalise;

/**
 * Implementa a interface de persistencia da entidade ResultadoAnalise
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IResultadoAnaliseDAO
 */
public class ResultadoAnaliseDAOImpl extends BaseDAOImpl<ResultadoAnalise>
		implements IResultadoAnaliseDAO {

	private IPacienteDAO pacienteDAO;
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 5298368937489228946L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;
	
	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(ResultadoAnaliseDAOImpl.class);
	
	public void setPacienteDAO(IPacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public IPacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	@Override
	public ResultadoAnalise findById(int id) {
		logger.info("Recuperando o resultado de analise de id = " + id);
		ResultadoAnalise ra = null;
		jdbcDao = new JdbcDAOImpl();
		try {
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select id, datainicial, datafinal, informativo, posicaoranking, ");
			sql.append(" paciente from resultadoanalise where id=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ra = new ResultadoAnalise();
				ra.setId(rs.getInt("id"));
				ra.setDataInicial(java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("dataInicial"))));
				ra.setDataFinal(java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("dataFinal"))));
				ra.setInformativo(rs.getString("informativo"));
				ra.setPosicaoRanking(rs.getInt("posicaoRanking"));
				ra.setPaciente(this.pacienteDAO.findById(rs.getInt("paciente")));
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.fatal("Erro durante busca de resultado de analise por id: " + e.getMessage(), e.getCause());
		}
		
		return ra;
	}

	@Override
	public List<ResultadoAnalise> findByName(String name) {
		return null;
	}

	@Override
	public void delete(ResultadoAnalise entity) {
	}

	@Override
	public List<ResultadoAnalise> listAll() {
		return null;
	}

	@Override
	public ResultadoAnalise save(ResultadoAnalise entity) {
		logger.info("Inserindo um novo resultado de analise");
		jdbcDao = new JdbcDAOImpl();
		Connection con;
		PreparedStatement stmt;
		try {
			con = jdbcDao.getConnection();
			stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into resultadoanalise values (?, ?, ?, ?, ?, ?); ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, getNumEntities(entity));
			stmt.setDate(3, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(entity.getDataInicial())));
			stmt.setDate(4, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(entity.getDataFinal())));
			stmt.setString(6, entity.getInformativo());
			stmt.setInt(5, entity.getPosicaoRanking());
			stmt.setInt(2, entity.getPaciente().getId());
			
			stmt.execute();
			
			jdbcDao.closeConnection(con, stmt, null);
		} catch (Exception e) {
			logger.error("Erro no salvamento da categoria: " + e.getMessage(), e.getCause());
		}
		entity.setDataInicial((Date)entity.getDataInicial());
		entity.setDataFinal((Date)entity.getDataFinal());
		return entity;
	}

	@Override
	public void update(ResultadoAnalise entity) {
	}
	
	@Override
	public List<ResultadoAnalise> listByPaciente(int paciente) {
		logger.info("Listando resultados de analise pelo paciente de id = " + paciente);
		ResultadoAnalise ra = null;
		List<ResultadoAnalise> results = new ArrayList<ResultadoAnalise>();
		jdbcDao = new JdbcDAOImpl();
		try {
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select id, datainicial, datafinal, informativo, posicaoranking ");
			sql.append(" from resultadoanalise where paciente=? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, paciente);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ra = new ResultadoAnalise();
				ra.setId(rs.getInt("id"));
				ra.setDataInicial(java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("dataInicial"))));
				ra.setDataFinal(java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("dataFinal"))));
				ra.setInformativo(rs.getString("informativo"));
				ra.setPosicaoRanking(rs.getInt("posicaoRanking"));
				ra.setPaciente(this.pacienteDAO.findById(paciente));
				
				results.add(ra);
			}
			
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.fatal("Erro durante listagem de resultados de analise por paciente: " + e.getMessage(), e.getCause());
		}
		
		return results;
	}
}
