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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("usuario")
public class UsuarioController {
	@Autowired
	private  UsuarioService usuarioService;
	 
	@PostMapping
	@Operation(summary = "Rota responsável pelo cadastro de usuários") 
    @ApiResponse(responseCode = "201",description = "usuário cadastrado com sucesso",content = {
   		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Usuario>cadastrarUsuario(@RequestBody Usuario usuario){
		var cadastrar = usuarioService.cadastrarUsuario(usuario);
		return new ResponseEntity<>(cadastrar,HttpStatus.CREATED);  
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Rota responsável pela busca de usuários pelo id")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
	public ResponseEntity<Usuario>buscarPorId(@PathVariable Long id){
		var buscaId = usuarioService.buscarPorId(id);
		return new ResponseEntity<>(buscaId,HttpStatus.OK);
	} 

	@GetMapping
	@Operation(summary = "Rota responsável pela busca de todos os usuários")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
	public ResponseEntity<List<Usuario>>buscarTodos(){
		var lista = usuarioService.buscarUsuarios();
		return new ResponseEntity<List<Usuario>>(lista,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Rota responsável por deletar um usuário pelo id")
	@ApiResponse(responseCode = "204",description = " sem conteúdo",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })           
	public ResponseEntity<Void>excluir(@PathVariable Long id){
		usuarioService.excluir(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Rota responsável pela atualização de dados do usuário")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
	public ResponseEntity<Usuario>atualizarUsuario(@RequestBody Usuario usuario,@PathVariable Long id){
		var atualizar = usuarioService.atualizarUsuario(usuario,id);	
		return new ResponseEntity<>(atualizar,HttpStatus.OK);
	}
	
	@PatchMapping("parcial/{id}")
	@Operation(summary = "Rota responsável pela atualização parcial de dados do usuário")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
	public ResponseEntity<Usuario>atualizarParcial(@RequestBody Usuario usuario,@PathVariable Long id){
	
		var atualizando = usuarioService.atualizarParcial(usuario,id);
		return new ResponseEntity<>(atualizando,HttpStatus.OK);
	}
	
	@GetMapping("buscar")
	@Operation(summary = "Rota responsável pela busca de usuários pelo nome")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
	public ResponseEntity<List<Usuario>>buscarPorNome(@RequestParam(name = "nome") String nome){
		var buscaNome = usuarioService.buscarPorNome(nome);
		return new ResponseEntity<List<Usuario>>(buscaNome,HttpStatus.OK);
	}
}
