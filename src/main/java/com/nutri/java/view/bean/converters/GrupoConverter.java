package com.nutri.java.view.bean.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Conversor para objetos de tipo GrupoAlimentar
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see Converter
 */
public class GrupoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		return Integer.parseInt(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		return String.valueOf(value);
	}

}
