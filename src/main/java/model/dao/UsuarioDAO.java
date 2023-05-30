package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.vo.UsuarioVO;

public class UsuarioDAO {

	public UsuarioVO realizarLoginDAO(UsuarioVO userLogado) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		UsuarioVO usuarioVO = new UsuarioVO();
		
		String query = "SELECT * "
				+ "FROM USUARIO u "
				+ "WHERE u.login like '" + userLogado.getLogin() + "' "
				+ "AND u.senha = " + userLogado.getSenha();
		
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				usuarioVO.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				usuarioVO.setTipoUsuario(Integer.parseInt(resultado.getString(2)));
				usuarioVO.setNome(resultado.getString(3));
				usuarioVO.setCpf(resultado.getString(4));
				usuarioVO.setDataNasci(LocalDate.parse(resultado.getString(5),
						DateTimeFormatter.ofPattern("yyy-MM-dd")));
				usuarioVO.setEmail(resultado.getString(6));
				usuarioVO.setSalariol(Double.parseDouble(resultado.getString(7)));
				usuarioVO.setLogin(resultado.getString(8));
				usuarioVO.setSenha(resultado.getString(9));
				
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do realizarLoginDAO!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return usuarioVO;
	}
	
	public UsuarioVO cadastrarUsuarioDAO(UsuarioVO usuario) {
		String query ="INSERT INTO usuario (tipousuario, nome, cpf, datanasci, email, salariol, login, "
				+ " senha ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, usuario.getTipoUsuario());
			pstmt.setString(2, usuario.getNome());
			pstmt.setString(3, usuario.getCpf());
			pstmt.setObject(4, usuario.getDataNasci());
			pstmt.setString(5, usuario.getEmail());
			pstmt.setDouble(6, usuario.getSalariol());
			pstmt.setString(7, usuario.getLogin());
			pstmt.setString(8, usuario.getSenha());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();	
			if(resultado.next()) {
				usuario.setIdUsuario(Integer.parseInt(resultado.getString(1)));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método cadastrarUsuarioDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	
	}

	public boolean excluirContaDAO(UsuarioVO userOnline) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		
		String query = "DELETE FROM usuario "
		             + "WHERE idusuario = " + userOnline.getIdUsuario();
		
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;                                                                      
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método excluirContaDAO");
			System.out.println("Erro: " + erro.getMessage());	
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

}
