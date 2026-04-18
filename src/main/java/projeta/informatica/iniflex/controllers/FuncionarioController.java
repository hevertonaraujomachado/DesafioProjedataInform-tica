package projeta.informatica.iniflex.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeta.informatica.iniflex.dto.FuncionarioDTO;
import projeta.informatica.iniflex.services.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }
    @GetMapping
    public List<FuncionarioDTO> listar() {
        return service.executar();
}
}
