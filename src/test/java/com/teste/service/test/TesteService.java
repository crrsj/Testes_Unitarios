package com.teste.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.teste.model.Usuario;
import com.teste.repository.UsuarioRepository;
import com.teste.service.UsuarioService;

   @SpringBootTest
   class TesteService {

	private static final Long ID = 1L;

	private static final String TELEFONE = "123";

	private static final String EMAIL = "carlos@gmail.com";

	private static final String NOME = "Carlos";

	
 

	private Usuario usuario;
	
	private Optional<Usuario>optionalUsuario;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private UsuarioService usuarioService;
	
	
	@BeforeEach
	void setup() {
	
	    MockitoAnnotations.openMocks(this);
		 start();
	}
	
	 @Test
	  void usuarioCriadoComSucesso() {
		  when(usuarioRepository.save(usuario)).thenReturn(usuario);
		  var resposta = usuarioService.cadastrarUsuario(usuario);
		  assertNotNull(resposta);
		  assertEquals(Usuario.class, resposta.getClass());
		  assertEquals(ID, resposta.getId());
		  assertEquals(NOME, resposta.getNome());
		  assertEquals(TELEFONE, resposta.getTelefone());
		  assertEquals(EMAIL, resposta.getEmail());
	  }
	 
	 @Test
	 void falhaAoCriarUsuario() {
		 doThrow(new RuntimeException()).when(usuarioRepository).save(usuario);
			assertThrows(RuntimeException.class, ()->usuarioService.cadastrarUsuario(usuario));
		 }
	 
	 @Test
	 void quandoBuscarPorIdRetorneSucesso() {
		 when(usuarioRepository.findById(anyLong())).thenReturn(optionalUsuario);
		 var resposta = usuarioService.buscarPorId(ID);
		 assertNotNull(resposta);
		 assertEquals(ID, resposta.getId());
		 assertEquals(NOME, resposta.getNome());
		 assertEquals(TELEFONE, resposta.getTelefone());
		 assertEquals(EMAIL, resposta.getEmail());
	 }
	@Test
	 void falhaAoBuscarPorId() {
		when(usuarioRepository.findById(anyLong())).thenThrow(new NoSuchElementException("ID,não encontrado !"));
		try {
			usuarioService.buscarPorId(ID);
		}catch(Exception ex){ 
			assertEquals(NoSuchElementException.class, ex.getClass());
			assertEquals("ID,não encontrado !", ex.getMessage());
		}
	 }
	 
	@Test
	 void quandoBuscarUsuariosRetorneSucesso() {
		 when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
		 List<Usuario>resposta = usuarioService.buscarUsuarios();
		 assertNotNull(resposta);
		 assertEquals(Usuario.class, resposta.get(0).getClass());
		 assertEquals(ID, resposta.get(0).getId());
		 assertEquals(NOME, resposta.get(0).getNome());
		 assertEquals(TELEFONE, resposta.get(0).getTelefone());
		 assertEquals(EMAIL, resposta.get(0).getEmail());
	 }
	
	@Test
	void quandoAtualizarUsuarioRetorneSucesso() {
		  when(usuarioRepository.save(usuario)).thenReturn(usuario);
		  var resposta = usuarioService.atualizarUsuario(usuario,ID);
		  assertNotNull(resposta);
		  assertEquals(Usuario.class, resposta.getClass());
		  assertEquals(ID, resposta.getId());
		  assertEquals(NOME, resposta.getNome());
		  assertEquals(TELEFONE, resposta.getTelefone());
		  assertEquals(EMAIL, resposta.getEmail());
		
	}
	
	@Test
	void falhaAoAtualizarUsuario() {
		 doThrow(new RuntimeException()).when(usuarioRepository).save(usuario);
		 assertThrows(RuntimeException.class, ()->usuarioService.atualizarUsuario(usuario,ID));
	}
	
	  
	  @Test
	  void sucessoAoExcluir() {
		  doNothing().when(usuarioRepository).deleteById(anyLong());
		  usuarioService.excluir(ID);
		  verify(usuarioRepository,times(1)).deleteById(ID);
	  }
	  
	  @Test
	 void sucessoAoAtualizarParcial() {
		  when(usuarioRepository.save(usuario)).thenReturn(usuario);
		  when(usuarioRepository.getReferenceById(anyLong())).thenReturn(usuario);
		  var resposta = usuarioService.atualizarParcial(usuario,ID);
		  assertNotNull(resposta);
		  assertEquals(Usuario.class, resposta.getClass());
		  assertEquals(ID, resposta.getId());
		  assertEquals(NOME, resposta.getNome());
		  assertEquals(TELEFONE, resposta.getTelefone());
		  assertEquals(EMAIL, resposta.getEmail());
	 }
	  
	  @Test
		void falhaAoAtualizarParcial() {
			 doThrow(new RuntimeException()).when(usuarioRepository).save(usuario);
			 assertThrows(RuntimeException.class, ()->usuarioService.atualizarParcial(usuario,ID));
		}
		
	  
	  @Test
	  void sucessoAobuscarPorNome() {
		  when(usuarioRepository.findByNome(anyString())).thenReturn(List.of(usuario));
		  List<Usuario> resposta = usuarioService.buscarPorNome(NOME);
		  assertNotNull(resposta);
		  assertEquals(Usuario.class, resposta.get(0).getClass());
		  assertEquals(ID, resposta.get(0).getId());
		  assertEquals(NOME, resposta.get(0).getNome());
		  assertEquals(TELEFONE, resposta.get(0).getTelefone());
		  assertEquals(EMAIL, resposta.get(0).getEmail());
	  }
	  
	  private  void start() {
		  usuario = new Usuario(ID,NOME,TELEFONE,EMAIL);		
		  optionalUsuario = Optional.of( new Usuario(ID,NOME,TELEFONE,EMAIL)); 
		  
		}
}
