package controller;

import model.vo.EnderecoVO;
import model.vo.TabelaVO;
import model.vo.UsuarioVO;

import java.util.ArrayList;

import exceptions.CampoInvalidoException;
import model.bo.*;
import model.seletor.TabelaSeletor;

public class EnderecoController {

	EnderecoBO enderecoBO = new EnderecoBO();
	
	public EnderecoVO cadastrarEnderecoController(EnderecoVO endereco) throws CampoInvalidoException {
	
		validarCamposObrigatorios(endereco);
		
		return enderecoBO.cadastrarEnderecoBO(endereco);
	}

	private void validarCamposObrigatorios(EnderecoVO endereco) throws CampoInvalidoException {

       String mensagemValidacao = "";
		
		mensagemValidacao += validarString(endereco.getCep(), "CEP");
		mensagemValidacao += validarString(endereco.getLogradouro(), "Rua");
		mensagemValidacao += validarString(endereco.getBairro(), "Bairro");
		mensagemValidacao += validarString(endereco.getLocalidade(), "Cidade");
		mensagemValidacao += validarString(endereco.getUf(), "Estado");
		
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
