package br.unifor.ads.controller.projeto;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import br.unifor.ads.controller.GenericTable;
import br.unifor.ads.entidades.Projeto;
import br.unifor.ads.entidades.Tecnologia;
import br.unifor.ads.entidades.Usuario;


public class ProjetoTable extends GenericTable<Projeto> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3242401362186246856L;
	
	//UsuarioDAO usuDao;
	//TecnologiaDAO tecDao;
	
	public ProjetoTable() throws SQLException{
		//usuDao = new UsuarioDAO();
		//tecDao = new TecnologiaDAO();
	}
	
	private List<Usuario> listaDeUsuario; // = usuDao.listarUsuario();
	private List<Tecnologia> listaDeTecnologia; // = tecDao.listarTecnologia();

	public List<Usuario> getListaDeUsuario() {
		return listaDeUsuario;
	}
	public void setListaDeUsuario(List<Usuario> listaDeUsuario) {
		this.listaDeUsuario = listaDeUsuario;
	}
	public List<Tecnologia> getListaDeTecnologia() {
		return listaDeTecnologia;
	}
	public void setListaDeTecnologia(List<Tecnologia> listaDeTecnologia) {
		this.listaDeTecnologia = listaDeTecnologia;
	}
	
	
}
