package br.com.empresa.service;

import br.com.empresa.exception.*;
import br.com.empresa.model.*;
import br.com.empresa.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioServiceTest {
    private FuncionarioService novoService() {
        return new FuncionarioService(new FuncionarioRepository());
    }
    @Test void cadastraBuscaELista() {
        var service = novoService();
        var f = new FuncionarioPJ(1, "Ana Silva", "52998224725", new BigDecimal("100"), 2);
        service.cadastrarFuncionario(f);
        assertEquals(f, service.buscarFuncionario(1));
        assertEquals(f, service.buscarFuncionario("529.982.247-25"));
        assertEquals(1, service.buscarFuncionario("Ana", false).size());
        assertEquals(1, service.listarFuncionarios().size());
    }
    @Test void impedeCodigoDuplicado() {
        var service = novoService();
        service.cadastrarFuncionario(new FuncionarioPJ(1, "Ana", "52998224725", new BigDecimal("100"), 1));
        assertThrows(FuncionarioJaCadastradoException.class, () ->
                service.cadastrarFuncionario(new FuncionarioPJ(1, "Bia", "11144477735", new BigDecimal("100"), 1)));
    }
    @Test void buscaEremoveInexistenteLancaExcecao() {
        var service = novoService();
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.buscarFuncionario(99));
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.removerFuncionario(99));
    }
    @Test void calculaTotalDaFolha() {
        var service = novoService();
        service.cadastrarFuncionario(new FuncionarioPJ(1, "Ana", "52998224725", new BigDecimal("100"), 2));
        service.cadastrarFuncionario(new FuncionarioPJ(2, "Bia", "11144477735", new BigDecimal("50"), 2));
        assertEquals(new BigDecimal("300.00"), service.calcularTotalFolha());
    }
    @Test void geraRelatorio() {
        var service = novoService();
        service.cadastrarFuncionario(new FuncionarioPJ(1, "Ana", "52998224725", new BigDecimal("100"), 1));
        assertTrue(service.gerarRelatorio().contains("TOTAL DA FOLHA"));
    }
}
