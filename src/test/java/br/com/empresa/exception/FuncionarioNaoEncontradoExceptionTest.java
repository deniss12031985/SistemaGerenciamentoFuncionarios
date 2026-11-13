package br.com.empresa.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioNaoEncontradoExceptionTest {
    @Test void preservaMensagem() {
        var e = new FuncionarioNaoEncontradoException("Não encontrado");
        assertEquals("Não encontrado", e.getMessage());
    }
}
