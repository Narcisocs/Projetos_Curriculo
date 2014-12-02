package br.unifor.ads.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unifor.ads.entidades.Projeto;
import br.unifor.ads.entidades.Tarefa;
import br.unifor.ads.entidades.Tecnologia;
import br.unifor.ads.entidades.Usuario;

public class TarefaDAO {
private Connection con;
	
	public TarefaDAO() throws SQLException{
		
	}
	
	public void inserirTarefa(Tarefa tarefa) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Insert into Tarefas(CD_TAREFA,DS_RESUMO,CD_USUARIO_RESP,DS_STATUS,DS_TEMPO_GASTO,DS_PREVISTO,PROJETOS_CD_PROJETO,DS_DESCRICAO)" + 
														  " values (isnull(max(cd_tarefa),0)+1,?,?,?,?,?,?,?)");
			
			stmt.setString(1, tarefa.getResumo());
			stmt.setLong(2, tarefa.getUsu_resp().getId());
			stmt.setString(3, tarefa.getStatus());
			stmt.setString(4, tarefa.getTempo_gasto());
			stmt.setString(5, tarefa.getTempo_previsto());
			stmt.setLong(6, tarefa.getProjeto().getId());
			stmt.setString(7, tarefa.getDescricao());
			
			stmt.execute();
			stmt.close();
			
			con.close(); 
	}
	
	public Tarefa buscarTarefa(Long id) throws SQLException{
		
			con = Conexao.getConnection();
			//PreparedStatement stmt = con.prepareStatement("Select t.*, p.*, u.*, tec.* from tarefas t, projetos p, usuario u, tecnologias tec where cd_projeto = ?" + 
			//								 " and t.cd_usuario_resp = u.cd_usuario and t.projetos_cd_projeto = p.cd_projeto and p.cd_tecnologia = tec.cd_tecnologia");
			
			PreparedStatement stmt = con.prepareStatement("Select t.*, u.nm_usuario, p.* from tarefas t, usuario u, tecnologias tec where  cd_projeto = ?" + 
										" and t.cd_usuario_resp = u.cd_usuario and t.projetos_cd_projeto = p.cd_projeto and p.cd_tecnologia = tec.cd_tecnologia");
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
				Tarefa tar = new Tarefa();
				Projeto proj = new Projeto();
				Usuario user = new Usuario();
				Tecnologia tec = new Tecnologia();
			if(rs.next()){
				user.setId(rs.getLong("CD_USUARIO_RESP"));
				user.setNome(rs.getString("NM_USUARIO"));
				/*user.setEmail(rs.getString("DS_EMAIL"));
				user.setSenha(rs.getString("DS_SENHA"));
				user.setTipo_usuario(rs.getString("TP_USUARIO"));
				user.setAtivo(rs.getString("IN_ATIVO"));*/
				
				//tec.setId(rs.getLong("CD_TECNOLOGIA"));
				//tec.setNome(rs.getString("NM_TECNOLOGIA"));
				//tec.setHome_page(rs.getString("DS_HOME_PAGE"));*/
				
				proj.setId(rs.getLong("PROJETOS_CD_PROJETO"));
				proj.setNome(rs.getString("NM_PROJETO"));
				proj.setUsu_resp(user);
				proj.setTecnologia(tec);
				
				tar.setId(id);
				tar.setDescricao(rs.getString("DS_DESCRICAO"));
				tar.setResumo(rs.getString("DS_RESUMO"));
				tar.setUsu_resp(user);
				tar.setStatus(rs.getString("DS_STATUS"));
				tar.setTempo_gasto(rs.getString("DS_TEMPO_GASTO"));
				tar.setTempo_previsto(rs.getString("DS_PREVISTO"));
				tar.setProjeto(proj);
			}
			
			stmt.close();
			
			con.close();
			
			return tar;
	}
	
	public List<Tarefa> listarTarefa(Long id) throws SQLException{
		
		List<Tarefa> temp = new ArrayList<Tarefa>();
		
			con = Conexao.getConnection();
			//PreparedStatement stmt = con.prepareStatement("Select t.*, p.*, u.*, tec.* from tarefas t, projetos p, usuario u, tecnologias tec where" +  
			//									  " t.cd_usuario_resp = u.cd_usuario and t.projetos_cd_projeto = p.cd_projeto and p.cd_tecnologia = tec.cd_tecnologia");
			PreparedStatement stmt = con.prepareStatement("Select t.*, u.nm_usuario, p.* from tarefas t, usuario u, projeto p where" + 
								" t.cd_usuario_resp = u.cd_usuario and t.projetos_cd_projeto = p.cd_projeto and cd_usuario = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Tarefa tar = new Tarefa();
			Projeto proj = new Projeto();
			Usuario user = new Usuario();
			Tecnologia tec = new Tecnologia();
			
			while(rs.next()){
				
				user.setId(rs.getLong("CD_USUARIO_RESP"));
				user.setNome(rs.getString("NM_USUARIO"));
				/*user.setEmail(rs.getString("DS_EMAIL"));
				user.setSenha(rs.getString("DS_SENHA"));
				user.setTipo_usuario(rs.getString("TP_USUARIO"));
				user.setAtivo(rs.getString("IN_ATIVO"));*/
				
				tec.setId(rs.getLong("CD_TECNOLOGIA"));
				tec.setNome(rs.getString("NM_TECNOLOGIA"));
				//tec.setHome_page(rs.getString("DS_HOME_PAGE"));*/
				
				proj.setId(rs.getLong("PROJETOS_CD_PROJETO"));
				proj.setNome(rs.getString("NM_PROJETO"));
				proj.setUsu_resp(user);
				proj.setTecnologia(tec);
				
				tar.setId(rs.getLong("CD_TAREFA"));
				tar.setDescricao(rs.getString("DS_DESCRICAO"));
				tar.setResumo(rs.getString("DS_RESUMO"));
				tar.setUsu_resp(user);
				tar.setStatus(rs.getString("DS_STATUS"));
				tar.setTempo_gasto(rs.getString("DS_TEMPO_GASTO"));
				tar.setTempo_previsto(rs.getString("DS_PREVISTO"));
				tar.setProjeto(proj);
				
				temp.add(tar);
			}
			stmt.close();
			
			con.close();
		
		return temp;
	}
	
	public void atualizaTarefa(Tarefa tarefa) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Update tarefas set ds_resumo = ?, cd_usuario_resp = ?, ds_status = ?, ds_tempo_gasto = ?," + 
														  "ds_previsto = ?, projetos_cd_projeto = ?, ds_descricao = ? where cd_tarefa = ?");
			
			stmt.setString(1, tarefa.getResumo());
			stmt.setLong(2, tarefa.getUsu_resp().getId());
			stmt.setString(3, tarefa.getStatus());
			stmt.setString(4, tarefa.getTempo_gasto());
			stmt.setString(5, tarefa.getTempo_previsto());
			stmt.setLong(6, tarefa.getProjeto().getId());
			stmt.setString(7, tarefa.getDescricao());
			stmt.setLong(8, tarefa.getId());
			
			stmt.execute();
			stmt.close();
			
			con.close();
	}
	
	public List<Tarefa> Pesquisar(int cd_proj, int cd_resp) throws SQLException{
		List<Tarefa> results = new ArrayList<Tarefa>();
		
			con = Conexao.getConnection();
			PreparedStatement stmt;
			if(cd_proj != 0 && cd_resp == 0){
				//stmt = con.prepareStatement("Select t.*, p.*, u.*, tec.* from tarefas t, projetos p, usuario u, tecnologias tec where t.projetos_cd_projeto = ?" +  
				//							"t.cd_usuario_resp = u.cd_usuario and t.projetos_cd_projeto = p.cd_projeto and p.cd_tecnologia = tec.cd_tecnologia");

				stmt = con.prepareStatement("Select t.*, u.nm_usuario, p.nm_projeto from tarefas t, usuario u, projeto p where projetos_cd_projeto = ? and t.cd_usuario_resp = u.cd_usuario" + 
											" and t.projetos_cd_projeto = p.cd_projeto");
				stmt.setInt(1, cd_proj);
			}else if(cd_proj == 0 && cd_resp != 0){
				//stmt = con.prepareStatement("Select t.*, p.*, u.*, tec.* from tarefas t, projetos p, usuario u, tecnologias tec where t.cd_usuario_resp = ?" +  
				//		"t.cd_usuario_resp = u.cd_usuario and t.projetos_cd_projeto = p.cd_projeto and p.cd_tecnologia = tec.cd_tecnologia");
				
				stmt = con.prepareStatement("Select t.*, u.nm_usuario, p.nm_projeto from tarefas t, usuario u, projeto p where cd_usuario_resp = ? and t.cd_usuario_resp = u.cd_usuario" + 
											" and t.projetos_cd_projeto = p.cd_projeto");
				stmt.setInt(1, cd_resp);
			}else{
				//stmt = con.prepareStatement("Select t.*, p.*, u.*, tec.* from tarefas t, projetos p, usuario u, tecnologias tec where t.cd_usuario_resp = ?" + 
				//						" and t.projetos_cd_projeto = ?" +  
				//		                " and t.cd_usuario_resp = u.cd_usuario and t.projetos_cd_projeto = p.cd_projeto and p.cd_tecnologia = tec.cd_tecnologia");
				stmt = con.prepareStatement("Select t.*, u.nm_usuario, p.nm_projeto from tarefas t, usuario u, projeto p where cd_usuario_resp = ? and projetos_cd_projeto = ? and t.cd_usuario_resp = u.cd_usuario" + 
											" and t.projetos_cd_projeto = p.cd_projeto");
				stmt.setInt(1, cd_resp);
				stmt.setInt(2, cd_proj);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Tarefa tar = new Tarefa();
				Projeto proj = new Projeto();
				Usuario user = new Usuario();
				//Tecnologia tec = new Tecnologia();
				
				user.setId(rs.getLong("CD_USUARIO_RESP"));
				user.setNome(rs.getString("NM_USUARIO"));
				/*user.setEmail(rs.getString("DS_EMAIL"));
				user.setSenha(rs.getString("DS_SENHA"));
				user.setTipo_usuario(rs.getString("TP_USUARIO"));
				user.setAtivo(rs.getString("IN_ATIVO"));*/
				
				/*tec.setId(rs.getLong("CD_TECNOLOGIA"));
				tec.setNome(rs.getString("NM_TECNOLOGIA"));
				tec.setHome_page(rs.getString("DS_HOME_PAGE"));*/
				
				proj.setId(rs.getLong("PROJETOS_CD_PROJETO"));
				proj.setNome(rs.getString("NM_PROJETO"));
				/*proj.setUsu_resp(user);
				proj.setTecnologia(tec);*/
				
				tar.setId(rs.getLong("CD_TAREFA"));
				tar.setDescricao(rs.getString("DS_DESCRICAO"));
				tar.setResumo(rs.getString("DS_RESUMO"));
				tar.setUsu_resp(user);
				tar.setStatus(rs.getString("DS_STATUS"));
				tar.setTempo_gasto(rs.getString("DS_TEMPO_GASTO"));
				tar.setTempo_previsto(rs.getString("DS_PREVISTO"));
				tar.setProjeto(proj);
				
				results.add(tar);
			}
			stmt.close();
			
			con.close();
		
		return results;
	}
	
	public List<Tarefa> pesquisarTarefas(int pageNumber) throws SQLException{
		List<Tarefa> results = new ArrayList<Tarefa>();
		
			int numPg = this.getTotalPages();
			
			int valueInitial = pageNumber*numPg;
			int valueFinal = valueInitial + 5;
			
			PreparedStatement stmt = con.prepareStatement("Select t.*, u.nm_usuario, p.nm_projeto from tarefas t, usuario u, projeto p where t.cd_usuario_resp = u.cd_usuario" + 
											" and t.projetos_cd_projeto = p.cd_projeto and cd_tarefa between ? and ?");
			
			stmt.setInt(1, valueInitial);
			stmt.setInt(2, valueFinal);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Tarefa tar = new Tarefa();
				Projeto proj = new Projeto();
				Usuario user = new Usuario();
				//Tecnologia tec = new Tecnologia();
				
				user.setId(rs.getLong("CD_USUARIO_RESP"));
				user.setNome(rs.getString("NM_USUARIO"));
				/*user.setEmail(rs.getString("DS_EMAIL"));
				user.setSenha(rs.getString("DS_SENHA"));
				user.setTipo_usuario(rs.getString("TP_USUARIO"));
				user.setAtivo(rs.getString("IN_ATIVO"));*/
				
				/*tec.setId(rs.getLong("CD_TECNOLOGIA"));
				tec.setNome(rs.getString("NM_TECNOLOGIA"));
				tec.setHome_page(rs.getString("DS_HOME_PAGE"));*/
				
				proj.setId(rs.getLong("PROJETOS_CD_PROJETO"));
				proj.setNome(rs.getString("NM_PROJETO"));
				/*proj.setUsu_resp(user);
				proj.setTecnologia(tec);*/
				
				tar.setId(rs.getLong("CD_TAREFA"));
				tar.setDescricao(rs.getString("DS_DESCRICAO"));
				tar.setResumo(rs.getString("DS_RESUMO"));
				tar.setUsu_resp(user);
				tar.setStatus(rs.getString("DS_STATUS"));
				tar.setTempo_gasto(rs.getString("DS_TEMPO_GASTO"));
				tar.setTempo_previsto(rs.getString("DS_PREVISTO"));
				tar.setProjeto(proj);
				
				results.add(tar);
			}
			stmt.close();
			
			con.close();
			
			return results;
	}
	
	public void deixarTarefaConcluida(Long id) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Upadte tarefas set ds_status = 'Concluído' where cd_tarefa = ?");
			
			stmt.setLong(1, id);
			
			stmt.executeUpdate();
			stmt.close();
			con.close();
	}
	
	public void deixarTarefaPendente(Long id) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Upadte tarefas set ds_status = 'Pendente' where cd_tarefa = ?");
			
			stmt.setLong(1, id);
			
			stmt.executeUpdate();
			stmt.close();
			con.close();
	}
	
	public void deletarTarefa(Long id) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Delete from tarefas where cd_tarefa = ?");
			
			stmt.setLong(1, id);
			stmt.executeUpdate();
			
			stmt.close();
			
			con.close();
	}
	
	public int getTotalRecords() throws SQLException{
		con = Conexao.getConnection();
		PreparedStatement count = con.prepareStatement("Select count(*) v_Aux from tarefas");
		int numPg =  0;
		
		ResultSet contagem = count.executeQuery();
		if(contagem.next())
			numPg = contagem.getInt("v_Aux");
		
		return numPg;
	}
	
	public int getTotalPages() throws SQLException{
		return getTotalRecords()/5;
	}
}
