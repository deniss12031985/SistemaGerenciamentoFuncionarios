package br.com.empresa.exception;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
