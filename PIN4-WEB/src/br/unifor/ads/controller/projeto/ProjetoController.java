package br.unifor.ads.controller.projeto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import br.unifor.ads.DAO.ProjetoDAO;
import br.unifor.ads.DAO.TecnologiaDAO;
import br.unifor.ads.DAO.UsuarioDAO;
import br.unifor.ads.controller.GenericControllerServlet;
import br.unifor.ads.controller.GenericTable;
import br.unifor.ads.entidades.Projeto;
import br.unifor.ads.entidades.Tecnologia;
import br.unifor.ads.entidades.Usuario;

/**
 * Servlet implementation class projetoServlet
 */
@WebServlet("/projs/*")
public class ProjetoController extends GenericControllerServlet<Projeto> {
	private static final long serialVersionUID = 1L;
       
	public ProjetoController() {
        super(Projeto.class);
        // TODO Auto-generated constructor stub
    }

	@Override
	protected GenericTable<Projeto> executeListAction(int pageIndex, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		ProjetoTable table = new ProjetoTable();
		ProjetoDAO projDao = new ProjetoDAO();
		UsuarioDAO usuDao =  new UsuarioDAO();
		TecnologiaDAO tecDao = new TecnologiaDAO();
			
			table.setListaDeUsuario(usuDao.listarUsuario());
			table.setListaDeTecnologia(tecDao.listarTecnologia());
			table.setCurrentPage(0);
			table.setTotalPages(1);
			table.setItems(projDao.listarProjeto());
			table.setNewItem(null);
			table.setItemDeleted(false);
			table.setUpdatedItem(null);
		return table;
	}

	@Override
	protected GenericTable<Projeto> executeEditAction(Long id, HttpServletRequest request)
			throws SQLException {
		// TODO Auto-generated method stub
		ProjetoTable table = new ProjetoTable();
		ProjetoDAO dao = new ProjetoDAO();
		Projeto projeto = dao.buscarProjeto(id);
		UsuarioDAO usuDao = new UsuarioDAO();
		TecnologiaDAO tecDao = new TecnologiaDAO();

		table.setListaDeUsuario(usuDao.listarUsuario());
		table.setListaDeTecnologia(tecDao.listarTecnologia());

		List<Projeto> lista = new ArrayList<Projeto>();
		lista.add(projeto);

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
	protected GenericTable<Projeto> executeAddAction(
			Map<String, String> properties, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		ProjetoTable table = new ProjetoTable();
		
		Usuario usuario;
		Tecnologia tecnologia;
		UsuarioDAO usuDao = new UsuarioDAO();
		TecnologiaDAO tecDao = new TecnologiaDAO();
		
		usuario = usuDao.buscarUsuario(Long.parseLong(properties.get("cbResponsavel")));
		tecnologia = tecDao.buscarTecnologia(Long.parseLong(properties.get("cbTecnologia")));
		
		Projeto projeto = new Projeto();
		projeto.setNome(properties.get("codigo"));
		projeto.setTitulo(properties.get("titulo"));
		projeto.setUsu_resp(usuario);
		projeto.setTecnologia(tecnologia);
		projeto.setOrcamento(properties.get("orcamento"));
		
		ProjetoDAO projDao = new ProjetoDAO();
		projDao.inserirProjeto(projeto);
		
		table.setCurrentPage(0);
		table.setNewItem(projeto);
		table.setItems(projDao.listarProjeto());
		table.setPageSize(0);
		table.setTotalPages(0);
		table.setItemDeleted(false);
		table.setUpdatedItem(null);
		table.setListaDeUsuario(usuDao.listarUsuario());
		table.setListaDeTecnologia(tecDao.listarTecnologia());
		
		return table;
	}

	@Override
	protected GenericTable<Projeto> executeDeleteAction(Long id, HttpServletRequest request)
			throws SQLException {
		// TODO Auto-generated method stub
			Projeto projeto = new Projeto();
			projeto.setId(id);
			ProjetoDAO dao = new ProjetoDAO();
			dao.deletarProjeto(projeto);
			
			GenericTable<Projeto> table = new GenericTable<Projeto>();
			table.setCurrentPage(0);
			table.setItemDeleted(true);
			table.setItems(dao.listarProjeto());
			table.setNewItem(null);
			table.setPageSize(0);
			table.setTotalPages(0);
			table.setUpdatedItem(null);
		
		return table;
	}

	@Override
	protected GenericTable<Projeto> executeUpdateAction(
			Map<String, String> properties, Long id, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		ProjetoDAO dao = new ProjetoDAO();
		UsuarioDAO usuDao = new UsuarioDAO();
		TecnologiaDAO tecDao = new TecnologiaDAO();
		Projeto projeto = dao.buscarProjeto(id);
		if (projeto != null) {
			Usuario usuario = new Usuario();
			Tecnologia tecnologia = new Tecnologia();
			
			usuario = usuDao.buscarUsuario(Long.parseLong(properties.get("cbResponsavel")));
			tecnologia = tecDao.buscarTecnologia(Long.parseLong(properties.get("cbTecnologia")));
			
			projeto.setId(id);
			projeto.setNome(properties.get("codigo"));
			projeto.setTitulo(properties.get("titulo"));
			projeto.setUsu_resp(usuario);
			projeto.setTecnologia(tecnologia);
			projeto.setOrcamento(properties.get("orcamento"));

			dao.atualizaProjeto(projeto);
		}
		
		ProjetoTable table = new ProjetoTable();
		table.setCurrentPage(0);
		table.setItemDeleted(false);
		table.setItems(dao.listarProjeto());
		table.setNewItem(null);
		table.setPageSize(0);
		table.setTotalPages(0);
		table.setUpdatedItem(projeto);
		table.setListaDeUsuario(usuDao.listarUsuario());
		table.setListaDeTecnologia(tecDao.listarTecnologia());
		
		return table;
	}

	@Override
	protected GenericTable<Projeto> prepareForInsert() throws SQLException {
		// TODO Auto-generated method stub
		UsuarioDAO usuDao = new UsuarioDAO();
		TecnologiaDAO tecDao = new TecnologiaDAO();
		
		ProjetoTable table = new ProjetoTable();
		table.setListaDeUsuario(usuDao.listarUsuario());
		table.setListaDeTecnologia(tecDao.listarTecnologia());
		return table;
	}


}
