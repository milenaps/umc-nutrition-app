package com.nutri.java.commons;

/**
 * Classe para estilizar o lancamento de excecoes
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class NutriException extends Exception {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe
	 */
	public NutriException() {
		super();
	}

	/**
	 * Construtor da classe
	 * 
	 * @param message
	 *            String
	 */
	public NutriException(String message) {
		super(message);
	}

	/**
	 * Construtor da classe
	 * 
	 * @param message
	 *            String
	 * @param cause
	 *            Throwable
	 */
	public NutriException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe
	 * 
	 * @param cause
	 *            Throwable
	 */
	public NutriException(Throwable cause) {
		super(cause);
	}
}
