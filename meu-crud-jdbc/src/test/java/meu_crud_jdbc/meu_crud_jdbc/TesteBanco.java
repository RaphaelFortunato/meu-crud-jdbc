package meu_crud_jdbc.meu_crud_jdbc;

import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConncetion;
import dao.UsuarioDAO;
import model.Usuario;

public class TesteBanco {

	/*@Test
	public void meoBanco() {
		SingleConncetion.getConnection();
	}*/ //teste de conexao com o banco
	
	@Test
	public void insertBanco() {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();		
		//usuarioDAO.salvar(usuario);//inserindo de forma statica
		//usuario.setId(3L);
		usuario.setNome("Luca");
		usuario.setEmail("lu@hotmail.com");
		
		usuarioDAO.salvar(usuario);
		System.out.println("Registro inserido com sucesso");
		
	}
	
	@Test
	public void consultarBanco() {
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
					
			List<Usuario> list = usuarioDAO.listaDeUsuarios();
			for (Usuario usuario : list) {
				System.out.println(usuario);
				System.out.println("----------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void consultaIdBanco() {
		
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			Usuario consulta = usuarioDAO.consultaUsuarioId(1L);
			System.out.println(consulta);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void atualizarBanco() {
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuarioBanco = usuarioDAO.consultaUsuarioId(2L);
			
			usuarioBanco.setNome("Nubia");
			
			usuarioDAO.atualizarUsuario(usuarioBanco);
			
			System.out.println(usuarioBanco.getNome());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void deletarNoBanco() {
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.deletarDoBanco(7L);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
