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
import com.nutri.java.dao.IAlimentoDAO;
import com.nutri.java.dao.IFrequenciaAtividadeDAO;
import com.nutri.java.dao.ILoginDAO;
import com.nutri.java.dao.INutricionistaDAO;
import com.nutri.java.dao.IPacienteDAO;
import com.nutri.java.dao.IPerfilPacienteDAO;
import com.nutri.java.model.Atividade;
import com.nutri.java.model.HistoricoAlimentar;
import com.nutri.java.model.Nutricionista;
import com.nutri.java.model.Paciente;

/**
 * Implementa a interface de persistencia da entidade Paciente
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IPacienteDAO
 */
public class PacienteDAOImpl extends BaseDAOImpl<Paciente> implements
		IPacienteDAO {

	private IAlimentoDAO alimentoDAO;
	private ILoginDAO loginDAO;
	private IFrequenciaAtividadeDAO frequenciaAtividadeDAO;
	private INutricionistaDAO nutricionistaDAO;
	private IPerfilPacienteDAO perfilPacienteDAO;

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 9007334802163998463L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;

	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(PacienteDAOImpl.class);

	public void setAlimentoDAO(IAlimentoDAO alimentoDAO) {
		this.alimentoDAO = alimentoDAO;
	}

	public IAlimentoDAO getAlimentoDAO() {
		return alimentoDAO;
	}

	public void setLoginDAO(ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public ILoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setFrequenciaAtividadeDAO(
			IFrequenciaAtividadeDAO frequenciaAtividadeDAO) {
		this.frequenciaAtividadeDAO = frequenciaAtividadeDAO;
	}

	public IFrequenciaAtividadeDAO getFrequenciaAtividadeDAO() {
		return frequenciaAtividadeDAO;
	}

	public void setNutricionistaDAO(INutricionistaDAO nutricionistaDAO) {
		this.nutricionistaDAO = nutricionistaDAO;
	}

	public INutricionistaDAO getNutricionistaDAO() {
		return nutricionistaDAO;
	}

	public void setPerfilPacienteDAO(IPerfilPacienteDAO perfilPacienteDAO) {
		this.perfilPacienteDAO = perfilPacienteDAO;
	}

	public IPerfilPacienteDAO getPerfilPacienteDAO() {
		return perfilPacienteDAO;
	}

	public Paciente findById(int id) {
		logger.info("Procurando o paciente de id = " + id);
		Paciente p = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select u.nome, u.email, u.login, p.peso, p.sexo, p.altura, ");
			sql.append(" p.datanascto, p.perfil from paciente p, usuario u ");
			sql.append(" where p.usuario=u.id and p.id = ? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				p = new Paciente();
				p.setId(id);
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setPeso(rs.getDouble("peso"));
				p.setSexo(rs.getInt("sexo"));
				p.setAltura(rs.getDouble("altura"));
				p.setDataNascto(rs.getDate("datanascto"));
				p.setLogin(this.loginDAO.findById(rs.getInt("login")));
				p.setPerfil(this.perfilPacienteDAO
						.findById(rs.getInt("perfil")));
				p.setNutricionista(this.getNutricionista(p.getId()));
				p.setAtividades(this.listAtividades(p.getId()));
				p.setHistoricosAlimentares(this.listHistoricosAlimentares(p
						.getId()));
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na procura de paciente por id: "
					+ e.getMessage(), e.getCause());
		}

		return p;
	}

	public List<Paciente> findByName(String name) {
		logger.info("Procurando pacientes com nome igual a " + name);
		Paciente p = null;
		List<Paciente> pacientes = new ArrayList<Paciente>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select p.id, u.nome, u.email, u.login, p.sexo, p.altura, p.datanascto ");
			sql.append(" p.perfil, p.peso from paciente p, usuario u ");
			sql.append(" where p.usuario=u.id and upper(u.nome) like upper('%"+name+"%') ");

			stmt = con.prepareStatement(sql.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				p = new Paciente();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setPeso(rs.getDouble("peso"));
				p.setSexo(rs.getInt("sexo"));
				p.setAltura(rs.getDouble("altura"));
				p.setDataNascto(rs.getDate("datanascto"));
				p.setLogin(this.loginDAO.findById(rs.getInt("login")));
				p.setPerfil(this.perfilPacienteDAO
						.findById(rs.getInt("perfil")));
				p.setNutricionista(this.getNutricionista(p.getId()));
				p.setAtividades(this.listAtividades(p.getId()));
				p.setHistoricosAlimentares(this.listHistoricosAlimentares(p
						.getId()));

				pacientes.add(p);
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na pesquisa de pacientes por nome: "
					+ e.getMessage(), e.getCause());
		}

		return pacientes;
	}

	public Paciente findByUserId(int usuario) {
		logger.info("Procurando pelo paciente com id de usuario = " + usuario);
		Paciente p = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select p.id, u.nome, u.email, u.login, p.sexo, p.altura, p.datanascto, ");
			sql.append(" p.perfil, p.peso from perfilpaciente pp, paciente p, usuario u where ");
			sql.append(" pp.id=p.perfil and p.usuario=u.id and u.id=? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, usuario);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				p = new Paciente();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setPeso(rs.getDouble("peso"));
				p.setSexo(rs.getInt("sexo"));
				p.setAltura(rs.getDouble("altura"));
				p.setDataNascto(rs.getDate("datanascto"));
				p.setLogin(this.loginDAO.findById(rs.getInt("login")));
				p.setPerfil(this.perfilPacienteDAO
						.findById(rs.getInt("perfil")));
				p.setNutricionista(this.getNutricionista(p.getId()));
				p.setAtividades(this.listAtividades(p.getId()));
				p.setHistoricosAlimentares(this.listHistoricosAlimentares(p
						.getId()));
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na pesquisa pelo paciente por id de usuario: "
					+ e.getMessage(), e.getCause());
		}

		return p;
	}

	/**
	 * Recupera o nutricionista dado o id do paciente, se houver
	 * 
	 * @param paciente
	 *            int
	 * @return O nutricionista
	 */
	private Nutricionista getNutricionista(int paciente) {
		logger.info("Procurando o nutricionista do paciente de id = "
				+ paciente);
		Nutricionista n = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select n.id from nutricionista n, paciente p, paciente_nutricionista pn ");
			sql.append(" where n.id=pn.nutricionista and p.id=pn.paciente and p.id = ? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, paciente);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				n = new Nutricionista();
				n = this.nutricionistaDAO.findById(rs.getInt("id"));
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na busca de nutricionista por paciente: "
					+ e.getMessage(), e.getCause());
		}

		return n;
	}

	/**
	 * Lista as atividades dado o id do paciente
	 * 
	 * @param paciente
	 *            int
	 * @return A lista
	 */
	private List<Atividade> listAtividades(int paciente) {
		logger.info("Listando as atividades do paciente de id = " + paciente);
		Atividade a = null;
		List<Atividade> atividades = new ArrayList<Atividade>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select a.id, a.nome, f.frequenciadiaria from paciente p, atividade a, ");
			sql.append(" frequenciaatividade f, paciente_atividade pa where p.id=pa.paciente ");
			sql.append(" and a.id=pa.atividade and f.id=pa.frequencia and p.id=? ");

			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, paciente);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				a = new Atividade();
				a.setId(rs.getInt("id"));
				a.setNome(rs.getString("nome"));
				a.setFrequencia(this.frequenciaAtividadeDAO.findById(rs
						.getInt("frequenciadiaria")));

				atividades.add(a);
			}

			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na listagem de atividades: " + e.getMessage(), e
					.getCause());
		}

		return atividades;
	}

	/**
	 * Lista os historicos alimentares dados o id do paciente
	 * 
	 * @param paciente
	 *            int
	 * @return A lista
	 */
	public List<HistoricoAlimentar> listHistoricosAlimentares(int paciente) {
		logger.info("Listando os historicos alimentares do paciente de id = "
				+ paciente);
		HistoricoAlimentar h = null;
		List<HistoricoAlimentar> historicos = new ArrayList<HistoricoAlimentar>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select h.id, h.quantidade, h.data, h.hora, h.alimento ");
			sql.append(" from historicoalimentar h, paciente p where ");
			sql.append(" p.id=h.paciente and p.id=? ");

			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, paciente);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				h = new HistoricoAlimentar();
				h.setId(rs.getInt("id"));
				h.setQuantidade(rs.getDouble("quantidade"));
				h.setData(rs.getDate("data"));
				h.setHora(rs.getString("hora"));
				h.setAlimento(this.alimentoDAO.findById(rs.getInt("alimento")));

				historicos.add(h);
			}

			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na listagem de historicos: " + e.getMessage(), e
					.getCause());
		}

		return historicos;
	}

	@Override
	public List<HistoricoAlimentar> listHistoricosByPeriod(int paciente,
			Date dataDe, Date dataAte) {
		logger.info("Listando todos os historicos alimentares por periodo");

		HistoricoAlimentar h = null;
		List<HistoricoAlimentar> historicos = new ArrayList<HistoricoAlimentar>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select h.id, h.quantidade, h.data, h.hora, h.alimento from ");
			sql.append(" historicoalimentar h, paciente p where p.id=h.paciente ");
			sql.append(" and p.id=? and h.data >= ? and h.data <= ? ");

			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, paciente);
			stmt.setDate(2, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(dataDe)));
			stmt.setDate(3, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(dataAte)));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				h = new HistoricoAlimentar();
				h.setId(rs.getInt("id"));
				h.setQuantidade(rs.getDouble("quantidade"));
				h.setData(rs.getDate("data"));
				h.setHora(rs.getString("hora"));
				h.setAlimento(this.alimentoDAO.findById(rs.getInt("alimento")));

				historicos.add(h);
			}

			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na listagem de historicos por periodo: "
					+ e.getMessage(), e.getCause());
		}

		return historicos;
	}

	@Override
	public void delete(Paciente entity) {
	}

	@Override
	public List<Paciente> listAll() {
		logger.info("Listando todos os pacientes");

		Paciente p = null;
		List<Paciente> pacs = new ArrayList<Paciente>();

		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select u.id, u.nome, u.email, u.login, p.sexo, p.peso, ");
			sql.append(" p.altura, p.perfil, p,datanascto from usuario u, ");
			sql.append(" paciente p where u.id=p.usuario ");

			stmt = con.prepareStatement(sql.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				p = new Paciente();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setPeso(rs.getDouble("peso"));
				p.setSexo(rs.getInt("sexo"));
				p.setAltura(rs.getDouble("altura"));
				p.setDataNascto(rs.getDate("datanascto"));
				p.setLogin(this.loginDAO.findById(rs.getInt("login")));
				p.setPerfil(this.perfilPacienteDAO
						.findById(rs.getInt("perfil")));
				p.setNutricionista(this.getNutricionista(p.getId()));
				p.setAtividades(this.listAtividades(p.getId()));
				p.setHistoricosAlimentares(this.listHistoricosAlimentares(p
						.getId()));

				pacs.add(p);
			}
			jdbcDao.closeConnection(con, stmt, rs);

		} catch (Exception e) {
			logger.error("Erro na listagem de historicos: " + e.getMessage(), e
					.getCause());
		}

		return pacs;
	}

	@Override
	public Paciente save(Paciente entity) {
		return null;
	}

	@Override
	public void update(Paciente entity) {
	}
}
