package br.com.empresa.repository;

import br.com.empresa.model.Funcionario;
import java.util.*;

public class FuncionarioRepository {
    private final Map<Integer, Funcionario> funcionarios = new LinkedHashMap<>();

    public void salvar(Funcionario funcionario) {
        Objects.requireNonNull(funcionario, "Funcionário não pode ser nulo.");
        funcionarios.put(funcionario.getIdentificador(), funcionario);
    }

    public Optional<Funcionario> buscarPorCodigo(int codigo) {
        return Optional.ofNullable(funcionarios.get(codigo));
    }

    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios.values());
    }

    public boolean existeCodigo(int codigo) {
        return funcionarios.containsKey(codigo);
    }

    public boolean existeCpf(String cpf) {
        return funcionarios.values().stream().anyMatch(f -> f.getCpf().equals(cpf.replaceAll("\\D", "")));
    }

    public void remover(int codigo) {
        funcionarios.remove(codigo);
    }

    public int quantidade() {
        return funcionarios.size();
    }
}
