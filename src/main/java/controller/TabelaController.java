package controller;

import model.vo.TabelaVO;
import model.vo.UsuarioVO;

import java.util.ArrayList;

import model.bo.*;
import model.seletor.TabelaSeletor;

public class TabelaController {
	
	TabelaBO tabBO = new TabelaBO();

	public TabelaVO salvarDadosTabelaController(UsuarioVO userLogado, TabelaVO tabela) {
		
		return tabBO.salvarDadosTabelaBO(userLogado, tabela);
	}

	public TabelaVO consultarMesController(TabelaVO tabelaVO) {
		
		return tabBO.consultarMesBO(tabelaVO);
	}

	public ArrayList<TabelaVO> consultarTodasController(TabelaVO tabelaVO) {
		
		return tabBO.consultarTabelaBO(tabelaVO);
	}

	public ArrayList<TabelaVO> consultarComFiltros(TabelaSeletor tabSeletor) {
		// TODO Auto-generated method stub
		return tabBO.consultarComFiltrosBO(tabSeletor);
	}


}
