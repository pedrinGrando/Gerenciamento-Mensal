package model.bo;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {
	
	private UsuarioDAO userDAO;

	public UsuarioVO realizarLoginBO(UsuarioVO userLogado) {
		
		return userDAO.realizarLoginDAO(userLogado);
	}

}
