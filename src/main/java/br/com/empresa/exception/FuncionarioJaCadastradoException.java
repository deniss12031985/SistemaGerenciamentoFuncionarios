package br.com.empresa.exception;

public class FuncionarioJaCadastradoException extends RuntimeException {
    public FuncionarioJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
