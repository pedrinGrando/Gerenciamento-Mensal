package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.vo.DespesaVO;
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

	public DespesaVO consultarDespesaDAO(DespesaVO despesaVO, UsuarioVO userOnline) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		DespesaVO despesa = new DespesaVO();
		
		String query = "SELECT idespesa, idusuario, despnome, valor "
				+ "FROM DESPESA "
				+ "WHERE despnome like '" + despesaVO.getDespNome() + "'"
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

}
