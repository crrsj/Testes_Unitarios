package com.teste.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class Usuario {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,unique = true)
	@NotBlank(message = "não pode estar em branco")
	private String nome;
	private String telefone;
	private String email;
	
	public void update(Usuario usuario) {
		if(usuario.nome != null) {
			this.nome = usuario.nome;
		}else if (usuario.telefone != null) {
			this.telefone = usuario.telefone;
		}else if(usuario.email != null) {
			this.email = usuario.email;
		}
		
	}
	
	
	}

	

