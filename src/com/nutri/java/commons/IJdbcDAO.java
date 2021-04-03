package com.nutri.java.commons;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Interface de persistencia com JDBC
 * 
 * @author Milena Santos
 * @version 1.0
 */
public interface IJdbcDAO {

	/**
	 * Obtem conexao com o SGBD PostgreSQL
	 * 
	 * @return A conexao
	 * @throws Exception
	 *             Excecao lancada durante a tentativa de conexao
	 */
	public Connection getConnection() throws Exception;
	
	/**
	 * Fecha a conexao
	 * 
	 * @param con Connection
	 * @param stmt Statement
	 * @param rset ResultSet
	 */
	public void closeConnection(Connection con, Statement stmt, ResultSet rset);
}
