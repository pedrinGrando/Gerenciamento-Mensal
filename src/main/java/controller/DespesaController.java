package controller;

import model.vo.DespesaVO;
import model.vo.UsuarioVO;
import model.bo.*;
import java.util.ArrayList;
import java.util.List;
import model.dao.*;

public class DespesaController {
	
	DespesaBO despBO = new DespesaBO();
	DespesaDAO despDAO = new DespesaDAO();
	
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

	public List<DespesaVO> consultarTodosController(UsuarioVO userOnline) {
		
		return despDAO.consultarTodasDAO(userOnline);
	}

	public boolean atualizarDespController(DespesaVO despesaAtualizar) {
		
		return despBO.atualizarDespBO(despesaAtualizar);
	}

}
