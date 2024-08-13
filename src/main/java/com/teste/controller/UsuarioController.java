package com.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.model.Usuario;
import com.teste.service.UsuarioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("usuario")
public class UsuarioController {
	@Autowired
	private  UsuarioService usuarioService;
	 
	@PostMapping
	public ResponseEntity<Usuario>cadastrarUsuario(@RequestBody @Valid Usuario usuario){
		var cadastrar = usuarioService.cadastrarUsuario(usuario);
		return new ResponseEntity<>(cadastrar,HttpStatus.CREATED);  
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Usuario>buscarPorId(@PathVariable Long id){
		var buscaId = usuarioService.buscarPorId(id);
		return new ResponseEntity<>(buscaId,HttpStatus.OK);
	} 

	@GetMapping
	public ResponseEntity<List<Usuario>>buscarTodos(){
		var lista = usuarioService.buscarUsuarios();
		return new ResponseEntity<List<Usuario>>(lista,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void>excluir(@PathVariable Long id){
		usuarioService.excluir(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Usuario>atualizarUsuario(@RequestBody Usuario usuario,@PathVariable Long id){
		var atualizar = usuarioService.atualizarUsuario(usuario,id);	
		return new ResponseEntity<>(atualizar,HttpStatus.OK);
	}
	
	@PatchMapping("parcial/{id}")
	public ResponseEntity<Usuario>atualizarParcial(@RequestBody Usuario usuario,@PathVariable Long id){
	
		var atualizando = usuarioService.atualizarParcial(usuario,id);
		return new ResponseEntity<>(atualizando,HttpStatus.OK);
	}
	
	@GetMapping("buscar")
	public ResponseEntity<List<Usuario>>buscarPorNome(@RequestParam(name = "nome") String nome){
		var buscaNome = usuarioService.buscarPorNome(nome);
		return new ResponseEntity<List<Usuario>>(buscaNome,HttpStatus.OK);
	}
}
