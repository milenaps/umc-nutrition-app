package com.nutri.java.commons;

import java.util.logging.Logger;

/**
 * Gestor de logs da aplicacao
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see Logger
 */
public class LogManager extends Logger {

	/**
	 * Construtor da classe
	 * 
	 * @param name String
	 * @param resourceBundleName String
	 */
	protected LogManager(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}

}
