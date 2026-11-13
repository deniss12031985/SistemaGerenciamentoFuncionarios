package br.com.empresa.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioPJTest {
    @Test void calculaValorDosProjetos() {
        var f = new FuncionarioPJ(2, "Bruno", "11144477735", new BigDecimal("1500"), 3);
        assertEquals(new BigDecimal("4500.00"), f.calcularRemuneracao());
    }
    @Test void rejeitaQuantidadeNegativa() {
        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioPJ(2, "Bruno", "11144477735", new BigDecimal("1500"), -1));
    }
}
