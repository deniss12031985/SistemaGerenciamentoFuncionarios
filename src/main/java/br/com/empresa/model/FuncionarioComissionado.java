package br.com.empresa.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FuncionarioComissionado extends Funcionario implements Tributavel {
    private BigDecimal percentualComissao;
    private BigDecimal totalVendas;

    public FuncionarioComissionado(int codigo, String nome, String cpf, BigDecimal salarioBase,
                                   BigDecimal percentualComissao, BigDecimal totalVendas) {
        super(codigo, nome, cpf, salarioBase);
        setPercentualComissao(percentualComissao);
        setTotalVendas(totalVendas);
    }

    public BigDecimal getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(BigDecimal percentualComissao) {
        if (percentualComissao == null || percentualComissao.compareTo(BigDecimal.ZERO) < 0
                || percentualComissao.compareTo(new BigDecimal("100")) > 0)
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 100.");
        this.percentualComissao = percentualComissao;
    }

    public BigDecimal getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(BigDecimal totalVendas) {
        if (totalVendas == null || totalVendas.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Total de vendas não pode ser nulo ou negativo.");
        this.totalVendas = totalVendas;
    }

    public BigDecimal calcularComissao() {
        return totalVendas.multiply(percentualComissao).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calcularTributos() {
        // Regra didática simplificada: desconto fixo de 8% sobre a remuneração bruta.
        return getSalarioBase().add(calcularComissao()).multiply(new BigDecimal("0.08"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calcularRemuneracao() {
        return getSalarioBase().add(calcularComissao()).subtract(calcularTributos())
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String exibirDados() {
        return super.exibirDados() + " | Categoria: Comissionado | Vendas: R$ " + totalVendas
                + " | Comissão: R$ " + calcularComissao();
    }
}
