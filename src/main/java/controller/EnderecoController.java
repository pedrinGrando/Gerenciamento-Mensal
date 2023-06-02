package controller;

import model.vo.EnderecoVO;
import model.vo.UsuarioVO;
import model.bo.*;

public class EnderecoController {

	EnderecoBO enderecoBO = new EnderecoBO();
	
	public EnderecoVO cadastrarEnderecoController(EnderecoVO endereco) {
	
		return enderecoBO.cadastrarEnderecoBO(endereco);
	}

	public boolean atualizarEndController(EnderecoVO endAtualizado) {
		
		return enderecoBO.atualizarEnderecoBO(endAtualizado);
	}

	public EnderecoVO consultarEnderecoPorId(EnderecoVO endereco) {
		
		return enderecoBO.consultarEnderecoBO(endereco);
	}

}
