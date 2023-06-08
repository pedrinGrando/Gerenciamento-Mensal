package model.seletor;

public class TabelaSeletor extends BaseSeletor {

	private String mes;
	private int ano;
	
	
	@Override
	public boolean temFiltro() {
		return (this.mes != null && this.mes.trim().length() > 0)
				|| (this.ano != 0);				
	}

	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}

}
