package br.com.empresa.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioJaCadastradoExceptionTest {
    @Test void preservaMensagem() {
        var e = new FuncionarioJaCadastradoException("Duplicado");
        assertEquals("Duplicado", e.getMessage());
    }
}
