package projeta.informatica.iniflex.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FuncionarioDTOTests {

    @Test
    void shouldCreateFuncionarioDTO_WhenValidData() {

        // Arrange
        String nome = "Maria";
        String funcao = "Operador";
        String dataNascimento = "18/10/2000";
        String salario = "2.210,38";

        // Act
        FuncionarioDTO dto = new FuncionarioDTO(
                nome,
                funcao,
                dataNascimento,
                salario
        );

        // Assert
        assertAll(
                () -> assertEquals(nome, dto.getNome()),
                () -> assertEquals(funcao, dto.getFuncao()),
                () -> assertEquals(dataNascimento, dto.getDataNascimento()),
                () -> assertEquals(salario, dto.getSalario())
        );
    }

}
