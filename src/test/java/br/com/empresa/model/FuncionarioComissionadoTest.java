package br.com.empresa.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioComissionadoTest {
    @Test void calculaSalarioComComissaoETributo() {
        var f = new FuncionarioComissionado(3, "Carla", "11144477735",
                new BigDecimal("2000"), new BigDecimal("10"), new BigDecimal("5000"));
        assertEquals(new BigDecimal("2300.00"), f.calcularRemuneracao());
    }
    @Test void rejeitaPercentualMaiorQueCem() {
        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioComissionado(3, "Carla", "11144477735",
                        new BigDecimal("2000"), new BigDecimal("101"), new BigDecimal("5000")));
    }
}
