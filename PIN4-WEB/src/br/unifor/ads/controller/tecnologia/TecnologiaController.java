package br.unifor.ads.controller.tecnologia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import br.unifor.ads.DAO.TecnologiaDAO;
import br.unifor.ads.controller.GenericControllerServlet;
import br.unifor.ads.controller.GenericTable;
import br.unifor.ads.entidades.Tecnologia;

/**
 * Servlet implementation class tecnologiaServlet
 */
@WebServlet("/tecs/*")
public class TecnologiaController extends GenericControllerServlet<Tecnologia>{
	private static final long serialVersionUID = 1L;
       
	public TecnologiaController() {
        super(Tecnologia.class);
        // TODO Auto-generated constructor stub
    }

	@Override
	protected GenericTable<Tecnologia> executeListAction(int pageIndex, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		GenericTable<Tecnologia> table = new GenericTable<Tecnologia>();
		TecnologiaDAO tecDao = new TecnologiaDAO();
		List<Tecnologia> listaDeTecnologias;
		
		listaDeTecnologias = tecDao.listarTecnologia();
		table.setCurrentPage(0);
		table.setTotalPages(1);
		table.setItems(listaDeTecnologias);
		table.setNewItem(null);
		table.setItemDeleted(false);
		table.setUpdatedItem(null);
		
		return table;
	}

	@Override
	protected GenericTable<Tecnologia> executeEditAction(Long id, HttpServletRequest request)
			throws SQLException {
		// TODO Auto-generated method stub
		TecnologiaDAO dao = new TecnologiaDAO();
		Tecnologia tecnologia = dao.buscarTecnologia(id);
		List<Tecnologia> lista = new ArrayList<Tecnologia>();
		lista.add(tecnologia);

		GenericTable<Tecnologia> table = new GenericTable<Tecnologia>();
		table.setCurrentPage(0);
		table.setItemDeleted(false);
		table.setItems(lista);
		table.setNewItem(null);
		table.setPageSize(0);
		table.setTotalPages(0);
		table.setUpdatedItem(null);
		
		return table;
	}

	@Override
	protected GenericTable<Tecnologia> executeAddAction(
			Map<String, String> properties, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		GenericTable<Tecnologia> table = new GenericTable<Tecnologia>();
		
		Tecnologia tecnologia = new Tecnologia();
		tecnologia.setNome(properties.get("nome"));
		tecnologia.setHome_page(properties.get("home_page"));
		tecnologia.setCodigo(properties.get("codigo"));
		
		TecnologiaDAO tecDao = new TecnologiaDAO();
		tecDao.inserirTecnologia(tecnologia);		
		
		table.setCurrentPage(0);
		table.setNewItem(tecnologia);
		table.setItems(tecDao.listarTecnologia());
		table.setPageSize(0);
		table.setTotalPages(0);
		table.setItemDeleted(false);
		table.setUpdatedItem(null);
		
		return table;
	}

	@Override
	protected GenericTable<Tecnologia> executeDeleteAction(Long id, HttpServletRequest request)
			throws SQLException {
		// TODO Auto-generated method stub
			Tecnologia tecnologia = new Tecnologia();
			tecnologia.setId(id);
			TecnologiaDAO dao = new TecnologiaDAO();
			dao.deletarTecnologia(tecnologia);
			
			GenericTable<Tecnologia> table = new GenericTable<Tecnologia>();
			table.setCurrentPage(0);
			table.setItemDeleted(true);
			table.setItems(dao.listarTecnologia());
			table.setNewItem(null);
			table.setPageSize(0);
			table.setTotalPages(0);
			table.setUpdatedItem(null);
		
		return table;
	}

	@Override
	protected GenericTable<Tecnologia> executeUpdateAction(
			Map<String, String> properties, Long id, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		TecnologiaDAO dao = new TecnologiaDAO();
		Tecnologia tecnologia = dao.buscarTecnologia(id);
		if (tecnologia != null) {
			tecnologia.setId(id);
			tecnologia.setNome(properties.get("nome"));
			tecnologia.setHome_page(properties.get("home_page"));
			tecnologia.setCodigo(properties.get("codigo"));
			
			dao.atualizaTecnologia(tecnologia);
		}
		
		GenericTable<Tecnologia> table = new GenericTable<Tecnologia>();
		table.setCurrentPage(0);
		table.setItemDeleted(false);
		table.setItems(dao.listarTecnologia());
		table.setNewItem(null);
		table.setPageSize(0);
		table.setTotalPages(0);
		table.setUpdatedItem(tecnologia);
		
		return table;
	}

	@Override
	protected GenericTable<Tecnologia> prepareForInsert() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
