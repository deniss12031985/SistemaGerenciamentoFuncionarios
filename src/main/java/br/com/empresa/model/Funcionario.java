package br.com.empresa.model;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Funcionario implements Identificavel {
    private final int codigo;
    private String nome;
    private String cpf;
    private BigDecimal salarioBase;

    protected Funcionario(int codigo, String nome, String cpf, BigDecimal salarioBase) {
        if (codigo <= 0) throw new IllegalArgumentException("O código deve ser positivo.");
        this.codigo = codigo;
        setNome(nome);
        setCpf(cpf);
        setSalarioBase(salarioBase);
    }

    @Override
    public int getIdentificador() {
        return codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!cpfValido(cpf)) throw new IllegalArgumentException("CPF inválido.");
        this.cpf = cpf.replaceAll("\\D", "");
    }

    private boolean cpfValido(String cpf) {
        if (cpf == null) return false;
        String n = cpf.replaceAll("\\D", "");
        if (!n.matches("\\d{11}") || n.chars().distinct().count() == 1) return false;
        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) soma += (n.charAt(i) - '0') * (10 - i);
            int dig1 = 11 - (soma % 11);
            if (dig1 >= 10) dig1 = 0;
            if (dig1 != n.charAt(9) - '0') return false;
            soma = 0;
            for (int i = 0; i < 10; i++) soma += (n.charAt(i) - '0') * (11 - i);
            int dig2 = 11 - (soma % 11);
            if (dig2 >= 10) dig2 = 0;
            return dig2 == n.charAt(10) - '0';
        } catch (RuntimeException e) {
            return false;
        }
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        if (salarioBase == null || salarioBase.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Salário-base não pode ser nulo ou negativo.");
        this.salarioBase = salarioBase;
    }

    public abstract BigDecimal calcularRemuneracao();

    public String exibirDados() {
        return String.format("Código: %d | Nome: %s | CPF: %s | Salário-base: R$ %s | Remuneração: R$ %s",
                codigo, nome, cpf, salarioBase, calcularRemuneracao());
    }

    @Override
    public String toString() {
        return exibirDados();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Funcionario outro)) return false;
        return codigo == outro.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
