package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.TabelaVO;
import model.vo.UsuarioVO;

public class TabelaDAO {

	public TabelaVO salvarDadosTabelaDAO(UsuarioVO userLogado, TabelaVO tabela) {
		
		String query ="INSERT INTO tabela (idusuario, mes, ano, totalrest, saldo) VALUES (?, ?, ?, ?, ?)";
		
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

}
