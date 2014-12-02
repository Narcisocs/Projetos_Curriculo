package br.unifor.ads.controller.tarefa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import br.unifor.ads.DAO.ProjetoDAO;
import br.unifor.ads.DAO.TarefaDAO;
import br.unifor.ads.DAO.UsuarioDAO;
import br.unifor.ads.controller.GenericControllerServlet;
import br.unifor.ads.controller.GenericTable;
import br.unifor.ads.entidades.Tarefa;
import br.unifor.ads.entidades.Usuario;

@WebServlet("/tars/*")
public class TarefaController extends GenericControllerServlet<Tarefa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TarefaController() {
		super(Tarefa.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected GenericTable<Tarefa> executeListAction(int pageIndex, HttpServletRequest request)
			throws SQLException {
		TarefaTable table = new TarefaTable();
		TarefaDAO tarDao = new TarefaDAO();
		UsuarioDAO usuDao;
			usuDao = new UsuarioDAO();
			ProjetoDAO projDao = new ProjetoDAO();
			
			table.setCurrentPage(pageIndex);
			table.setItemDeleted(false);
			table.setItems(tarDao.listarTarefa(((Usuario)request.getSession().getAttribute("userLogado")).getId()));
			table.setNewItem(null);
			table.setPageSize(5);
			table.setTotalPages(tarDao.getTotalPages());
			table.setUpdatedItem(null);
			table.setListaDeUsuarios(usuDao.listarUsuario());
			table.setListaDeProjetos(projDao.listarProjeto());
			table.setProjResp(usuDao.checaResponsavel("projeto", ((Usuario)request.getSession().getAttribute("userLogado")).getId(), null, null));
			table.setTarResp(usuDao.checaResponsavel("tarefa", ((Usuario)request.getSession().getAttribute("userLogado")).getId(), null, null));
		return table;
	}

	@Override
	protected GenericTable<Tarefa> executeEditAction(Long id, HttpServletRequest request)
			throws SQLException {
		TarefaTable table = new TarefaTable();
		TarefaDAO tarDao = new TarefaDAO();
		UsuarioDAO usuDao;
			usuDao = new UsuarioDAO();
			ProjetoDAO projDao = new ProjetoDAO();
			
			Tarefa tarefa = tarDao.buscarTarefa(id);
			List<Tarefa> lista = new ArrayList<Tarefa>();
			lista.add(tarefa);
			
			table.setCurrentPage(0);
			table.setItemDeleted(false);
			table.setItems(lista);
			table.setNewItem(null);
			table.setPageSize(10);
			table.setTotalPages(0);
			table.setUpdatedItem(null);
			table.setListaDeUsuarios(usuDao.listarUsuario());
			table.setListaDeProjetos(projDao.listarProjeto());
			table.setProjResp(usuDao.checaResponsavel("projeto", ((Usuario)request.getSession().getAttribute("userLogado")).getId(), null, null));
			table.setTarResp(usuDao.checaResponsavel("tarefa", ((Usuario)request.getSession().getAttribute("userLogado")).getId(), null, null));
		return table;
	}

	@Override
	protected GenericTable<Tarefa> executeAddAction(
			Map<String, String> properties, HttpServletRequest request) throws SQLException {
		GenericTable<Tarefa> table = new GenericTable<Tarefa>();
		Tarefa tarefa = new Tarefa();
		UsuarioDAO usuDao;
			usuDao = new UsuarioDAO();
			ProjetoDAO projDao = new ProjetoDAO();
			
			tarefa.setResumo(properties.get("resumo"));
			tarefa.setStatus(properties.get("situacao"));
			tarefa.setTempo_gasto(properties.get("tpGasto"));
			tarefa.setTempo_previsto(properties.get("tpPrevsito"));
			tarefa.setUsu_resp(usuDao.buscarUsuario(Long.parseLong(properties.get("cbResponsavel"))));
			tarefa.setProjeto(projDao.buscarProjeto(Long.parseLong(properties.get("cbProjeto"))));
			
			TarefaDAO tarDAO = new TarefaDAO();
			tarDAO.inserirTarefa(tarefa);
			
			table.setCurrentPage(0);
			table.setItemDeleted(false);
			table.setItems(tarDAO.listarTarefa(((Usuario)request.getSession().getAttribute("userLogado")).getId()));
			table.setNewItem(tarefa);
			table.setPageSize(0);
			table.setTotalPages(0);
			table.setUpdatedItem(null);
		return table;
	}

	@Override
	protected GenericTable<Tarefa> executeDeleteAction(Long id, HttpServletRequest request)
			throws SQLException {
		TarefaDAO dao = new TarefaDAO();
		dao.deletarTarefa(id);
		
		GenericTable<Tarefa> table = new GenericTable<Tarefa>();
		table.setCurrentPage(0);
		table.setItemDeleted(true);
		table.setItems(dao.listarTarefa(((Usuario)request.getSession().getAttribute("userLogado")).getId()));
		table.setNewItem(null);
		table.setPageSize(0);
		table.setTotalPages(0);
		table.setUpdatedItem(null);
		
		return table;
	}

	@Override
	protected GenericTable<Tarefa> executeUpdateAction(
			Map<String, String> properties, Long id, HttpServletRequest request) throws SQLException {
		TarefaDAO dao = new TarefaDAO();
		Tarefa tarefa = dao.buscarTarefa(id);
		UsuarioDAO usuDao = new UsuarioDAO();
		TarefaTable table = new TarefaTable();
		if (tarefa != null && properties != null) {
				ProjetoDAO projDao = new ProjetoDAO();
				
				tarefa.setId(id);
				tarefa.setResumo(properties.get("resumo"));
				tarefa.setStatus(properties.get("situacao"));
				tarefa.setTempo_gasto(properties.get("tpGasto"));
				tarefa.setTempo_previsto(properties.get("tpPrevsito"));
				tarefa.setUsu_resp(usuDao.buscarUsuario(Long.parseLong(properties.get("cbResponsavel"))));
				tarefa.setProjeto(projDao.buscarProjeto(Long.parseLong(properties.get("cbProjeto"))));
				
				TarefaDAO tarDAO = new TarefaDAO();
				tarDAO.atualizaTarefa(tarefa);
			}
		table.setCurrentPage(0);
		table.setItemDeleted(false);
		table.setItems(dao.listarTarefa(((Usuario)request.getSession().getAttribute("userLogado")).getId()));
		table.setNewItem(null);
		table.setPageSize(0);
		table.setTotalPages(0);
		table.setUpdatedItem(tarefa);
		table.setProjResp(usuDao.checaResponsavel(null, null, Long.parseLong(request.getParameter("tarefaId")), null));
		table.setTarResp(usuDao.checaResponsavel(null, null, null, Long.parseLong(request.getParameter("tarefaId"))));
		
		return table;
	}

	@Override
	protected GenericTable<Tarefa> prepareForInsert() throws SQLException {
		// TODO Auto-generated method stub
		UsuarioDAO usuDao = new UsuarioDAO();
		ProjetoDAO projDao = new ProjetoDAO();
		
		TarefaTable table = new TarefaTable();
		table.setListaDeProjetos(projDao.listarProjeto());
		table.setListaDeUsuarios(usuDao.listarUsuario());
		return table;
	}

}
