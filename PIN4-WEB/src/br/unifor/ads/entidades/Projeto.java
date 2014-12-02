package br.unifor.ads.entidades;

public class Projeto {
	Long id;
	String nome;
	Usuario usu_resp;
	Tecnologia tecnologia;
	//int usu_resp;
	//int tecnologia;
	String orcamento;
	String titulo;
	
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
	/*public int getUsu_resp() {
		return usu_resp;
	}
	public void setUsu_resp(int usu_resp) {
		this.usu_resp = usu_resp;
	}
	public int getTecnologia() {
		return tecnologia;
	}
	public void setTecnologia(int tecnologia) {
		this.tecnologia = tecnologia;
	}*/
	public Usuario getUsu_resp() {
		return usu_resp;
	}
	public void setUsu_resp(Usuario usu_resp) {
		this.usu_resp = usu_resp;
	}
	public Tecnologia getTecnologia() {
		return tecnologia;
	}
	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}
	public String getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(String orcamento) {
		this.orcamento = orcamento;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
