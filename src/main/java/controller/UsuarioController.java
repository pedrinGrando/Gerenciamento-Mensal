package controller;

import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

public class UsuarioController {
	
	private UsuarioBO userBO;


	public UsuarioVO realizarLoginController(UsuarioVO userLogado) {
		// TODO Auto-generated method stub
		return userBO.realizarLoginBO(userLogado);
	}

}
