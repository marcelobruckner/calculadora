package br.com.calculadora.api.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calculadora.model.Calculadora;
import br.com.calculadora.service.CalculadoraServiceImpl;

@RestController
@RequestMapping("calculadora/api")
public class CalculadoraController {

  private final CalculadoraServiceImpl calculadoraService;

  public CalculadoraController(CalculadoraServiceImpl calculadoraService) {
    this.calculadoraService = calculadoraService;
  }

  @GetMapping("/somar")
  public Calculadora somar(@RequestBody @Valid Calculadora calculadora) {
    System.out.println("CalculadoraController >> somar: " + calculadora);
    return calculadoraService.calcularSoma(calculadora);
  }

  @GetMapping("/subtrair")
  public Calculadora subtrair(@RequestBody Calculadora calculadora) {
    return calculadoraService.calcularSubtracao(calculadora);
  }

  @GetMapping("/multiplicar")
  public Calculadora multiplicar(@RequestBody Calculadora calculadora) {
    return calculadoraService.calcularMultiplicacao(calculadora);
  }

  @GetMapping("/dividir")
  public Calculadora dividir(@RequestBody Calculadora calculadora) {
    return calculadoraService.calcularDivisao(calculadora);
  }

}
