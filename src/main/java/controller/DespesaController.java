package controller;

import model.vo.DespesaVO;
import model.vo.UsuarioVO;
import model.bo.*;

public class DespesaController {
	
	DespesaBO despBO = new DespesaBO();
	
	public double calcularDescontoTotalController(UsuarioVO userOnline) {
		
		return despBO.calcularDescontoTotalBO(userOnline);
	}

	public DespesaVO consultarDespesaController(String string, UsuarioVO userOnline) {
		
		return despBO.consultarDespesaBO(string, userOnline);
	}

	public DespesaVO inserirDespesaController(DespesaVO despesa) {
		
		return despBO.inserirDespesaBO(despesa);
	}

	public boolean removerDespesaController(UsuarioVO userLogado, String text) {
		
		return despBO.removerDespController(userLogado, text);
	}

}
