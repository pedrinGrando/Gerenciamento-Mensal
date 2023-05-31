package controller;

import model.vo.TabelaVO;
import model.vo.UsuarioVO;
import model.bo.*;

public class TabelaController {
	
	TabelaBO tabBO = new TabelaBO();

	public TabelaVO salvarDadosTabelaDAO(UsuarioVO userLogado, TabelaVO tabela) {
		
		return tabBO.salvarDadosTabelaBO(userLogado, tabela);
	}

	public TabelaVO consultarMesController(TabelaVO tabelaVO) {
		
		return tabBO.consultarMesBO(tabelaVO);
	}

}
