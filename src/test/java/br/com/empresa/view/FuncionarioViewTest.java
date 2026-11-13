package br.com.empresa.view;

import br.com.empresa.repository.FuncionarioRepository;
import br.com.empresa.service.FuncionarioService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioViewTest {
    @Test void construtorExigeService() {
        assertThrows(NullPointerException.class, () -> new FuncionarioView(null));
    }
}
