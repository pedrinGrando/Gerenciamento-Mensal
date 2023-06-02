package model.bo;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {
	
	UsuarioDAO userDAO = new UsuarioDAO();
	
	public UsuarioVO realizarLoginBO(UsuarioVO userOnline) {
		UsuarioVO usuarioVO = new UsuarioVO();
		
		
		usuarioVO = userDAO.realizarLoginDAO(userOnline);
		
		return usuarioVO;
	}

	public boolean excluirContaBO(UsuarioVO userOnline) {
	
		return userDAO.excluirContaDAO(userOnline);
	}

	public boolean atualizarUsuarioBO(UsuarioVO userAtualizado) {
		
		return userDAO.atualizarUsuarioDAO(userAtualizado);
	}

}
