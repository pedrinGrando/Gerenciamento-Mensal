package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.seletor.TabelaSeletor;
import model.vo.TabelaVO;
import model.vo.UsuarioVO;

public class TabelaDAO {

	public TabelaVO salvarDadosTabelaDAO(UsuarioVO userLogado, TabelaVO tabela) {
		
		String query ="INSERT INTO tabelamensal (idusuario, mes, ano, totalrestmes, saldofinal) VALUES (?, ?, ?, ?, ?)";
		
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			pstmt.setInt(1, userLogado.getIdUsuario());
			pstmt.setString(2, tabela.getMes());
			pstmt.setInt(3, tabela.getAno());
			pstmt.setDouble(4, tabela.getTotalRest());
			pstmt.setDouble(5, tabela.getSaldoFinal());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();	
			if(resultado.next()) {
				tabela.setIdTabela(Integer.parseInt(resultado.getString(1)));
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método salvarDadosTabelaDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return tabela;
	
	}

	public TabelaVO consultarMesTabelaDAO(TabelaVO tabelaVO) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		TabelaVO tabela = new TabelaVO();
	
		String query = "SELECT idtabela, idusuario, mes, ano, totalrestmes, "
				+ "saldofinal "
				+ "FROM tabelamensal "
				+ "WHERE mes = '" + tabelaVO.getMes() +"'"
				+ "AND idusuario = " + tabelaVO.getIdUsuario();
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				tabela.setIdTabela(Integer.parseInt(resultado.getString(1)));
				tabela.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				tabela.setMes(resultado.getString(3));
				tabela.setAno(Integer.parseInt(resultado.getString(4)));
				tabela.setTotalRest(Double.parseDouble(resultado.getString(5)));
				tabela.setSaldoFinal(Double.parseDouble(resultado.getString(6)));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarMesTabelaDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return tabela;
	}

    public ArrayList<TabelaVO> consultarTabelaCompletaDAO(TabelaVO tabelaVO) {
			Connection conn = Banco.getConnection();
			Statement stmt = Banco.getStatement(conn);
			ResultSet resultado = null;
			ArrayList<TabelaVO> tabela_completaVO = new ArrayList<>(); 
	
	String query = "SELECT idtabela, idusuario, mes, ano, totalrestmes, "
			+ "saldofinal "
			+ "FROM tabelamensal "
			+ "WHERE idusuario = " + tabelaVO.getIdUsuario();
	
	try {
		resultado = stmt.executeQuery(query);
		while(resultado.next()) {
			TabelaVO tabela = new TabelaVO();
			tabela.setIdTabela(Integer.parseInt(resultado.getString(1)));
			tabela.setIdUsuario(Integer.parseInt(resultado.getString(2)));
			tabela.setMes(resultado.getString(3));
			tabela.setAno(Integer.parseInt(resultado.getString(4)));
			tabela.setTotalRest(Double.parseDouble(resultado.getString(5)));
			tabela.setSaldoFinal(Double.parseDouble(resultado.getString(6)));
			
			tabela_completaVO.add(tabela);
		}
	} catch (SQLException erro) {
		System.out.println("Erro ao executar a query do método consultarTabelaCompletaDAO");
		System.out.println("Erro: " + erro.getMessage());
	}finally {
		Banco.closeResultSet(resultado);
		Banco.closeStatement(stmt);
		Banco.closeConnection(conn);
	}

	return tabela_completaVO;
	
  }

	public ArrayList<TabelaVO> consultarComFiltrosDAO(TabelaSeletor tabSeletor) {
		
		ArrayList<TabelaVO> tabelas = new ArrayList<TabelaVO>();
		Connection conexao = Banco.getConnection();
		String sql = " select * from tabelamensal ";
		
		if(tabSeletor.temFiltro()) {
			sql = preencherFiltros(sql, tabSeletor);
		}
		
		//if(tabSeletor.temPaginacao()) {
			//sql += " LIMIT "  + tabSeletor.getLimite()
				// + " OFFSET " + tabSeletor.getOffset();  
		//}
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			
			while(resultado.next()) {
				TabelaVO clienteBuscado = montarTabelaComResultadoDoBanco(resultado);
				tabelas.add(clienteBuscado);
			}
			
		}catch (Exception e) {
			System.out.println("Erro ao buscar todas as tabelas. \n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return tabelas;
	
	}

	private TabelaVO montarTabelaComResultadoDoBanco(ResultSet resultado) throws SQLException {

		TabelaVO tabelaBuscada = new TabelaVO();
		tabelaBuscada.setIdTabela(resultado.getInt("idtabela"));
		tabelaBuscada.setIdUsuario(resultado.getInt("idusuario"));
		tabelaBuscada.setMes(resultado.getString("mes"));
		tabelaBuscada.setAno(resultado.getInt("ano"));
		tabelaBuscada.setTotalRest(resultado.getDouble("totalrestmes"));
		tabelaBuscada.setSaldoFinal(resultado.getDouble("saldofinal"));
		
		return tabelaBuscada;
		
	}

	private String preencherFiltros(String sql, TabelaSeletor tabSeletor) {
		
		boolean primeiro = true;
		
		if(tabSeletor.getMes() != null && !tabSeletor.getMes().trim().isEmpty()) {
			if(primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			
			sql += " mes LIKE '%" + tabSeletor.getMes() + "%'";
			primeiro = false;
		}
		
		if(tabSeletor.getAno() != 0 && !(tabSeletor.getAno() > 0)) {
			if(primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			sql += " ano = " + tabSeletor.getAno();
			primeiro = false;
		}
		
		
		return sql;
	}
    
}
