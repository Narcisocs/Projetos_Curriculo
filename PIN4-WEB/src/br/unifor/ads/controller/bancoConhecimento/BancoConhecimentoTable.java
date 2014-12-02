package br.unifor.ads.controller.bancoConhecimento;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import br.unifor.ads.DAO.TecnologiaDAO;
import br.unifor.ads.controller.GenericTable;
import br.unifor.ads.entidades.BancoConhecimento;
import br.unifor.ads.entidades.Tecnologia;

public class BancoConhecimentoTable extends GenericTable<BancoConhecimento> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//TecnologiaDAO tecDao;
	
	public BancoConhecimentoTable() throws SQLException{
		//tecDao = new TecnologiaDAO();
	}
	
	private List<Tecnologia> listaDeTecnologias; // = tecDao.listarTecnologia();

	public List<Tecnologia> getListaDeTecnologias() {
		return listaDeTecnologias;
	}

	public void setListaDeTecnologias(List<Tecnologia> listaDeTecnologias) {
		this.listaDeTecnologias = listaDeTecnologias;
	}
}
