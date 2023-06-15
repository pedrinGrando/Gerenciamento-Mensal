package controller;

import model.vo.TabelaVO;
import model.vo.UsuarioVO;

import java.util.ArrayList;

import exceptions.CampoInvalidoException;
import model.bo.*;
import model.seletor.TabelaSeletor;
import model.geradorPlanilhas.*;

public class TabelaController {
	
	TabelaBO tabBO = new TabelaBO();

	public TabelaVO salvarDadosTabelaController(UsuarioVO userLogado, TabelaVO tabela) {
		
		return tabBO.salvarDadosTabelaBO(userLogado, tabela);
	}

	public TabelaVO consultarMesController(TabelaVO tabelaVO) {
		
		return tabBO.consultarMesBO(tabelaVO);
	}

	public ArrayList<TabelaVO> consultarTodasController(TabelaVO tabelaVO) {
		
		return tabBO.consultarTabelaBO(tabelaVO);
	}

	public ArrayList<TabelaVO> consultarComFiltros(TabelaSeletor tabSeletor) {
		// TODO Auto-generated method stub
		return tabBO.consultarComFiltrosBO(tabSeletor);
	}

	public int contarTotalRegistrosComFiltros(TabelaSeletor seletor) {
		
		return tabBO.contarTotalRegistrosComFiltros(seletor);
	}

	public String gerarPlanilha(ArrayList<TabelaVO> tabelas, String caminhoEscolhido) throws CampoInvalidoException {
		
		if(tabelas == null || caminhoEscolhido == null || caminhoEscolhido.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos");
		}
		
		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.gerarPlanilhaClientes(tabelas, caminhoEscolhido);
	}


}
