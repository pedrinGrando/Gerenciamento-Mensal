package controller;

import exceptions.CampoInvalidoException;
import exceptions.CpfJaUtilizadoException;
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

	public UsuarioVO cadastrarUsuarioController(UsuarioVO usuario) throws CpfJaUtilizadoException,
	    CampoInvalidoException {
		
		this.validarCamposObrigatorios(usuario);
			
         return userBO.cadastrarUsuarioBO(usuario);
	}

	private void validarCamposObrigatorios(UsuarioVO u) throws CampoInvalidoException {
		
          String mensagemValidacao = "";
		
		if(u.getNome() == null || u.getNome().trim().length() < 3) {
			mensagemValidacao += " Nome inválido \n";
		}
		
		mensagemValidacao += validarCpf(u);
		
		if(u.getLogin().isEmpty() || u.getLogin() == null) {
			mensagemValidacao += " Login é obrigatório";
		}
		
		if(u.getSenha().isEmpty() || u.getSenha() == null) {
			mensagemValidacao += " Senha é obrigatória";
		}
		
		if(u.getSenha().length() < 6 || !u.getSenha().contains("0123456789")) {
			mensagemValidacao += " Senha digitada inválida";
			
		} 
		
		if(u.getSalariol() <= 0 ) {
			mensagemValidacao += " O salário é obrigatório";
		}
		
		if(u.getDataNasci().getYear() > 2005 ) {
			mensagemValidacao += " É necessário ter 18 anos para o cadastro!";
		}
		
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
		
	}

	private String validarCpf(UsuarioVO u) {
	
         String validacao = "";
		
		if(u.getCpf() == null) {
			validacao += "CPF é obrigatório \n" ;
		}else {
			String cpfSemMascara = u.getCpf().replace(".", "");
			cpfSemMascara = u.getCpf().replace("-", "");
			u.setCpf(cpfSemMascara);
			if(u.getCpf().length() != 11) {
				validacao += "CPF deve possuir 11 dígitos\n" ;	
			}
		}
		
		return validacao;
	}

	public boolean atualizarUsuarioController(UsuarioVO userAtualizado) {
		
		return userBO.atualizarUsuarioBO(userAtualizado);
	}

}
