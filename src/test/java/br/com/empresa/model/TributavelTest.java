package br.com.empresa.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TributavelTest {
    @Test void calculaTributoDosFuncionariosTributaveis() {
        Tributavel f = new FuncionarioCLT(1, "Ana", "52998224725",
                new BigDecimal("1000"), new BigDecimal("100"));
        assertEquals(new BigDecimal("100.00"), f.calcularTributos());
    }
}
