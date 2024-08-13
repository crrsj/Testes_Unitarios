package com.teste.infra;

import org.springframework.http.HttpStatus;

public record Validacao(HttpStatus status, String mensagem) {

}
