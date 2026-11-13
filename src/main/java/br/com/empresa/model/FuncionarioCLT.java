package br.com.empresa.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FuncionarioCLT extends Funcionario implements Tributavel {
    private BigDecimal adicionalBeneficios;

    public FuncionarioCLT(int codigo, String nome, String cpf, BigDecimal salarioBase,
                          BigDecimal adicionalBeneficios) {
        super(codigo, nome, cpf, salarioBase);
        setAdicionalBeneficios(adicionalBeneficios);
    }

    public BigDecimal getAdicionalBeneficios() {
        return adicionalBeneficios;
    }

    public void setAdicionalBeneficios(BigDecimal adicionalBeneficios) {
        if (adicionalBeneficios == null || adicionalBeneficios.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Benefícios não podem ser nulos ou negativos.");
        this.adicionalBeneficios = adicionalBeneficios;
    }

    @Override
    public BigDecimal calcularRemuneracao() {
        return getSalarioBase().add(adicionalBeneficios).subtract(calcularTributos())
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calcularTributos() {
        // Regra didática simplificada: desconto fixo de 10% sobre salário-base.
        return getSalarioBase().multiply(new BigDecimal("0.10")).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String exibirDados() {
        return super.exibirDados() + " | Categoria: CLT | Benefícios: R$ " + adicionalBeneficios;
    }
}
