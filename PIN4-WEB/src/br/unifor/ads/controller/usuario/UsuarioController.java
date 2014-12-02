package br.unifor.ads.controller.usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import br.unifor.ads.DAO.UsuarioDAO;
import br.unifor.ads.controller.GenericControllerServlet;
import br.unifor.ads.controller.GenericTable;
import br.unifor.ads.entidades.Usuario;

/**
 * Servlet implementation class usuarioServlet
 */
@WebServlet("/users/*")
public class UsuarioController extends GenericControllerServlet<Usuario> {
	private static final long serialVersionUID = 1L;
       
    public UsuarioController() {
        super(Usuario.class);
        // TODO Auto-generated constructor stub
    }

	@Override
	protected GenericTable<Usuario> executeListAction(int pageIndex, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		GenericTable<Usuario> table = new GenericTable<Usuario>();
		UsuarioDAO usuDao;
			usuDao = new UsuarioDAO();
			List<Usuario> listaDeUsuarios;
			
			listaDeUsuarios = usuDao.listarUsuario();
			table.setCurrentPage(0);
			table.setTotalPages(1);
			table.setItems(listaDeUsuarios);
			table.setNewItem(null);
			table.setItemDeleted(false);
			table.setUpdatedItem(null);
			
		return table;
	}

	@Override
	protected GenericTable<Usuario> executeEditAction(Long id, HttpServletRequest request)
			throws SQLException {
		// TODO Auto-generated method stub
		UsuarioDAO dao;
		GenericTable<Usuario> table = new GenericTable<Usuario>();
		dao = new UsuarioDAO();
		Usuario usuario = dao.buscarUsuario(id);
		List<Usuario> lista = new ArrayList<Usuario>();
		lista.add(usuario);
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
	protected GenericTable<Usuario> executeAddAction(
			Map<String, String> properties, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		GenericTable<Usuario> table = new GenericTable<Usuario>();
		
		Usuario usuario = new Usuario();
		usuario.setNome(properties.get("nome"));
		usuario.setEmail(properties.get("email"));
		usuario.setSenha(properties.get("senha"));
		usuario.setTipo_usuario(properties.get("cbPerfil"));
		usuario.setAtivo(properties.get("ativo") != null ? "S" : "N");
		
		UsuarioDAO usuDao;
		
			usuDao = new UsuarioDAO();
			usuDao.inserirUsuario(usuario);		
			
			table.setCurrentPage(0);
			table.setNewItem(usuario);
			table.setItems(usuDao.listarUsuario());
			table.setPageSize(0);
			table.setTotalPages(0);
			table.setItemDeleted(false);
			table.setUpdatedItem(null);
			
		return table;
	}

	@Override
	protected GenericTable<Usuario> executeDeleteAction(Long id, HttpServletRequest request)
			throws SQLException {
		// TODO Auto-generated method stub
			UsuarioDAO dao;
			GenericTable<Usuario> table = new GenericTable<Usuario>();
				dao = new UsuarioDAO();
				dao.deletarUsuario(id);
				table.setCurrentPage(0);
				table.setItemDeleted(true);
				table.setItems(dao.listarUsuario());
				table.setNewItem(null);
				table.setPageSize(0);
				table.setTotalPages(0);
				table.setUpdatedItem(null);
			
		return table;
	}

	@Override
	protected GenericTable<Usuario> executeUpdateAction(
			Map<String, String> properties, Long id, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		UsuarioDAO dao;
		GenericTable<Usuario> table = new GenericTable<Usuario>();
			dao = new UsuarioDAO();
			Usuario usuario = dao.buscarUsuario(id);
			if (usuario != null) {
				usuario.setId(id);
				usuario.setNome(properties.get("nome"));
				usuario.setEmail(properties.get("email"));
				usuario.setSenha(properties.get("senha"));
				usuario.setTipo_usuario(properties.get("cbPerfil"));
				usuario.setAtivo(properties.get("ativo") != null ? "S" : "N");
				
				dao.atualizaUsuario(usuario);
			}
			
			table.setCurrentPage(0);
			table.setItemDeleted(false);
			table.setItems(dao.listarUsuario());
			table.setNewItem(null);
			table.setPageSize(0);
			table.setTotalPages(0);
			table.setUpdatedItem(usuario);
			
		return table;
	}

	@Override
	protected GenericTable<Usuario> prepareForInsert() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
