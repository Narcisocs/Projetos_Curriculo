package br.unifor.ads.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unifor.ads.entidades.BancoConhecimento;
import br.unifor.ads.entidades.Tecnologia;
import br.unifor.ads.entidades.Usuario;

public class BancoConhecimentoDAO {
private Connection con;
	
	public BancoConhecimentoDAO() throws SQLException {
		
	}
	
	public void inserirBanco(BancoConhecimento banco) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Insert into BANCO_CONHECIMENTO(CD_TECNOLOGIA,CD_USUARIO_CONTRIBUIDOR,DS_RESUMO,DT_CADASTRO,DS_DESCRICAO)" + 
														  " values (?,?,?,GETDATE(),?)");
			
			stmt.setLong(1, banco.getTecnologia().getId());
			stmt.setLong(2, banco.getUsu_contribuidor().getId());
			stmt.setString(3, banco.getResumo());
			stmt.setString(4, banco.getDescricao());
			
			stmt.execute();
			stmt.close();
			
			con.close();
	}
	
	public BancoConhecimento buscarBanco(Long cd_banco) throws SQLException{
		
			con = Conexao.getConnection();
			//PreparedStatement stmt = con.prepareStatement("Select bc.*, u.*, t.* from banco_conhecimento bc, usuario u, tecnologias t where bc.cd_banco_conhecimento = ? " +
			//											  "and bc.cd_tecnologia = t.cd_tecnologia and bc.cd_usuario_contribuidor = u.cd_usuario");
			PreparedStatement stmt = con.prepareStatement("Select bc.*, u.nm_usuario, t.nm_tecnologia from banco_conhecimento bc, usuario u, tecnologias t where cd_banco_conhecimento = ?" +
															"and bc.cd_tecnologia = t.cd_tecnologia and bc.cd_usuario_contribuidor = u.cd_usuario");
			stmt.setLong(1, cd_banco);
			
			ResultSet rs = stmt.executeQuery();
			
				Tecnologia tec = new Tecnologia();
				Usuario user = new Usuario();
				BancoConhecimento bc = new BancoConhecimento();
			if(rs.next()){
				user.setId(rs.getLong("CD_USUARIO_CONTRIBUIDOR"));
				user.setNome(rs.getString("NM_USUARIO"));
				/*user.setEmail(rs.getString("DS_EMAIL"));
				user.setSenha(rs.getString("DS_SENHA"));
				user.setTipo_usuario(rs.getString("TP_USUARIO"));
				user.setAtivo(rs.getString("IN_ATIVO"));*/
				
				tec.setId(rs.getLong("CD_TECNOLOGIA"));
				tec.setNome(rs.getString("NM_TECNOLOGIA"));
				//tec.setHome_page(rs.getString("DS_HOME_PAGE"));
				
				bc.setId(rs.getLong("CD_BANCO_CONHECIMENTO"));
				bc.setUsu_contribuidor(user);
				bc.setTecnologia(tec);
				bc.setResumo(rs.getString("DS_RESUMO"));
				bc.setCadastro(rs.getDate("DT_CADASTRO"));
				bc.setDescricao(rs.getString("DS_DESCRICAO"));
			}	
			
			stmt.close();
			
			con.close();
			
			return bc;
	}
	
	public List<BancoConhecimento> listarBancoConhecimento() throws SQLException{
		
		List<BancoConhecimento> lista = new ArrayList<BancoConhecimento>();
		
			con = Conexao.getConnection();
			//PreparedStatement stmt = con.prepareStatement("Select bc.*, u.*, t.* from banco_conhecimento bc, usuario u, tecnologias t where " +
			//		  									  "and bc.cd_tecnologia = t.cd_tecnologia and bc.cd_usuario_contribuidor = u.cd_usuario");
			
			PreparedStatement stmt = con.prepareStatement("Select bc.*, u.nm_usuario, t.nm_tecnologia from banco_conhecimento bc, usuario u, tecnologias t where" +
															" bc.cd_tecnologia = t.cd_tecnologia and bc.cd_usuario_contribuidor = u.cd_usuario");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Tecnologia tec = new Tecnologia();
				Usuario user = new Usuario();
				BancoConhecimento bc = new BancoConhecimento();
				
				user.setId(rs.getLong("CD_USUARIO_CONTRIBUIDOR"));
				user.setNome(rs.getString("NM_USUARIO"));
				/*user.setEmail(rs.getString("DS_EMAIL"));
				user.setSenha(rs.getString("DS_SENHA"));
				user.setTipo_usuario(rs.getString("TP_USUARIO"));
				user.setAtivo(rs.getString("IN_ATIVO"));*/
				
				tec.setId(rs.getLong("CD_TECNOLOGIA"));
				tec.setNome(rs.getString("NM_TECNOLOGIA"));
				//tec.setHome_page(rs.getString("DS_HOME_PAGE"));
				
				bc.setId(rs.getLong("CD_BANCO_CONHECIMENTO"));
				bc.setUsu_contribuidor(user);
				bc.setTecnologia(tec);
				bc.setResumo(rs.getString("DS_RESUMO"));
				bc.setCadastro(rs.getDate("DT_CADASTRO"));
				bc.setDescricao(rs.getString("DS_DESCRICAO"));
				
				lista.add(bc);
			}
			stmt.close();
			
			con.close();
			
		return lista;
	}
	
	public List<BancoConhecimento> pesquisarConhecimento(Long pageSize, Long pageNumber) throws SQLException{
		List<BancoConhecimento> results = new ArrayList<BancoConhecimento>();
		
			con = Conexao.getConnection();
			PreparedStatement count = con.prepareStatement("Select count(*) v_Aux from banco_conhecimento");
			Long numPg = (long) 0;
			
			ResultSet contagem = count.executeQuery();
			if(contagem.next())
				numPg = contagem.getLong("v_Aux")/pageSize;
			
			count.close();
			Long valueInitial = pageNumber*numPg;
			Long valueFinal = valueInitial + 10;
			
			PreparedStatement stmt = con.prepareStatement("Select bc.*, u.nm_usuario, t.nm_tecnologia from banco_conhecimento bc, usuario u, tecnologias t where cd_banco_conhecimento = ?" +
															"and bc.cd_tecnologia = t.cd_tecnologia and bc.cd_usuario_contribuidor = u.cd_usuario and cd_banco_conhecimento between ? and ?");
			
			stmt.setLong(1, valueInitial);
			stmt.setLong(2, valueFinal);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Tecnologia tec = new Tecnologia();
				Usuario user = new Usuario();
				BancoConhecimento bc = new BancoConhecimento();
				
				user.setId(rs.getLong("CD_USUARIO_CONTRIBUIDOR"));
				user.setNome(rs.getString("NM_USUARIO"));
				/*user.setEmail(rs.getString("DS_EMAIL"));
				user.setSenha(rs.getString("DS_SENHA"));
				user.setTipo_usuario(rs.getString("TP_USUARIO"));
				user.setAtivo(rs.getString("IN_ATIVO"));*/
				
				tec.setId(rs.getLong("CD_TECNOLOGIA"));
				tec.setNome(rs.getString("NM_TECNOLOGIA"));
				//tec.setHome_page(rs.getString("DS_HOME_PAGE"));
				
				bc.setId(rs.getLong("CD_BANCO_CONHECIMENTO"));
				bc.setUsu_contribuidor(user);
				bc.setTecnologia(tec);
				bc.setResumo(rs.getString("DS_RESUMO"));
				bc.setCadastro(rs.getDate("DT_CADASTRO"));
				bc.setDescricao(rs.getString("DS_DESCRICAO"));
				
				results.add(bc);
			}
			stmt.close();
			
			con.close();
			
			return results;
	}
	 
	public void atualizaBanco(BancoConhecimento banco) throws SQLException{
		con = Conexao.getConnection();
		PreparedStatement stmt = con.prepareStatement("Update BANCO_CONHECIMENTO set CD_TECNOLOGIA = ?, CD_USUARIO_CONTRIBUIDOR = ?, DS_RESUMO = ?, DS_DESCRICAO = ? where CD_TECNOLOGIA = ?");
		stmt.setLong(1, banco.getTecnologia().getId());
		stmt.setLong(2, banco.getUsu_contribuidor().getId());
		stmt.setString(3, banco.getResumo());
		stmt.setString(4, banco.getDescricao());
		stmt.setLong(5, banco.getId());
		
		stmt.executeUpdate();
		stmt.close();
		
		con.close();
	}
}
