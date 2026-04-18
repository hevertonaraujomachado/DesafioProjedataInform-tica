package projeta.informatica.iniflex.tests;

import projeta.informatica.iniflex.dto.FuncionarioDTO;

import java.time.LocalDate;

public class FuncionarioFactory {

    public static FuncionarioDTO create() {
        return new FuncionarioDTO(
                "Maria",
                "Operador",
                "18/10/2000",
                "2.210,38"
        );
    }
}

