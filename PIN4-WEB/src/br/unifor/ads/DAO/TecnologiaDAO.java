package br.unifor.ads.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unifor.ads.entidades.Tecnologia;

public class TecnologiaDAO {
	private Connection con;
	
	public void inserirTecnologia(Tecnologia tecnologia) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Insert into tecnologias(NM_TECNOLOGIA,DS_HOME_PAGE,DS_CODIGO) values (?,?,?)");
			
			stmt.setString(1, tecnologia.getNome());
			stmt.setString(2, tecnologia.getHome_page());
			stmt.setString(3, tecnologia.getCodigo());
			
			stmt.executeUpdate();
			stmt.close();
			
			con.close(); 
	}
	
	public Tecnologia buscarTecnologia(Long tecId) throws SQLException{
		
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from tecnologias where cd_tecnologia = ?");
			stmt.setLong(1, tecId);
			
			ResultSet rs = stmt.executeQuery();
			Tecnologia tec = new Tecnologia();
			
			if(rs.next()){
				tec.setId(rs.getLong("CD_TECNOLOGIA"));
				tec.setNome(rs.getString("NM_TECNOLOGIA"));
				tec.setHome_page(rs.getString("DS_HOME_PAGE"));
				tec.setCodigo(rs.getString("DS_CODIGO"));
			}
				
			
			stmt.close();
			
			con.close();
			
			return tec;
	}
	
	public List<Tecnologia> listarTecnologia() throws SQLException{
		
		List<Tecnologia> temp = new ArrayList<Tecnologia>();
		
		
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from tecnologias");
			
			ResultSet rs = stmt.executeQuery();
			Tecnologia tec = new Tecnologia();
			
			while(rs.next()){
				tec.setId(rs.getLong("CD_TECNOLOGIA"));
				tec.setNome(rs.getString("NM_TECNOLOGIA"));
				tec.setHome_page(rs.getString("DS_HOME_PAGE"));
				tec.setCodigo(rs.getString("DS_CODIGO"));
				
				temp.add(tec);
			}
			stmt.close();
			
			con.close();
			
		return temp;
	}
	
	public void atualizaTecnologia(Tecnologia tecnologia) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Update tecnologias set nm_tecnologia = ?, ds_home_page = ?, ds_codigo = ? where cd_tecnologia = ?");
			
			stmt.setString(1, tecnologia.getNome());
			stmt.setString(2, tecnologia.getHome_page());
			stmt.setString(3, tecnologia.getCodigo());
			stmt.setLong(4, tecnologia.getId());
			
			stmt.executeUpdate();
			stmt.close();
			
			con.close();
	}
	
	public void deletarTecnologia(Tecnologia tecnologia) throws SQLException{
			con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Delete from tecnologias where cd_tecnologia = ?");
			
			stmt.setLong(1, tecnologia.getId());
			
			stmt.executeUpdate();
			stmt.close();
			
			con.close();
	}
}
