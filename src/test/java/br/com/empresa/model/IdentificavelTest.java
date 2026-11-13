package br.com.empresa.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class IdentificavelTest {
    @Test void retornaIdentificador() {
        Identificavel f = new FuncionarioPJ(7, "Ana", "52998224725", new BigDecimal("10"), 1);
        assertEquals(7, f.getIdentificador());
    }
}
