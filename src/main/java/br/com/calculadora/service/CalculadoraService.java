package br.com.calculadora.service;

import br.com.calculadora.model.Calculadora;

public interface CalculadoraService {
  Calculadora calcularSoma(Calculadora calculadora);

  Calculadora calcularSubtracao(Calculadora calculadora);

  Calculadora calcularMultiplicacao(Calculadora calculadora);

  Calculadora calcularDivisao(Calculadora calculadora);
}
