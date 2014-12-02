package br.unifor.ads.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unifor.ads.entidades.Usuario;

public class UsuarioDAO {
	
	private Connection con;
	
	public UsuarioDAO() throws SQLException {
	}
	
	public void inserirUsuario(Usuario usuario) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Insert into usuario(NM_USUARIO,DS_EMAIL,DS_SENHA,TP_USUARIO,IN_ATIVO) values (?,?,?,?,?)");
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getTipo_usuario());
			stmt.setString(5, usuario.getAtivo());
			
			stmt.executeUpdate();
			stmt.close();
			
			con.close(); 
			System.out.println("Conexão fechada"); 
	}
	
	public Usuario buscarUsuario(Long usuId) throws SQLException{
		
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from usuario where cd_usuario = ?");
			stmt.setLong(1, usuId);
			
			ResultSet rs = stmt.executeQuery();
			Usuario user = new Usuario();
			if(rs.next()){
				user.setId(rs.getLong("CD_USUARIO"));
				user.setNome(rs.getString("NM_USUARIO"));
				user.setEmail(rs.getString("DS_EMAIL"));
				user.setSenha(rs.getString("DS_SENHA"));
				user.setTipo_usuario(rs.getString("TP_USUARIO"));
				user.setAtivo(rs.getString("IN_ATIVO"));
			}
			
			stmt.close();
			
			con.close(); 
			System.out.println("Conexão fechada");
			
			return user;
	}
	
	public List<Usuario> listarUsuario() throws SQLException{
		
		List<Usuario> temp = new ArrayList<Usuario>();
		
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from usuario");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Usuario user = new Usuario();
				user.setId(rs.getLong("CD_USUARIO"));
				user.setNome(rs.getString("NM_USUARIO"));
				user.setEmail(rs.getString("DS_EMAIL"));
				user.setSenha(rs.getString("DS_SENHA"));
				user.setTipo_usuario(rs.getString("TP_USUARIO"));
				user.setAtivo(rs.getString("IN_ATIVO"));
				
				temp.add(user);
			}
			stmt.close();
			
			con.close(); 
			System.out.println("Conexão fechada");
		
		return temp;
	}
	
	public void atualizaUsuario(Usuario usuario) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Update usuario set NM_USUARIO = ?, DS_EMAIL = ?, DS_SENHA = ?, TP_USUARIO = ?, IN_ATIVO = ?" + 
														  " where cd_usuario = ?");
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getTipo_usuario());
			stmt.setString(5, usuario.getAtivo());
			stmt.setLong(6, usuario.getId());
			
			stmt.executeUpdate();
			stmt.close();
			
			con.close(); 
			System.out.println("Conexão fechada");
	}
	
	public void deletarUsuario(Long id) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Delete from usuario where cd_usuario = ?");
			
			stmt.setLong(1, id);
			
			stmt.executeUpdate();
			stmt.close();
			
			con.close(); 
			System.out.println("Conexão fechada");
		
	}
	
	public Usuario checaLogin(String email, String senha) throws SQLException{
		
		Usuario user = null;
		
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from usuario where ds_email = ? and ds_senha = ?");
			stmt.setString(1, email);
			stmt.setString(2, senha);
			System.out.println(stmt.toString());
			ResultSet login = stmt.executeQuery();
			user = new Usuario();
			
			if(login.next()){
				user.setId(login.getLong("CD_USUARIO"));
				user.setNome(login.getString("NM_USUARIO"));
				user.setEmail(login.getString("DS_EMAIL"));
				user.setSenha(login.getString("DS_SENHA"));
				user.setTipo_usuario(login.getString("TP_USUARIO"));
				user.setAtivo(login.getString("IN_ATIVO"));
				return user;
			}
			
			stmt.close();
			con.close(); 
			System.out.println("Conexão fechada");
			
			return user;
	}
	
	public boolean checaResponsavel(String TarOuProj, Long id, Long idRespProj , Long idRespTar) throws SQLException{
		boolean r = false;
			con = Conexao.getConnection();
			if("projeto".equals(TarOuProj)){
				PreparedStatement stmt = con.prepareStatement("Select u.* from usuario u, projeto p where u.cd_usuario = p.cd_usuario_resp and u.cd_usuario = ?");
				
				stmt.setLong(1, id);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()){
					r = true;
				}
			}//fim do if projResp
			else if("tarefa".equals(TarOuProj)){
				PreparedStatement stmt = con.prepareStatement("Select u.* from usuario u, tarefas t where u.cd_usuario = t.cd_usuario_resp and u.cd_usuario = ?");
				
				stmt.setLong(1, id);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()){
					r = true;
				}
			}
			
			if(idRespProj != null){
				PreparedStatement stmt = con.prepareStatement("Select u.* from usuario u, projeto p, tarefas t where u.cd_usuario = p.cd_usuario_resp and p.cd_projeto = t.projetos_cd_projeto and t.cd_tarefa = ?");
				stmt.setLong(1, idRespProj);
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					r = true;
				}
			}
			else if(idRespTar != null) 
			{
				PreparedStatement stmt = con.prepareStatement("Select u.* from usuario u, tarefas t where t.cd_usuario_resp = u.cd_usuario and t.cd_tarefa = ?");
				stmt.setLong(1, idRespTar);
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					r = true;
				}
			}
			
			con.close(); 
			System.out.println("Conexão fechada");
		return r;
	}//fim da funcao
}
