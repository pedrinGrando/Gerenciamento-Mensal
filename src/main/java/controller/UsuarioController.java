package controller;

import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

public class UsuarioController {
	
	UsuarioBO userBO = new UsuarioBO();

	public UsuarioVO realizarLoginController(UsuarioVO userOnline) {
		UsuarioVO usuarioVO = new UsuarioVO();
		
		usuarioVO = userBO.realizarLoginBO(userOnline);
		
		return usuarioVO;
	}

	public boolean excluirContaController(UsuarioVO userOnline) {
		
		return userBO.excluirContaBO(userOnline);
	}

	public UsuarioVO cadastrarUsuarioController(UsuarioVO usuario) {
			
         return userBO.cadastrarUsuarioBO(usuario);
	}

	public boolean atualizarUsuarioController(UsuarioVO userAtualizado) {
		
		return userBO.atualizarUsuarioBO(userAtualizado);
	}

}
