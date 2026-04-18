package projeta.informatica.iniflex.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import projeta.informatica.iniflex.services.FuncionarioService;
import projeta.informatica.iniflex.dto.FuncionarioDTO;
import projeta.informatica.iniflex.tests.FuncionarioFactory;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class FuncionarioServiceTests{


    private FuncionarioService service;

    @BeforeEach
    void setUp() {

        service = new FuncionarioService();
}



@Test
void shouldReturnListOfFuncionarios_WhenExecutarIsCalled() {

    List<FuncionarioDTO> result = service.executar();

    assertNotNull(result);
    assertFalse(result.isEmpty());
}

@Test
void shouldNotContainJoao_WhenExecutarIsCalled() {

    List<FuncionarioDTO> result = service.executar();

    boolean existeJoao = result.stream()
            .anyMatch(f -> f.getNome().equalsIgnoreCase("João"));

    assertFalse(existeJoao);
}

@Test
void shouldReturnNineFuncionarios_WhenExecutarIsCalled() {

    List<FuncionarioDTO> result = service.executar();

    assertEquals(9, result.size());
}

@Test
void shouldIncreaseSalary_WhenExecutarIsCalled() {

    List<FuncionarioDTO> result = service.executar();

    FuncionarioDTO maria = result.stream()
            .filter(f -> f.getNome().equals("Maria"))
            .findFirst()
            .orElseThrow();

    // só valida que está formatado e diferente do original
    assertNotEquals("2009.44", maria.getSalario());
}

@Test
void shouldGroupFuncionariosByFuncao_WhenAgruparIsCalled() {

    List<FuncionarioDTO> lista = service.executar();

    Map<String, List<FuncionarioDTO>> agrupado =
            service.agruparPorFuncao(lista);

    assertNotNull(agrupado);
    assertTrue(agrupado.containsKey("Operador"));
}

    @Test
    void shouldReturnAniversariantes_WhenMetodoIsCalled() {

        List<FuncionarioDTO> lista = service.executar();

        List<FuncionarioDTO> result =
                service.aniversariantes(lista);

        assertTrue(result.stream()
                .allMatch(f -> f.getDataNascimento().contains("/10/")
                        || f.getDataNascimento().contains("/12/")));
    }

    @Test
    void shouldReturnOrderedList_WhenOrdenarIsCalled() {

        List<FuncionarioDTO> lista = service.executar();

        List<FuncionarioDTO> ordenado =
                service.ordenarPorNome(lista);

        assertEquals("Alice", ordenado.get(0).getNome());
    }

    @Test
    void shouldCalculateTotalSalary_WhenMetodoIsCalled() {

        List<FuncionarioDTO> lista = service.executar();

        double total = service.totalSalarios(lista);

        assertTrue(total > 0);
    }
}