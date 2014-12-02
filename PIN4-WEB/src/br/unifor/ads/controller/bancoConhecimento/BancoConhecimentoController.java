package br.unifor.ads.controller.bancoConhecimento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import br.unifor.ads.DAO.BancoConhecimentoDAO;
import br.unifor.ads.DAO.TecnologiaDAO;
import br.unifor.ads.DAO.UsuarioDAO;
import br.unifor.ads.controller.GenericControllerServlet;
import br.unifor.ads.controller.GenericTable;
import br.unifor.ads.entidades.BancoConhecimento;

/**
 * Servlet implementation class BancoConhecimentoController
 */
@WebServlet("/banco/*")
public class BancoConhecimentoController extends GenericControllerServlet<BancoConhecimento>  {
	
	public BancoConhecimentoController() {
		super(BancoConhecimento.class);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected GenericTable<BancoConhecimento> executeListAction(int pageIndex, HttpServletRequest request)
			throws SQLException {
		BancoConhecimentoTable table = new BancoConhecimentoTable();
		BancoConhecimentoDAO bcDao = new BancoConhecimentoDAO();
		TecnologiaDAO tecDao = new TecnologiaDAO();
		
		table.setCurrentPage(pageIndex);
		table.setItemDeleted(false);
		table.setItems(bcDao.listarBancoConhecimento());
		table.setListaDeTecnologias(tecDao.listarTecnologia());
		table.setNewItem(null);
		table.setPageSize(10);
		table.setTotalPages(0);
		table.setUpdatedItem(null);
		
		return table;
	}

	@Override
	protected GenericTable<BancoConhecimento> executeEditAction(Long id, HttpServletRequest request)
			throws SQLException {
		BancoConhecimentoTable table = new BancoConhecimentoTable();
		BancoConhecimentoDAO dao = new BancoConhecimentoDAO();
		TecnologiaDAO tecDao = new TecnologiaDAO();
		BancoConhecimento bc = dao.buscarBanco(id);
		List<BancoConhecimento> lista = new ArrayList<BancoConhecimento>();
		lista.add(bc);

		table.setListaDeTecnologias(tecDao.listarTecnologia());
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
	protected GenericTable<BancoConhecimento> executeAddAction(
			Map<String, String> properties, HttpServletRequest request) throws SQLException {
		GenericTable<BancoConhecimento> table = new GenericTable<BancoConhecimento>();
		
		BancoConhecimento bc = new BancoConhecimento();
		TecnologiaDAO tecDao = new TecnologiaDAO();
		UsuarioDAO usuDao;
			usuDao = new UsuarioDAO();
			BancoConhecimentoDAO bcDao = new BancoConhecimentoDAO();
			
			bc.setResumo(properties.get("resumo"));
			bc.setUsu_contribuidor(usuDao.buscarUsuario(Long.parseLong(properties.get("usuarioId"))));
			bc.setTecnologia(tecDao.buscarTecnologia(Long.parseLong(properties.get("cbTecnologia"))));
			bc.setDescricao(properties.get("descricao"));
			
			table.setCurrentPage(0);
			table.setNewItem(bc);
			table.setItems(bcDao.listarBancoConhecimento());
			table.setPageSize(0);
			table.setTotalPages(0);
			table.setItemDeleted(false);
			table.setUpdatedItem(null);
		return table;
	}
	
	@Override
	protected GenericTable<BancoConhecimento> executeDeleteAction(Long id, HttpServletRequest request)
			throws SQLException {
		return null;
	}

	@Override
	protected GenericTable<BancoConhecimento> executeUpdateAction(
			Map<String, String> properties, Long id, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		BancoConhecimentoDAO dao = new BancoConhecimentoDAO();
		BancoConhecimento bc = dao.buscarBanco(id);
		UsuarioDAO usuDao = new UsuarioDAO();
		TecnologiaDAO tecDao = new TecnologiaDAO();
		BancoConhecimentoTable table = new BancoConhecimentoTable();
		if (bc != null && properties != null) {
				bc.setId(id);
				bc.setResumo(properties.get("resumo"));
				bc.setDescricao(properties.get("descricao"));
				bc.setTecnologia(tecDao.buscarTecnologia(Long.parseLong(properties.get("cbTecnologia"))));
				bc.setUsu_contribuidor(usuDao.buscarUsuario(Long.parseLong(properties.get("user"))));
				
				dao.atualizaBanco(bc);
			}
		table.setCurrentPage(0);
		table.setItemDeleted(false);
		table.setItems(dao.listarBancoConhecimento());
		table.setNewItem(null);
		table.setPageSize(0);
		table.setTotalPages(0);
		table.setUpdatedItem(bc);
		
		return table;
	}

	@Override
	protected GenericTable<BancoConhecimento> prepareForInsert()
			throws SQLException {
		TecnologiaDAO tecDao = new TecnologiaDAO();
		BancoConhecimentoTable table = new BancoConhecimentoTable();
		table.setListaDeTecnologias(tecDao.listarTecnologia());
		return table;
	}

}
