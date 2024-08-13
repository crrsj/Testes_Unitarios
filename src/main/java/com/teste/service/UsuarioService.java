package com.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.model.Usuario;
import com.teste.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
   
	@Autowired
	private  UsuarioRepository usuarioRepository;
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario>buscarUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {		
		Optional<Usuario> buscaId =  usuarioRepository.findById(id);
		return buscaId.get();
	
	} 
	
	public Usuario atualizarUsuario(Usuario usuario,Long id) {
		usuario.setId(id);
		return usuarioRepository.save(usuario);
	}
	
	public void excluir(Long id) {
		usuarioRepository.deleteById(id); 
	}
	
   @Transactional
	public Usuario atualizarParcial(Usuario usuario,Long id) {
		  var atualize = usuarioRepository.getReferenceById(id);
		  atualize.update(usuario);
		return usuarioRepository.save(atualize);
	}
   
   public List<Usuario> buscarPorNome(String nome) {	  
	  return  usuarioRepository.findByNome(nome.trim().toUpperCase());
	 
   }
	
}
