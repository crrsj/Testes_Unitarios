package com.teste.infra;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.Valid;

@ControllerAdvice
public class TratamentoDeErros { 

	  @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        BindingResult result = ex.getBindingResult();
	        Map<String, String> errors = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errors.put(error.getField(), error.getDefaultMessage());
	        }
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	    }
	  
	  @ExceptionHandler(DataIntegrityViolationException.class)
	  public ResponseEntity<Validacao>validarCampos(){		   
		  var erros = new Validacao(HttpStatus.CONFLICT,"Já existe cadastro com um ou mais campos digitados ");
		  return new ResponseEntity<>(erros,HttpStatus.CONFLICT);
	  }
	 
	  @ExceptionHandler(NoSuchElementException.class)
	  public ResponseEntity<Validacao>idNaoEncontrado(){
		  var erro = new Validacao(HttpStatus.NOT_FOUND,"ID,não encontrado !");
		  return new ResponseEntity<>(erro,HttpStatus.NOT_FOUND);
	  }
}
