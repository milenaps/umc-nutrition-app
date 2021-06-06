package com.nutri.java.commons.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nutri.java.commons.IJdbcDAO;

/**
 * Implementa a interface generica de persistencia com JDBC
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IJdbcDAO
 */
public class JdbcDAOImpl implements IJdbcDAO {

	/**
	 * Variavel de conexao
	 */
	private Connection con;

	/**
	 * @see IJdbcDAO#getConnection()
	 */
	@Override
	public Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver").newInstance();
		con = DriverManager
				.getConnection("jdbc:postgresql://127.0.0.1:5432/nutri?user=postgres&password=aula");
		return con;
	}

	/**
	 * @see IJdbcDAO#closeConnection(Connection, Statement, ResultSet)
	 */
	@Override
	public void closeConnection(Connection con, Statement stmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
			}
		} catch (SQLException e) {
		}

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
		}

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
		}
	}

}
