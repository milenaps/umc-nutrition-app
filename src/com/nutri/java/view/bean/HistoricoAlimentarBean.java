package com.nutri.java.view.bean;

import com.nutri.java.model.HistoricoAlimentar;
import com.nutri.java.services.IHistoricoAlimentarService;

public class HistoricoAlimentarBean {

	private HistoricoAlimentar historicoAlimentar;
	private static IHistoricoAlimentarService historicoAlimentarService;
	
	public HistoricoAlimentar getHistoricoAlimentar() {
		return historicoAlimentar;
	}

	public void setHistoricoAlimentar(HistoricoAlimentar historicoAlimentar) {
		this.historicoAlimentar = historicoAlimentar;
	}

	public IHistoricoAlimentarService getHistoricoAlimentarService() {
		return historicoAlimentarService;
	}

	@SuppressWarnings("static-access")
	public void setHistoricoAlimentarService(
			IHistoricoAlimentarService historicoAlimentarService) {
		this.historicoAlimentarService = historicoAlimentarService;
	}
}
