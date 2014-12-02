package br.unifor.ads.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unifor.ads.entidades.Projeto;
import br.unifor.ads.entidades.Tecnologia;
import br.unifor.ads.entidades.Usuario;

public class ProjetoDAO {
	private Connection con;
	
	public ProjetoDAO() throws SQLException{
		
	}
	
	public void inserirProjeto(Projeto projeto) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Insert into Projeto(DS_CODIGO,NM_PROJETO,CD_USUARIO_RESP,CD_TECNOLOGIA,VR_ORCAMENTO) values (?,?,?,?,?)");
			
			stmt.setString(1, projeto.getTitulo());
			stmt.setString(2, projeto.getNome());
			stmt.setLong(3, projeto.getUsu_resp().getId());
			stmt.setLong(4, projeto.getTecnologia().getId());
			stmt.setString(5, projeto.getOrcamento());
			
			stmt.executeUpdate();
			stmt.close();
			
			con.close(); 
	}
	
	public Projeto buscarProjeto(Long id) throws SQLException{
		
			con = Conexao.getConnection();
			//PreparedStatement stmt = con.prepareStatement("Select p.*, u.*, t.* from projetos p, usuario u, tecnologias t "	+ 
			//											  " where p.cd_projeto = ? and p.cd_usuario_resp = u.cd_usuario and p.cd_tecnologia = t.cd_tecnologia");
			PreparedStatement stmt = con.prepareStatement("Select p.*, u.nm_usuario, t.nm_tecnologia from projeto p, usuario u, tecnologias t where " +
															"p.cd_tecnologia = t.cd_tecnologia and p.cd_usuario_resp = u.cd_usuario and p.cd_projeto = ?");
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
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
				
				tec.setId(id);
				tec.setNome(rs.getString("NM_TECNOLOGIA"));
				//tec.setHome_page(rs.getString("DS_HOME_PAGE"));*/
				
				proj.setId(rs.getLong("CD_PROJETO"));
				proj.setNome(rs.getString("NM_PROJETO"));
				proj.setUsu_resp(user);
				proj.setTecnologia(tec);
				proj.setTitulo(rs.getString("DS_CODIGO"));
				proj.setOrcamento(rs.getString("VR_ORCAMENTO"));
				
			}
			stmt.close();
			
			con.close();
			
			return proj;
	}
	
	public List<Projeto> listarProjeto() throws SQLException{
		
		List<Projeto> temp = new ArrayList<Projeto>();
		
			con = Conexao.getConnection();
			//PreparedStatement stmt = con.prepareStatement("Select p.*, u.*, t.* from projetos p, usuario u, tecnologias t "	+ 
			//											  " where p.cd_usuario_resp = u.cd_usuario and p.cd_tecnologia = t.cd_tecnologia");
			PreparedStatement stmt = con.prepareStatement("Select p.*, u.nm_usuario, t.nm_tecnologia from projeto p, usuario u, tecnologias t where " +
															"p.cd_tecnologia = t.cd_tecnologia and p.cd_usuario_resp = u.cd_usuario");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Projeto proj = new Projeto();
				Usuario user = new Usuario();
				Tecnologia tec = new Tecnologia();
				
				user.setId(rs.getLong("CD_USUARIO_RESP"));
				user.setNome(rs.getString("NM_USUARIO"));
				/*user.setEmail(rs.getString("DS_EMAIL"));
				user.setSenha(rs.getString("DS_SENHA"));
				user.setTipo_usuario(rs.getString("TP_USUARIO"));
				user.setAtivo(rs.getString("IN_ATIVO"));*/
				
				tec.setId(rs.getLong("CD_TECNOLOGIA"));
				tec.setNome(rs.getString("NM_TECNOLOGIA"));
				//tec.setHome_page(rs.getString("DS_HOME_PAGE"));*/
				
				proj.setId(rs.getLong("CD_PROJETO"));
				proj.setNome(rs.getString("NM_PROJETO"));
				proj.setUsu_resp(user);
				proj.setTecnologia(tec);
				proj.setOrcamento(rs.getString("VR_ORCAMENTO"));
				proj.setTitulo(rs.getString("DS_CODIGO"));
				
				temp.add(proj);
			}
			stmt.close();
			
			con.close();
			
		return temp;
	}
	
	public void atualizaProjeto(Projeto projeto) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Update Projeto set DS_CODIGO = ?, NM_PROJETO = ?, CD_USUARIO_RESP = ?, CD_TECNOLOGIA = ?, VR_ORCAMENTO = ?" + 
														  " where CD_PROJETO = ?");
			
			stmt.setString(1, projeto.getNome());
			stmt.setString(2, projeto.getTitulo());
			stmt.setLong(3, projeto.getUsu_resp().getId());
			stmt.setLong(4, projeto.getTecnologia().getId());
			stmt.setString(5, projeto.getOrcamento());
			stmt.setLong(6, projeto.getId());
			
			stmt.executeUpdate();
			stmt.close();
			
			con.close();
	}
	
	public void deletarProjeto(Projeto projeto) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Delete from projeto where cd_projeto = ?");
			
			stmt.setLong(1, projeto.getId());
			
			stmt.executeUpdate();
			stmt.close();
			
			con.close();
	}
}
