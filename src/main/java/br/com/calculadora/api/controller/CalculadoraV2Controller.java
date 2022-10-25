package br.com.calculadora.api.controller;

import br.com.calculadora.model.Calculadora;
import br.com.calculadora.service.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculadora-v2/api")
public class CalculadoraV2Controller {

    @Autowired
    CalculadoraService calculadoraService;

    @GetMapping("/somar/{operador1}/{operador2}")
    public Calculadora somar(@PathVariable("operador1") Long operador1,
                             @PathVariable("operador2") Long operador2){

        Calculadora calculadora = new Calculadora(operador1, operador2);
        return calculadoraService.calcularSoma(calculadora);
    }

    @GetMapping("/subtrair/{operador1}/{operador2}")
    public Calculadora subtrair(@PathVariable("operador1") Long operador1,
                                @PathVariable("operador2") Long operador2) {
        Calculadora calculadora = new Calculadora(operador1, operador2);
        return calculadoraService.calcularSubtracao(calculadora);
    }

    @GetMapping("/multiplicar/{operador1}/{operador2}")
    public Calculadora multiplicar(@PathVariable("operador1") Long operador1,
                                   @PathVariable("operador2") Long operador2) {
        Calculadora calculadora = new Calculadora(operador1, operador2);
        return calculadoraService.calcularMultiplicacao(calculadora);
    }

    @GetMapping("/dividir/{operador1}/{operador2}")
    public Calculadora dividir(@PathVariable("operador1") Long operador1,
                               @PathVariable("operador2") Long operador2) {
        Calculadora calculadora = new Calculadora(operador1, operador2);
        return calculadoraService.calcularDivisao(calculadora);
    }
}
