package br.com.empresa.view;

import br.com.empresa.exception.FuncionarioJaCadastradoException;
import br.com.empresa.exception.FuncionarioNaoEncontradoException;
import br.com.empresa.model.*;
import br.com.empresa.service.FuncionarioService;

import java.math.BigDecimal;
import java.util.Scanner;

public class FuncionarioView {
    private final FuncionarioService service;
    private final Scanner scanner;

    public FuncionarioView(FuncionarioService service) {
        this.service = java.util.Objects.requireNonNull(service, "Service é obrigatório.");
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n===== SISTEMA DE FUNCIONÁRIOS =====");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Buscar por código");
            System.out.println("4 - Remover funcionário");
            System.out.println("5 - Relatório da folha");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opcao = scanner.nextLine();
            try {
                switch (opcao) {
                    case "1" -> cadastrar();
                    case "2" -> service.listarFuncionarios().forEach(f -> System.out.println(f.exibirDados()));
                    case "3" -> {
                        System.out.print("Código: ");
                        System.out.println(service.buscarFuncionario(Integer.parseInt(scanner.nextLine())).exibirDados());
                    }
                    case "4" -> {
                        System.out.print("Código: ");
                        service.removerFuncionario(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Funcionário removido.");
                    }
                    case "5" -> System.out.println(service.gerarRelatorio());
                    case "0" -> executando = false;
                    default -> System.out.println("Opção inválida.");
                }
            } catch (FuncionarioNaoEncontradoException | FuncionarioJaCadastradoException |
                     IllegalArgumentException e) {
                System.out.println("Atenção: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Não foi possível executar a operação: " + e.getMessage());
            }
        }
        System.out.println("Sistema encerrado.");
    }

    private void cadastrar() {
        System.out.println("Tipo: 1-CLT | 2-PJ | 3-Comissionado");
        String tipo = scanner.nextLine();
        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF (11 dígitos): ");
        String cpf = scanner.nextLine();
        Funcionario funcionario;
        switch (tipo) {
            case "1" -> {
                System.out.print("Salário-base: ");
                BigDecimal salario = new BigDecimal(scanner.nextLine());
                System.out.print("Adicional de benefícios: ");
                BigDecimal beneficios = new BigDecimal(scanner.nextLine());
                funcionario = new FuncionarioCLT(codigo, nome, cpf, salario, beneficios);
            }
            case "2" -> {
                System.out.print("Valor por projeto: ");
                BigDecimal valor = new BigDecimal(scanner.nextLine());
                System.out.print("Quantidade de projetos: ");
                int quantidade = Integer.parseInt(scanner.nextLine());
                funcionario = new FuncionarioPJ(codigo, nome, cpf, valor, quantidade);
            }
            case "3" -> {
                System.out.print("Salário-base: ");
                BigDecimal salario = new BigDecimal(scanner.nextLine());
                System.out.print("Percentual de comissão (0 a 100): ");
                BigDecimal percentual = new BigDecimal(scanner.nextLine());
                System.out.print("Total de vendas: ");
                BigDecimal vendas = new BigDecimal(scanner.nextLine());
                funcionario = new FuncionarioComissionado(codigo, nome, cpf, salario, percentual, vendas);
            }
            default -> throw new IllegalArgumentException("Tipo de funcionário inválido.");
        }
        service.cadastrarFuncionario(funcionario);
        System.out.println("Funcionário cadastrado com sucesso.");
    }
}
