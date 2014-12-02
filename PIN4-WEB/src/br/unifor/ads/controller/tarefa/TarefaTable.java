package br.unifor.ads.controller.tarefa;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import br.unifor.ads.controller.GenericTable;
import br.unifor.ads.entidades.Projeto;
import br.unifor.ads.entidades.Tarefa;
import br.unifor.ads.entidades.Usuario;

public class TarefaTable extends GenericTable<Tarefa> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6295222276865277149L;
	
	//UsuarioDAO usuDao;
	//ProjetoDAO projDao;
	
	public TarefaTable() throws SQLException{
		//usuDao = new UsuarioDAO();
		//projDao = new ProjetoDAO();
	}
	
	private boolean tarResp;
	private boolean projResp;
	private boolean tarRespGeral;
	private boolean projRespGeral;
	private List<Usuario> listaDeUsuarios;// = usuDao.listarUsuario();
	private List<Projeto> listaDeProjetos;// = projDao.listarProjeto();

	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}
	public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}
	public List<Projeto> getListaDeProjetos() {
		return listaDeProjetos;
	}
	public void setListaDeProjetos(List<Projeto> listaDeProjetos) {
		this.listaDeProjetos = listaDeProjetos;
	}
	public boolean isProjResp() {
		return projResp;
	}
	public void setProjResp(boolean projResp) {
		this.projResp = projResp;
	}
	public boolean isTarResp() {
		return tarResp;
	}
	public void setTarResp(boolean tarResp) {
		this.tarResp = tarResp;
	}
	public boolean isTarRespGeral() {
		return tarRespGeral;
	}
	public void setTarRespGeral(boolean tarRespGeral) {
		this.tarRespGeral = tarRespGeral;
	}
	public boolean isProjRespGeral() {
		return projRespGeral;
	}
	public void setProjRespGeral(boolean projRespGeral) {
		this.projRespGeral = projRespGeral;
	}
}
