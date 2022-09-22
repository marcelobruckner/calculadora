package br.com.calculadora.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

  MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
  RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado");

  private String title;
  private String uri;

  ProblemType(String path, String title) {
    this.uri = "" + path;
    this.title = title;
  }
}
