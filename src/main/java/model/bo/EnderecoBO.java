package model.bo;

import model.vo.EnderecoVO;
import model.dao.*;

public class EnderecoBO {
	
	EnderecoDAO enderecoDAO = new EnderecoDAO();

	public EnderecoVO cadastrarEnderecoBO(EnderecoVO endereco) {
		
		return enderecoDAO.cadastrarEnderecoDAO(endereco);
	}

}
