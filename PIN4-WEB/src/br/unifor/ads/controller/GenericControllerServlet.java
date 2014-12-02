package br.unifor.ads.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public  abstract class GenericControllerServlet<T> extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected abstract GenericTable<T> executeListAction(int pageIndex, HttpServletRequest request) throws SQLException;
    protected abstract GenericTable<T> executeEditAction(Long id, HttpServletRequest request) throws SQLException;
    protected abstract GenericTable<T> executeAddAction(Map<String, String> properties, HttpServletRequest request) throws SQLException;
    protected abstract GenericTable<T> executeDeleteAction(Long id, HttpServletRequest request) throws SQLException;
    protected abstract GenericTable<T> executeUpdateAction(Map<String, String> properties, Long id, HttpServletRequest request) throws SQLException;
    protected abstract GenericTable<T> prepareForInsert() throws SQLException;
    
    private final Class<T> type;

    public GenericControllerServlet(Class<T> type) {
        super(); 
    	this.type = type;
    }

    public Class<T> getMyType() {
        return this.type;
    }

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException {
    	String action = request.getPathInfo();
    	if(action != null && !"/".equals(action))
    		action = action.split("/")[1];
    	else 
    		action = "list";
    	
    	String jspSuffix = "";
    	int pageIndex = 1;
    	GenericTable<T> table = null;
    	
    	if ("list".equals(action)){
    		jspSuffix = "List";
    		if(request.getParameter("pageIndex") != null)
    			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
    		table = executeListAction(pageIndex, request);
    	}
    	else if("add".equals(action)){
    		jspSuffix = "Editar_Inserir";
    		table = prepareForInsert();
    	}
    	else if ("edit".equals(action)){
    		jspSuffix = "Editar_Inserir";
    		Long id = Long.parseLong(request.getParameter("id"));
    		table = executeEditAction(id,request);
    	}
    	else if ("new".equals(action)){
    		//foreach(property p in entity.property.names) 
    		//entity.setValue(p,request.getParameter(p));
    		jspSuffix = "List";
    		Map<String, String> properties = new HashMap<String, String>();
    		Enumeration<String> atributes = request.getParameterNames();
    		while(atributes.hasMoreElements()){
    			String elemento = atributes.nextElement();
    			properties.put(elemento, request.getParameter(elemento));
    		}
    		table = executeAddAction(properties,request);
    	}
    	else if ("delete".equals(action)){
    		jspSuffix = "List";
    		Long id = Long.parseLong(request.getParameter("id"));
    		table = executeDeleteAction(id, request);
    	}
    	else if ("update".equals(action)){
    		jspSuffix = "List";
    		Long id = Long.parseLong(request.getParameter("id"));
    		Map<String, String> properties = new HashMap<String, String>();
    		Enumeration<String> atributes = request.getParameterNames();
    		while(atributes.hasMoreElements()){
    			String elemento = atributes.nextElement();
    			properties.put(elemento, request.getParameter(elemento));
    		}
    		table = executeUpdateAction(properties, id, request);
    	}
    	else if("view".equals(action)){
    		jspSuffix = "Visualizar";
    		Long id = Long.parseLong(request.getParameter("id"));
    		table = executeUpdateAction(null, id, request);
    	}
    	else
    		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    	
    	request.setAttribute("table", table);
    	//String path = request.getContextPath() + "/pages/" + type.getSimpleName().toLowerCase() + jspSuffix + ".jsp";
    	//System.out.println(path);
    	//response.sendRedirect(path);
    	RequestDispatcher disp = request.getRequestDispatcher("/pages/" + type.getSimpleName().toLowerCase() + jspSuffix + ".jsp");
    	disp.forward(request, response);
    }
    
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			//System.out.println("entrou no post");
			this.processRequest(request, response);
	}*/
	
	//@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("entrou no service");
		
			try {
				this.processRequest(request, response);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
