package model.bo;

import model.vo.DespesaVO;
import model.vo.UsuarioVO;
import model.dao.*;

public class DespesaBO {
	
	DespesaDAO despDAO = new DespesaDAO();

	public double calcularDescontoTotalBO(UsuarioVO userOnline) {
		
		return despDAO.calcularDescontoTotalDAO(userOnline);
	}

	public DespesaVO consultarDespesaBO(String string, UsuarioVO userOnline) {
		
		return despDAO.consultarDespesaDAO(string, userOnline);
	}

	public DespesaVO inserirDespesaBO(DespesaVO despesa) {
		
		return despDAO.inserirDespesaDAO(despesa);
	}

	public boolean removerDespController(UsuarioVO userLogado, String text) {
		
		return despDAO.removerDespDAO(userLogado, text);
	}

}
