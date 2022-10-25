package br.com.calculadora.model;

import javax.validation.constraints.NotNull;

import br.com.calculadora.exception.CalculadoraException;
import br.com.calculadora.exception.DivisaoPorZeroException;
import br.com.calculadora.exception.OperandoNaoInformadoException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Calculadora {

  @NotNull
  private Long operador1;
  @NotNull
  private Long operador2;

  private Operacoes operando;
  private long resultado;

  public Calculadora(Long operador1, Long operador2) {
    this.operador1 = operador1;
    this.operador2 = operador2;
  }

  public Calculadora() {
  }

  public Calculadora calcular() throws CalculadoraException {

    if (this.operando == null) {
      throw new OperandoNaoInformadoException("Operando não informado");
    }

    if (Operacoes.SUBTRACAO.equals(this.operando)) {
      calcularSubtracao();
    }

    if (Operacoes.SOMA.equals(this.operando)) {
      calcularSoma();
    }

    if (Operacoes.MULTIPLICACAO.equals(this.operando)) {
      calcularMultiplicacao();
    }

    if (Operacoes.DIVISAO.equals(this.operando)) {
      calcularDivisao();
    }

    System.out.println("Calculadora >> calcular: " + this);
    return this;
  }

  private void calcularDivisao() {
    try {
      this.resultado = operador1 / operador2;
    } catch (ArithmeticException e) {
      throw new DivisaoPorZeroException("Divisão por zero");
    }
  }

  private void calcularMultiplicacao() {
    this.resultado = operador1 * operador2;
  }

  private void calcularSubtracao() {
    this.resultado = operador1 - operador2;
  }

  private void calcularSoma() {
    this.resultado = operador1 + operador2;
  }
}
