package br.com.calculadora.service;

import org.springframework.stereotype.Service;

import br.com.calculadora.model.Calculadora;
import br.com.calculadora.model.Operacoes;

@Service
public class CalculadoraServiceImpl implements CalculadoraService {

  @Override
  public Calculadora calcularSoma(Calculadora calculadora) {
    calculadora.setOperando(Operacoes.SOMA);
    return calculadora.calcular();
  }

  @Override
  public Calculadora calcularSubtracao(Calculadora calculadora) {
    calculadora.setOperando(Operacoes.SUBTRACAO);
    return calculadora.calcular();
  }

  @Override
  public Calculadora calcularMultiplicacao(Calculadora calculadora) {
    calculadora.setOperando(Operacoes.MULTIPLICACAO);
    return calculadora.calcular();
  }

  @Override
  public Calculadora calcularDivisao(Calculadora calculadora) {
    calculadora.setOperando(Operacoes.DIVISAO);
    return calculadora.calcular();
  }
}
