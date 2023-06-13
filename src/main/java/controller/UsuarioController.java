package controller;

import exceptions.CampoInvalidoException;
import exceptions.CpfJaUtilizadoException;
import model.bo.UsuarioBO;
import model.vo.UsuarioVO;
import model.dao.*;

public class UsuarioController {
	
	UsuarioBO userBO = new UsuarioBO();
	UsuarioDAO userDAO  = new UsuarioDAO();

	public UsuarioVO realizarLoginController(UsuarioVO userOnline) throws CampoInvalidoException {
		
		this.validarCamposObrigatoriosLogin(userOnline);
		
		return userBO.realizarLoginBO(userOnline);
	}

	private void validarCamposObrigatoriosLogin(UsuarioVO userOnline) throws CampoInvalidoException {
		
		String mensagemValidacao = "";
		
		if (userOnline.getLogin().trim().isEmpty() || userOnline.getLogin().trim().isBlank()) {
			
			mensagemValidacao = "Todos os campos são obrigatórios!";
		} 
		
		if (!userOnline.getSenha().trim().isEmpty() && userOnline.getSenha().trim().length() < 6) {
			
			mensagemValidacao = "A senha digitada é inválida!";
		}
		
		if (!userOnline.getSenha().trim().isEmpty() && userOnline.getSenha().trim().length() > 6) {
			
			mensagemValidacao = "A senha digitada é inválida!";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
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
		
		mensagemValidacao += validarEmail(u);
		
		if(u.getLogin().isEmpty() || u.getLogin() == null) {
			mensagemValidacao += " Login é obrigatório";
		}
		
		if(u.getSenha().isEmpty() || u.getSenha() == null) {
			mensagemValidacao += " Senha é obrigatória";
		}
		
		if(u.getSenha().length() < 6) {
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

	private String validarEmail(UsuarioVO u) {
		
		String validacao = "";
		
		if (u.getEmail().isEmpty() || u.getEmail().isBlank()) {
			
			validacao = "O Email é obrigatório!";
			
		} 
		
		return validacao;
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

	public UsuarioVO consultarUserPorNome(String text, String cpf) {
		
		return userDAO.consultarUsuarioPorNomeDAO(text, cpf);
	}

}
