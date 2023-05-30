package controller;

import model.vo.DespesaVO;
import model.vo.UsuarioVO;
import model.bo.*;

public class DespesaController {
	
	DespesaBO despBO = new DespesaBO();
	
	public double calcularDescontoTotalController(UsuarioVO userOnline) {
		
		return despBO.calcularDescontoTotalBO(userOnline);
	}

	public DespesaVO consultarDespesaController(DespesaVO despesaVO, UsuarioVO userOnline) {
		
		return despBO.consultarDespesaBO(despesaVO, userOnline);
	}

}
