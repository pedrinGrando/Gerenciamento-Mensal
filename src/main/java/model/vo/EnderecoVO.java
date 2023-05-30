package model.vo;

public class EnderecoVO {

	private int idEndereco;
	private int idUsuario;
	private String rua;
	private String bairro;
	private int numero;
	private String cep;
	private String estado;
	private String cidade;
	
	public EnderecoVO(int idEndereco, int idUsuario, String rua, String bairro, int numero, String cep, String estado,
			String cidade) {
		super();
		this.idEndereco = idEndereco;
		this.idUsuario = idUsuario;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
		this.estado = estado;
		this.cidade = cidade;
	}

	public EnderecoVO() {
		super();
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	
}
