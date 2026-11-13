package br.com.empresa;

import br.com.empresa.repository.FuncionarioRepository;
import br.com.empresa.service.FuncionarioService;
import br.com.empresa.view.FuncionarioView;

public class Main {
    public static void main(String[] args) {
        FuncionarioRepository repository = new FuncionarioRepository();
        FuncionarioService service = new FuncionarioService(repository);
        new FuncionarioView(service).iniciar();
    }
}
