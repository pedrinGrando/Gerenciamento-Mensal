package model.bo;

import model.vo.TabelaVO;
import model.vo.UsuarioVO;
import model.dao.*;

public class TabelaBO {

	TabelaDAO tabDAO = new TabelaDAO();
	
	
	public TabelaVO salvarDadosTabelaBO(UsuarioVO userLogado, TabelaVO tabela) {
		
		return tabDAO.salvarDadosTabelaDAO(userLogado, tabela);
	}

}
