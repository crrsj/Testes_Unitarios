package com.teste.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.teste.controller.UsuarioController;
import com.teste.model.Usuario;
import com.teste.repository.UsuarioRepository;
import com.teste.service.UsuarioService;

@SpringBootTest
    class UsuarioControllerTest {
	
	private static final Long ID = 1L;

	private static final String TELEFONE = "123";

	private static final String EMAIL = "carlos@gmail.com";

	private static final String NOME = "Carlos";

	
	
	private Usuario usuario;
	
	
	@Mock
	private UsuarioService usuarioService;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private UsuarioController usuarioController;
	
	@BeforeEach
	void setup() {
		
		MockitoAnnotations.openMocks(this);
		 start();
	}
	
	@Test
	void quandoCadastrarUsuarioRetorneSucesso() {
		when(usuarioService.cadastrarUsuario(usuario)).thenReturn(usuario);
		ResponseEntity<Usuario> resposta = usuarioController.cadastrarUsuario(usuario);		
		assertNotNull(resposta);
		assertEquals(ResponseEntity.class, resposta.getClass());
		assertEquals(HttpStatus.CREATED , resposta.getStatusCode());
		
		
		
	}
	
	@Test
	void QuandoBuscarPorIdRetorneSucesso() {
		when(usuarioService.buscarPorId(anyLong())).thenReturn(usuario);
		ResponseEntity<Usuario>resposta = usuarioController.buscarPorId(ID);
		assertNotNull(resposta);
		assertNotNull(resposta.getBody());
		assertEquals(ResponseEntity.class, resposta.getClass());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(ID, resposta.getBody().getId());
		assertEquals(NOME, resposta.getBody().getNome());
		assertEquals(EMAIL, resposta.getBody().getEmail());
		assertEquals(TELEFONE, resposta.getBody().getTelefone());
	}
	
	@Test
	void sucessoAoAtualizar() {
		when(usuarioService.atualizarUsuario(usuario,ID)).thenReturn(usuario);
		ResponseEntity<Usuario>resposta = usuarioController.atualizarUsuario(usuario, ID);
		assertNotNull(resposta);
		assertEquals(ResponseEntity.class, resposta.getClass());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	void sucessoAoAtualizarParcialmente() {
		
		when(usuarioService.atualizarParcial(usuario,ID)).thenReturn(usuario);
		ResponseEntity<Usuario>resposta = usuarioController.atualizarParcial(usuario, ID);
		assertNotNull(resposta);
		assertEquals(ResponseEntity.class, resposta.getClass());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	void sucessoAoBuscarPorNome() {
		when(usuarioService.buscarPorNome(anyString())).thenReturn(List.of(usuario));
		ResponseEntity<List<Usuario>>resposta = usuarioController.buscarPorNome(NOME);
		assertNotNull(resposta);
		assertNotNull(resposta.getBody());
		assertEquals(ResponseEntity.class, resposta.getClass());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(ID, resposta.getBody().get(0).getId());
		assertEquals(NOME, resposta.getBody().get(0).getNome());
		assertEquals(TELEFONE, resposta.getBody().get(0).getTelefone());
		assertEquals(EMAIL, resposta.getBody().get(0).getEmail());
	}
	
	@Test
	void sucessoAoExcluir() {
		doNothing().when(usuarioService).excluir(anyLong());
		ResponseEntity<Void>resposta = usuarioController.excluir(ID);
		assertNotNull(resposta);
		verify(usuarioService,times(1)).excluir(anyLong());
		assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
	}
	
	@Test
	void quandoBuscarUsuariosRetorneSucesso (){
		when(usuarioService.buscarUsuarios()).thenReturn(List.of(usuario));
		ResponseEntity<List<Usuario>>resposta = usuarioController.buscarTodos();
		assertNotNull(resposta);
		assertNotNull(resposta.getBody());
		assertEquals(ResponseEntity.class, resposta.getClass());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(ID, resposta.getBody().get(0).getId());
		assertEquals(NOME, resposta.getBody().get(0).getNome());
		assertEquals(TELEFONE, resposta.getBody().get(0).getTelefone());
		assertEquals(EMAIL, resposta.getBody().get(0).getEmail());
	}
	private void start() {
		 usuario = new Usuario(ID,NOME,TELEFONE,EMAIL);		
	     
	}

}
