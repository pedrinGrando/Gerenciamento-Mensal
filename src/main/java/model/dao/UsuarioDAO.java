package model.dao;

import java.sql.Connection;
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
		
		String query = "SELECT u.idusuario, u.tipousuario, u.nome, u.cpf, u.datanasci, "
				+ "u.email, u.salariol, u.login, u.senha "
				+ "FROM USUARIO u "
				+ "WHERE u.login like '" + userLogado.getLogin() + "' "
				+ "AND u.senha = " + userLogado.getSenha();
		
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				userLogado.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				userLogado.setTipoUsuario(Integer.parseInt(resultado.getString(2)));
				userLogado.setNome(resultado.getString(3));
				userLogado.setCpf(resultado.getString(4));
				userLogado.setDataNasci(LocalDate.parse(resultado.getString(5),
						DateTimeFormatter.ofPattern("yyy-MM-dd")));
				userLogado.setEmail(resultado.getString(6));
				userLogado.setSalariol(Double.parseDouble(resultado.getString(7)));
				
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do realizarLoginDAO!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return userLogado;
	}

}
