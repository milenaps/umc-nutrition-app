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
import com.nutri.java.dao.ILoginDAO;
import com.nutri.java.dao.INutricionistaDAO;
import com.nutri.java.model.Endereco;
import com.nutri.java.model.Nutricionista;
import com.nutri.java.model.Telefone;

/**
 * Implementa a interface de persistencia da entidade Nutricionista
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see INutricionistaDAO
 */
public class NutricionistaDAOImpl extends BaseDAOImpl<Nutricionista> implements
		INutricionistaDAO {

	private ILoginDAO loginDAO;
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -8183409165888383478L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;

	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(NutricionistaDAOImpl.class);

	public void setLoginDAO(ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public ILoginDAO getLoginDAO() {
		return loginDAO;
	}

	public Nutricionista findById(int id) {
		logger.info("Procurando o nutricionista de id = " + id);
		Nutricionista n = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select u.id, u.nome, u.email, u.login, n.aprovado, n.crn ");
			sql.append(" from nutricionista n, usuario u where n.usuario=u.id and n.id = ? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				n = new Nutricionista();
				n.setId(id);
				n.setNome(rs.getString("nome"));
				n.setEmail(rs.getString("email"));
				n.setLogin(this.loginDAO.findById(rs.getInt("login")));
				n.setAprovado(rs.getBoolean("aprovado"));
				n.setCrn(rs.getString("crn"));
				n.setEnderecos(this.listEnderecosByUser(rs.getInt("id")));
				n.setTelefones(this.listTelefonesByUser(rs.getInt("id")));
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na procura de nutricionista por id: " + e.getMessage(), e.getCause());
		}

		return n;
	}
	
	/**
	 * Lista os enderecos do usuario cujo id eh dado
	 * 
	 * @param usuario
	 *            int
	 * @return A lista
	 */
	private List<Endereco> listEnderecosByUser(int usuario) {
		logger.info("Listando os enderecos do usuario de id = " + usuario);
		Endereco end = null;
		List<Endereco> ends = new ArrayList<Endereco>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select e.id, e.logradouro, e.numero, e.complemento, e.bairro, ");
			sql.append(" e.cidade, e.cep, e.pais, e.uf from endereco e, usuario u ");
			sql.append(" where e.usuario=u.id and u.id=? ");

			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, usuario);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				end = new Endereco();
				end.setId(rs.getInt("id"));
				end.setLogradouro(rs.getString("logradouro"));
				end.setNumero(rs.getString("numero"));
				end.setComplemento(rs.getString("complemento"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("cidade"));
				end.setCep(rs.getInt("cep"));
				end.setPais(rs.getString("pais"));
				end.setUf(rs.getString("uf"));

				ends.add(end);
			}

			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro listando os enderecos do usuario "
					+ e.getMessage(), e.getCause());
		}

		return ends;
	}

	/**
	 * Lista os telefones do usuario cujo id eh dado
	 * 
	 * @param usuario
	 *            int
	 * @return A lista
	 */
	private List<Telefone> listTelefonesByUser(int usuario) {
		logger.info("Listando os telefones do usuario de id = " + usuario);
		Telefone tel = null;
		List<Telefone> tels = new ArrayList<Telefone>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select t.id, t.ddd, t.numero from telefone t, usuario u ");
			sql.append(" where t.usuario=u.id and u.id=? ");

			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, usuario);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				tel = new Telefone();
				tel.setId(rs.getInt("id"));
				tel.setDdd(rs.getInt("ddd"));
				tel.setNumero(rs.getInt("numero"));

				tels.add(tel);
			}

			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro listando os telefones do usuario "
					+ e.getMessage(), e.getCause());
		}

		return tels;
	}

	public List<Nutricionista> findByName(String name) {
		logger.info("Procurando nutricionistas com nome igual a " + name);
		Nutricionista n = null;
		List<Nutricionista> nutris = new ArrayList<Nutricionista>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select n.id, u.nome, u.email, u.login, n.aprovado, n.crn, u.id from ");
			sql.append(" nutricionista n, usuario u where n.usuario=u.id and upper(u.nome) like upper('%"+name+"%') ");

			stmt = con.prepareStatement(sql.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				n = new Nutricionista();
				n.setId(rs.getInt("id"));
				n.setNome(rs.getString("nome"));
				n.setEmail(rs.getString("email"));
				n.setLogin(this.loginDAO.findById(rs.getInt("login")));
				n.setAprovado(rs.getBoolean("aprovado"));
				n.setCrn(rs.getString("crn"));
				int id = rs.getInt("id");
				n.setEnderecos(this.listEnderecosByUser(id));
				n.setTelefones(this.listTelefonesByUser(id));
				
				nutris.add(n);
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na pesquisa de nutricionistas por nome: " + e.getMessage(), e.getCause());
		}

		return nutris;
	}

	public Nutricionista findByUserId(int usuario) {
		logger.info("Procurando pelo nutricionista com id de usuario = " + usuario);
		Nutricionista n = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select n.id, u.nome, u.email, u.login, n.crn, n.aprovado, u.id ");
			sql.append(" from nutricionista n, usuario u where n.usuario=u.id and u.id=? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, usuario);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				n = new Nutricionista();
				n.setId(rs.getInt("id"));
				n.setNome(rs.getString("nome"));
				n.setEmail(rs.getString("email"));
				n.setLogin(this.loginDAO.findById(rs.getInt("login")));
				n.setAprovado(rs.getBoolean("aprovado"));
				n.setCrn(rs.getString("crn"));
				int id = rs.getInt("id");
				n.setEnderecos(this.listEnderecosByUser(id));
				n.setTelefones(this.listTelefonesByUser(id));
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na pesquisa pelo nutricionista por id de usuario: "
					+ e.getMessage(), e.getCause());
		}
		
		return n;
	}

	public List<Nutricionista> listForApproval() {
		logger.info("Procurando nutricionistas pendentes de aprovação");
		Nutricionista n = null;
		List<Nutricionista> nutris = new ArrayList<Nutricionista>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select n.id, u.nome, u.email, u.login, n.aprovado, n.crn, u.id from ");
			sql.append(" nutricionista n, usuario u where n.usuario=u.id and aprovado=0");

			stmt = con.prepareStatement(sql.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				n = new Nutricionista();
				n.setId(rs.getInt("id"));
				n.setNome(rs.getString("nome"));
				n.setEmail(rs.getString("email"));
				n.setLogin(this.loginDAO.findById(rs.getInt("login")));
				n.setAprovado(rs.getBoolean("aprovado"));
				n.setCrn(rs.getString("crn"));
				int id = rs.getInt("id");
				n.setEnderecos(this.listEnderecosByUser(id));
				n.setTelefones(this.listTelefonesByUser(id));
				
				nutris.add(n);
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na pesquisa de nutricionistas para aprovação");
		}

		return nutris;
	}
	
	@Override
	public void delete(Nutricionista entity) {
	}

	@Override
	public List<Nutricionista> listAll() {
		logger.info("Listando todos os nutricionistas");
		
		Nutricionista usu = null;
		List<Nutricionista> nuts = new ArrayList<Nutricionista>();
		
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select n.id, u.nome, u.email, u.login, n.aprovado, n.crn, u.id from ");
			sql.append(" nutricionista n, usuario u where n.usuario=u.id ");
			
			stmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				usu = new Nutricionista();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setEmail(rs.getString("email"));
				usu.setLogin(this.loginDAO.findById(rs.getInt("login")));
				usu.setAprovado(rs.getBoolean("aprovado"));
				usu.setCrn(rs.getString("crn"));
				int id = rs.getInt("id");
				usu.setEnderecos(this.listEnderecosByUser(id));
				usu.setTelefones(this.listTelefonesByUser(id));
				
				nuts.add(usu);
			}
			jdbcDao.closeConnection(con, stmt, rs);
			
		} catch (Exception e) {
			logger.error("Erro na listagem de nutricionistas: " + e.getMessage(), e.getCause());
		}
		
		return nuts;
	}

	@Override
	public Nutricionista save(Nutricionista entity) {
		return null;
	}

	@Override
	public void update(Nutricionista entity) {
	}
}
