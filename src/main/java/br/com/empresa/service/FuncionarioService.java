package br.com.empresa.service;

import br.com.empresa.exception.FuncionarioJaCadastradoException;
import br.com.empresa.exception.FuncionarioNaoEncontradoException;
import br.com.empresa.model.Funcionario;
import br.com.empresa.repository.FuncionarioRepository;

import java.math.BigDecimal;
import java.util.List;

public class FuncionarioService {
    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        if (repository == null) throw new IllegalArgumentException("Repository é obrigatório.");
        this.repository = repository;
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario == null) throw new IllegalArgumentException("Funcionário é obrigatório.");
        if (repository.existeCodigo(funcionario.getIdentificador()))
            throw new FuncionarioJaCadastradoException("Já existe funcionário com o código " + funcionario.getIdentificador());
        if (repository.existeCpf(funcionario.getCpf()))
            throw new FuncionarioJaCadastradoException("Já existe funcionário com este CPF.");
        repository.salvar(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return repository.listarTodos();
    }

    public Funcionario buscarFuncionario(int codigo) {
        return repository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário não encontrado: " + codigo));
    }

    public Funcionario buscarFuncionario(String cpf) {
        if (cpf == null) throw new IllegalArgumentException("CPF é obrigatório.");
        String cpfNormalizado = cpf.replaceAll("\\D", "");
        return repository.listarTodos().stream()
                .filter(f -> f.getCpf().equals(cpfNormalizado))
                .findFirst()
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário não encontrado para o CPF informado."));
    }

    public List<Funcionario> buscarFuncionario(String nome, boolean buscaExata) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");
        return repository.listarTodos().stream()
                .filter(f -> buscaExata ? f.getNome().equalsIgnoreCase(nome.trim())
                        : f.getNome().toLowerCase().contains(nome.trim().toLowerCase()))
                .toList();
    }

    public void removerFuncionario(int codigo) {
        buscarFuncionario(codigo);
        repository.remover(codigo);
    }

    public BigDecimal calcularTotalFolha() {
        return repository.listarTodos().stream()
                .map(Funcionario::calcularRemuneracao)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder("===== RELATÓRIO DA FOLHA DE PAGAMENTO =====\n");
        for (Funcionario funcionario : repository.listarTodos()) {
            sb.append(funcionario.exibirDados()).append("\n");
        }
        sb.append("TOTAL DA FOLHA: R$ ").append(calcularTotalFolha());
        return sb.toString();
    }
}
