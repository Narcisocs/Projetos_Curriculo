package br.unifor.ads.entidades;

public class Tecnologia {
	Long id;
	String codigo;
	String nome;
	String home_page;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getHome_page() {
		return home_page;
	}
	public void setHome_page(String home_page) {
		this.home_page = home_page;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
