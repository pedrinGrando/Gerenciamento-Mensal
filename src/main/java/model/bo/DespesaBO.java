package model.bo;

import model.vo.DespesaVO;
import model.vo.UsuarioVO;
import model.dao.*;

public class DespesaBO {
	
	DespesaDAO despDAO = new DespesaDAO();

	public double calcularDescontoTotalBO(UsuarioVO userOnline) {
		
		return despDAO.calcularDescontoTotalDAO(userOnline);
	}

	public DespesaVO consultarDespesaBO(DespesaVO despesaVO, UsuarioVO userOnline) {
		
		return despDAO.consultarDespesaDAO(despesaVO, userOnline);
	}

}
