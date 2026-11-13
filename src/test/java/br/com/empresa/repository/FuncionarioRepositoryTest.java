package br.com.empresa.repository;

import br.com.empresa.model.FuncionarioPJ;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioRepositoryTest {
    @Test void salvaBuscaListaERemove() {
        var repo = new FuncionarioRepository();
        var f = new FuncionarioPJ(1, "Ana", "52998224725", new BigDecimal("100"), 2);
        repo.salvar(f);
        assertTrue(repo.existeCodigo(1));
        assertTrue(repo.buscarPorCodigo(1).isPresent());
        assertEquals(1, repo.listarTodos().size());
        repo.remover(1);
        assertFalse(repo.existeCodigo(1));
    }
    @Test void rejeitaNulo() {
        assertThrows(NullPointerException.class, () -> new FuncionarioRepository().salvar(null));
    }
}
