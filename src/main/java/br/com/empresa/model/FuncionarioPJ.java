package br.com.empresa.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FuncionarioPJ extends Funcionario {
    private BigDecimal valorPorProjeto;
    private int quantidadeProjetos;

    public FuncionarioPJ(int codigo, String nome, String cpf, BigDecimal valorPorProjeto,
                         int quantidadeProjetos) {
        super(codigo, nome, cpf, BigDecimal.ZERO);
        setValorPorProjeto(valorPorProjeto);
        setQuantidadeProjetos(quantidadeProjetos);
    }

    public BigDecimal getValorPorProjeto() {
        return valorPorProjeto;
    }

    public void setValorPorProjeto(BigDecimal valorPorProjeto) {
        if (valorPorProjeto == null || valorPorProjeto.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Valor por projeto não pode ser nulo ou negativo.");
        this.valorPorProjeto = valorPorProjeto;
    }

    public int getQuantidadeProjetos() {
        return quantidadeProjetos;
    }

    public void setQuantidadeProjetos(int quantidadeProjetos) {
        if (quantidadeProjetos < 0) throw new IllegalArgumentException("Quantidade de projetos inválida.");
        this.quantidadeProjetos = quantidadeProjetos;
    }

    @Override
    public BigDecimal calcularRemuneracao() {
        return valorPorProjeto.multiply(BigDecimal.valueOf(quantidadeProjetos))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String exibirDados() {
        return String.format("Código: %d | Nome: %s | CPF: %s | Categoria: PJ | Valor/projeto: R$ %s | Projetos: %d | Remuneração: R$ %s",
                getCodigo(), getNome(), getCpf(), valorPorProjeto, quantidadeProjetos, calcularRemuneracao());
    }
}
