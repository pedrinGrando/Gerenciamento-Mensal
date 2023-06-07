package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.vo.DespesaVO;
import model.vo.TabelaVO;
import model.vo.UsuarioVO;

public class DespesaDAO {

	public double calcularDescontoTotalDAO(UsuarioVO userOnline) {
        double desconto = 0;
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		String query = "SELECT SUM(VALOR) as total "
				+ "FROM DESPESA "
				+ "WHERE idusuario = " + userOnline.getIdUsuario();
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				desconto = resultado.getDouble(1);
				
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método calcularDescontoTotalDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return desconto;
	
	}

	public DespesaVO consultarDespesaDAO(String string, UsuarioVO userOnline) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		DespesaVO despesa = new DespesaVO();
		
		String query = "SELECT iddespesa, idusuario, despnome, valor "
				+ "FROM DESPESA "
				+ "WHERE despnome like '" + string + "'"
				+ "AND idusuario = " +userOnline.getIdUsuario();
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				despesa.setIdDespesa(Integer.parseInt(resultado.getString(1)));
				despesa.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				despesa.setDespNome(resultado.getString(3));
				despesa.setValor(resultado.getDouble(4));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarDespesaDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return despesa;
		
		
		
	}

	public DespesaVO inserirDespesaDAO(DespesaVO despesa) {
		
		String query ="INSERT INTO despesa (idusuario, despnome, valor) VALUES (?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, despesa.getIdUsuario());
			pstmt.setString(2, despesa.getDespNome());
			pstmt.setDouble(3, despesa.getValor());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();	
			if(resultado.next()) {
				despesa.setIdDespesa(Integer.parseInt(resultado.getString(1)));
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método inserirDespesaDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return despesa;
	
	}

	public boolean removerDespDAO(UsuarioVO userLogado, String text) {

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		
		String query = "DELETE FROM despesa " 
				+ " WHERE idusuario = " + userLogado.getIdUsuario()
				+ " AND despnome = '" + text +"' ";
		
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método removerDespDAO");
			System.out.println("Erro: " + erro.getMessage());	
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
		
	}

	public List<DespesaVO> consultarTodasDAO(UsuarioVO userOnline) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<DespesaVO> despesasCadastradas = new ArrayList<>(); 
		

		String query = "SELECT iddespesa, idusuario, despnome, valor "
				+ "FROM despesa "
				+ "WHERE idusuario = " + userOnline.getIdUsuario();
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				DespesaVO despesa = new DespesaVO();
				despesa.setIdDespesa(Integer.parseInt(resultado.getString(1)));
				despesa.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				despesa.setDespNome(resultado.getString(3));
				despesa.setValor(Double.parseDouble(resultado.getString(4)));
				despesasCadastradas.add(despesa);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarTodasDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

          return despesasCadastradas;
		
	}

}
