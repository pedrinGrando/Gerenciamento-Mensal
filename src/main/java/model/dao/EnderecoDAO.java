package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.vo.EnderecoVO;
import model.vo.UsuarioVO;

public class EnderecoDAO {

	public EnderecoVO cadastrarEnderecoDAO(EnderecoVO endereco) {
		
		String query ="INSERT INTO endereco (idusuario, rua, bairro, numero, cep, estado, cidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
				
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

	public boolean atualizarEndDAO(EnderecoVO endAtualizado) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		
		String query = "UPDATE endereco SET idusuario = " + endAtualizado.getIdUsuario()
				+ ", rua = '" + endAtualizado.getRua()
				+ "', bairro = '" + endAtualizado.getBairro()
				+ "', numero = '" + endAtualizado.getNumero()
				+ "', cep = '" + endAtualizado.getCep()
				+ "', estado = '" + endAtualizado.getEstado()
				+ "', cidade = '" + endAtualizado.getCidade()
				+ "' WHERE idusuario = " + endAtualizado.getIdUsuario();
		 
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método atualizarEndDAO");
			System.out.println("Erro: " + erro.getMessage());	
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
		
	}

	public EnderecoVO consultarEnderecoDAO(EnderecoVO endereco) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		String query = "SELECT u.idendereco, u.idusuario, u.rua, u.bairro, u.numero, "
				+ "u.cep, u.estado, u.cidade "
				+ "FROM ENDERECO u "
				+ "WHERE u.idusuario = " + endereco.getIdUsuario();
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				endereco.setIdEndereco(Integer.parseInt(resultado.getString(1)));
				endereco.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				endereco.setRua(resultado.getString(3));
				endereco.setBairro(resultado.getString(4));
				endereco.setNumero(Integer.parseInt(resultado.getString(5)));
				endereco.setCep(resultado.getString(6));
				endereco.setEstado(resultado.getString(7));
				endereco.setCidade(resultado.getString(8));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarEnderecoDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
				
		return endereco;
	}

}
