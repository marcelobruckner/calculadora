package br.com.calculadora.model;

import lombok.Getter;

@Getter
public enum Operacoes {
    SOMA("Soma"),
    SUBTRACAO("Subtracao"),
    DIVISAO("Divisao"),
    MULTIPLICACAO("Multiplicacao");

    private String operando;

    Operacoes(String operando) {
        this.operando = operando;
    }
}
