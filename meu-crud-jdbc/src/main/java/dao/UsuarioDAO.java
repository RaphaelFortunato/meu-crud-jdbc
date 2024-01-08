package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConncetion;
import model.Usuario;

public class UsuarioDAO {
	
	private Connection conexao;
	
	public UsuarioDAO() {
		conexao = SingleConncetion.getConnection();
	}

	public void salvar(Usuario usuario) {
		
		try {
			String sql = "insert into usuario ( nome, email) values (?,?) ";
			PreparedStatement insertUsu = conexao.prepareStatement(sql);
			//insertUsu.setLong(1, usuario.getId());
			insertUsu.setString(1, usuario.getNome());
			insertUsu.setString(2, usuario.getEmail());
			insertUsu.execute();
			
			conexao.commit();//salvar no banco
			
			
		} catch (Exception e) {
			try {
				conexao.rollback();//reverte
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
	}
	
	
	public List<Usuario> listaDeUsuarios() throws Exception{
		
		List<Usuario> lista = new ArrayList<Usuario>();		
		String sql = "select * from usuario";
		
		PreparedStatement consultaUsu = conexao.prepareStatement(sql);
		ResultSet resultado = consultaUsu.executeQuery();
		
		while (resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getLong("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setEmail(resultado.getString("email"));
			
			lista.add(usuario);
			
		}
		
		return lista;
	}
	
	
	public Usuario consultaUsuarioId(Long id) throws Exception{
		
		Usuario usuarioRetorno = new Usuario();
		
		String sql = "select * from usuario where id = " + id;
		
		PreparedStatement consultaId = conexao.prepareStatement(sql);
		ResultSet resultado = consultaId.executeQuery();
		while (resultado.next()) {
			usuarioRetorno.setId(resultado.getLong("id"));
			usuarioRetorno.setNome(resultado.getString("nome"));
			usuarioRetorno.setEmail(resultado.getString("email"));
		}
		
		
		return usuarioRetorno;
		
	}
	
	public void atualizarUsuario(Usuario usuario) {
		
		try {
			
			String sql = "update usuario set nome = ? where id = " + usuario.getId();
			
			PreparedStatement atualizar =  conexao.prepareStatement(sql);
			atualizar.setString(1, usuario.getNome());
			atualizar.execute();
			conexao.commit();
			
			
			
		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	public void deletarDoBanco(Long id) {
		try {
			
			String sql = "delete from usuario where id =" + id;
			PreparedStatement deletar = conexao.prepareStatement(sql);
			deletar.execute();
			conexao.commit();
			
		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
}
