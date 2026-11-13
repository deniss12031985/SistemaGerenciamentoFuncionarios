package br.com.empresa.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {
    @Test void rejeitaCpfInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioPJ(1, "Nome", "12345678900", new BigDecimal("100"), 1));
    }
    @Test void classesConcretasSaoPolimorficas() {
        Funcionario a = new FuncionarioPJ(1, "Nome", "52998224725", new BigDecimal("100"), 2);
        assertEquals(new BigDecimal("200.00"), a.calcularRemuneracao());
    }
}
