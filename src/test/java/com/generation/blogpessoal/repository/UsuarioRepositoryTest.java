package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new Usuario (0l,"Pipoca da silva", "pipoquinha@email.com.br", "13465278", "https://i.imgur.com/FETvs20.jpg"));
		
		usuarioRepository.save(new Usuario (0l,"Gabriel da silva", "gabriel@email.com.br", "13465278", "https://i.imgur.com/FETvs20.jpg"));
		
		usuarioRepository.save(new Usuario (0l,"Jainna da silva", "jainna@email.com.br", "13465278", "https://i.imgur.com/FETvs20.jpg"));
		
		usuarioRepository.save(new Usuario (0l,"Kimberlly da silva", "kim@email.com.br", "13465278", "https://i.imgur.com/FETvs20.jpg"));
	}
	
	@Test
	@DisplayName("Retorna 1 Usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("pipoquinha@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("pipoquinha@email.com.br"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Pipoca da silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Gabriel da silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Jainna da silva"));
		
	}
	
	@AfterAll
	public void end() {
		
		usuarioRepository.deleteAll();
	}
	
}
