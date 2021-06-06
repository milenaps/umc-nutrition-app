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
import com.nutri.java.dao.IUsuarioDAO;
import com.nutri.java.model.Endereco;
import com.nutri.java.model.Telefone;
import com.nutri.java.model.Usuario;

/**
 * Implementa a interface de persistencia da entidade Usuario
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see BaseDAOImpl
 * @see IUsuarioDAO
 */
public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements IUsuarioDAO {

	private ILoginDAO loginDAO;

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 5776539522591224618L;

	/**
	 * Variavel de conexao
	 */
	private IJdbcDAO jdbcDao;

	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(UsuarioDAOImpl.class);

	public void setLoginDAO(ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public ILoginDAO getLoginDAO() {
		return loginDAO;
	}

	public Usuario findById(int id) {
		logger.info("Procurando o usuario de id = " + id);
		Usuario usu = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select nome, email, login from usuario where id = ? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usu = new Usuario();
				usu.setId(id);
				usu.setNome(rs.getString("nome"));
				usu.setEmail(rs.getString("email"));
				usu.setLogin(this.loginDAO.findById(rs.getInt("login")));
				usu.setEnderecos(this.listEnderecosByUser(usu.getId()));
				usu.setTelefones(this.listTelefonesByUser(usu.getId()));
			}

			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na procura de usuario por id: " + e.getMessage(),
					e.getCause());
		}

		return usu;
	}

	public List<Usuario> findByName(String name) {
		logger.info("Procurando por usuarios com nome igual a " + name);
		Usuario usu = null;
		List<Usuario> users = new ArrayList<Usuario>();
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select id, nome, email, login from usuario where upper(nome) like upper('%"+name+"%') ");

			stmt = con.prepareStatement(sql.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				usu = new Usuario();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setEmail(rs.getString("email"));
				usu.setLogin(this.loginDAO.findById(rs.getInt("login")));
				usu.setEnderecos(this.listEnderecosByUser(usu.getId()));
				usu.setTelefones(this.listTelefonesByUser(usu.getId()));

				users.add(usu);
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na pesquisa de usuario por nome "
					+ e.getMessage(), e.getCause());
		}

		return users;
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

	public Usuario findUserByLogin(String login) {
		logger.info("Procurando pelo usuario de login = " + login);

		Usuario usu = null;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select u.id, u.nome, u.email, u.login from usuario u, login l ");
			sql.append(" where u.login=l.id and l.login like ? ");

			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, login);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usu = new Usuario();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setEmail(rs.getString("email"));
				usu.setLogin(this.loginDAO.findById(rs.getInt("login")));
				usu.setEnderecos(this.listEnderecosByUser(usu.getId()));
				usu.setTelefones(this.listTelefonesByUser(usu.getId()));
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro na procura de usuario por login "
					+ e.getMessage(), e.getCause());
		}

		return usu;
	}

	public boolean validarLogin(String login, String senha) {
		logger.info("Validando o login " + login);
		boolean isValidLogin = false;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select login from login where login like ? ");
			sql.append(" and senha like ? ");

			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, login);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				isValidLogin = true;
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro durante a validacao de login " + e.getMessage(),
					e.getCause());
		}

		return isValidLogin;
	}

	public boolean validarUsuario(Usuario usuario) {
		logger.info("Validando o usuario \"" + usuario.getNome() + "\"");
		boolean isValidUser = false;
		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select n.aprovado from nutricionista n, usuario u where n.usuario=u.id and u.id = ? ");

			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, usuario.getId());
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				isValidUser = rs.getBoolean("aprovado");
			} else {
				isValidUser = true;
			}
			jdbcDao.closeConnection(con, stmt, rs);
		} catch (Exception e) {
			logger.error("Erro durante a validacao de usuario: " + e.getMessage(),
					e.getCause());
		}

		return isValidUser;
	}
	
	@Override
	public void delete(Usuario entity) {
	}

	@Override
	public List<Usuario> listAll() {
		logger.info("Listando todos os usuarios");

		Usuario usu = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			jdbcDao = new JdbcDAOImpl();
			Connection con = jdbcDao.getConnection();
			PreparedStatement stmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select id, nome, email, login from usuario ");

			stmt = con.prepareStatement(sql.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				usu = new Usuario();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setEmail(rs.getString("email"));
				usu.setLogin(this.loginDAO.findById(rs.getInt("login")));
				usu.setEnderecos(this.listEnderecosByUser(usu.getId()));
				usu.setTelefones(this.listTelefonesByUser(usu.getId()));

				usuarios.add(usu);
			}
			jdbcDao.closeConnection(con, stmt, rs);

		} catch (Exception e) {
			logger.error("Erro na listagem de usuarios: " + e.getMessage(), e
					.getCause());
		}
		return usuarios;
	}

	@Override
	public Usuario save(Usuario entity) {
		return null;
	}

	@Override
	public void update(Usuario entity) {
	}
}
