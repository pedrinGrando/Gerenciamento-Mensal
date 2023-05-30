package controller;

import model.vo.EnderecoVO;
import model.bo.*;

public class EnderecoController {

	EnderecoBO enderecoBO = new EnderecoBO();
	
	public EnderecoVO cadastrarEnderecoController(EnderecoVO endereco) {
	
		return enderecoBO.cadastrarEnderecoBO(endereco);
	}

}
