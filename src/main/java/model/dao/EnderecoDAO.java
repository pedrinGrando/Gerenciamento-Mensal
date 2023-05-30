package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.EnderecoVO;

public class EnderecoDAO {

	public EnderecoVO cadastrarEnderecoDAO(EnderecoVO endereco) {
		
		String query ="INSERT INTO endereco (rua, bairro, numero, cep, estado, cidade, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
				
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, endereco.getIdUsuario());
			pstmt.setString(2, endereco.getRua());
			pstmt.setString(3, endereco.getBairro());
			pstmt.setObject(4, endereco.getNumero());
			pstmt.setString(5, endereco.getCep());
			pstmt.setString(6, endereco.getEstado());
			pstmt.setString(7, endereco.getCidade());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();	
			if(resultado.next()) {
				endereco.setIdEndereco(Integer.parseInt(resultado.getString(1)));
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método cadastrarEnderecoDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return endereco;
		
	}

}
