package controller;

import model.vo.EnderecoVO;
import model.vo.UsuarioVO;
import exceptions.CampoInvalidoException;
import model.bo.*;

public class EnderecoController {

	EnderecoBO enderecoBO = new EnderecoBO();
	
	public EnderecoVO cadastrarEnderecoController(EnderecoVO endereco) throws CampoInvalidoException {
	
		validarCamposObrigatorios(endereco);
		
		return enderecoBO.cadastrarEnderecoBO(endereco);
	}

	private void validarCamposObrigatorios(EnderecoVO endereco) throws CampoInvalidoException {

       String mensagemValidacao = "";
		
		mensagemValidacao += validarString(endereco.getCep(), "CEP");
		mensagemValidacao += validarString(endereco.getRua(), "Rua");
		mensagemValidacao += validarString(endereco.getBairro(), "Bairro");
		mensagemValidacao += validarString(endereco.getCidade(), "Cidade");
		mensagemValidacao += validarString(endereco.getEstado(), "Estado");
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
		
	}

	private String validarString(String texto, String string) {
		
        boolean valido = (texto != null) && !texto.trim().isEmpty();
		
		if(valido) {
			return "";
		}else {
			return 	"- " + string + "\n" ;
		}
	}

	public boolean atualizarEndController(EnderecoVO endAtualizado) {
		
		return enderecoBO.atualizarEnderecoBO(endAtualizado);
	}

	public EnderecoVO consultarEnderecoPorId(EnderecoVO endereco) {
		
		return enderecoBO.consultarEnderecoBO(endereco);
	}

}
