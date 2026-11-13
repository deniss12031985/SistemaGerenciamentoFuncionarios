package br.com.empresa.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioCLTTest {
    @Test void calculaRemuneracaoComBeneficiosEDesconto() {
        var f = new FuncionarioCLT(1, "Ana", "52998224725", new BigDecimal("3000"), new BigDecimal("500"));
        assertEquals(new BigDecimal("3200.00"), f.calcularRemuneracao());
    }
    @Test void rejeitaBeneficioNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioCLT(1, "Ana", "52998224725", new BigDecimal("3000"), new BigDecimal("-1")));
    }
}
