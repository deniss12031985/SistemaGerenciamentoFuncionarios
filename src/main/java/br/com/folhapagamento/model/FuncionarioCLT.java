package br.com.folhapagamento.model;

import java.math.BigDecimal;

public class FuncionarioCLT {

    private final int codigo;
    private final String nome;
    private final String cpf;
    private final BigDecimal salarioBase;
    private final BigDecimal beneficios;

    public FuncionarioCLT(
            int codigo,
            String nome,
            String cpf,
            BigDecimal salarioBase,
            BigDecimal beneficios) {

        if (salarioBase.signum() < 0) {
            throw new IllegalArgumentException(
                    "O salario-base nao pode ser negativo."
            );
        }

        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.salarioBase = salarioBase;
        this.beneficios = beneficios;
    }

    public BigDecimal calcularRemuneracao() {
        return salarioBase.add(beneficios);
    }
}