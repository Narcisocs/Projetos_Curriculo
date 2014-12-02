package br.unifor.ads.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unifor.ads.DAO.UsuarioDAO;
import br.unifor.ads.entidades.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /*/**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     
    public LoginServlet() {
    	super();
    	System.out.println("constructor");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("doget");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("entrou no post");
		UsuarioDAO usuDao = null;
		Usuario user;
		try {
			usuDao = new UsuarioDAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(request.getParameter("Entrar") != null)
		{
			String email = request.getParameter("inputEmailUC");
			String senha = request.getParameter("inputPasswordUC");
			
			try {
				user = usuDao.checaLogin(email,senha);
				if (user != null) {
					//boolean tarResp = usuDao.checaResponsavel("tarefa", user.getId(), null, null);
					//boolean projResp = usuDao.checaResponsavel("projeto", user.getId(), null, null);
					request.getSession().setAttribute("userLogado", user);
					//request.getSession().setAttribute("tarResp", tarResp);
					//request.getSession().setAttribute("projResp", projResp);
					response.sendRedirect(request.getContextPath() + "/users");
					//RequestDispatcher disp = request.getRequestDispatcher("/users");
					//disp.forward(request, response);
				} else {
					request.getSession().removeAttribute("userLogado");
					request.getSession().setAttribute("failed", true);
					
					RequestDispatcher disp = request.getRequestDispatcher("/pages/login.jsp");
					disp.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}else if(request.getParameter("Criar") != null){	
			user = new Usuario();
			String email = request.getParameter("inputEmailNU");
			String nome = request.getParameter("inputNomeCompletoNU");
			String senha = request.getParameter("inputPasswordNU");
			
			
			user.setNome(nome);
			user.setEmail(email);
			user.setSenha(senha);
			user.setTipo_usuario("U");
			user.setAtivo("S");
			
			try {
				usuDao.inserirUsuario(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/users");
		}
	}

}
