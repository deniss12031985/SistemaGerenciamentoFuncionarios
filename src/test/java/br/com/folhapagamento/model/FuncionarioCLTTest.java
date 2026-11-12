package br.com.folhapagamento.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FuncionarioCLTTest {

    @Test
    void deveSomarSalarioBaseEBeneficios() {
        FuncionarioCLT funcionario = new FuncionarioCLT(
                1,
                "Ana Souza",
                "52998224725",
                new BigDecimal("3000.00"),
                new BigDecimal("500.00")
        );

        BigDecimal remuneracao = funcionario.calcularRemuneracao();

        assertEquals(new BigDecimal("3500.00"), remuneracao);
    }
}