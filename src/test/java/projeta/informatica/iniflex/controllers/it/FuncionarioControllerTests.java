package projeta.informatica.iniflex.controllers.it;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import projeta.informatica.iniflex.dto.FuncionarioDTO;
import projeta.informatica.iniflex.services.FuncionarioService;
import projeta.informatica.iniflex.tests.FuncionarioFactory;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
public class FuncionarioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FuncionarioService service;

    private List<FuncionarioDTO> lista;

    @BeforeEach
    void setUp() {
        FuncionarioDTO funcionarioDTO = FuncionarioFactory.create();


        lista = List.of(funcionarioDTO);

        Mockito.when(service.executar()).thenReturn(lista);
    }

    @Test
    public void shouldReturnListOfFuncionarios_WhenListarIsCalled() throws Exception {

        ResultActions result =
                mockMvc.perform(get("/funcionarios")
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].nome").exists());
        result.andExpect(jsonPath("$[0].nome").value("Maria"));
        result.andExpect(jsonPath("$[0].funcao").value("Operador"));
    }
    @Test
    public void shouldReturnEmptyList_WhenNoFuncionariosExist() throws Exception {

        Mockito.when(service.executar()).thenReturn(List.of());

        mockMvc.perform(get("/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
    @Test
    public void shouldReturnCorrectSize_WhenMultipleFuncionariosExist() throws Exception {

        Mockito.when(service.executar()).thenReturn(List.of(
                FuncionarioFactory.create(),
                new FuncionarioDTO("Caio", "Coordenador", "02/05/1961", "10.819,75")
        ));

        mockMvc.perform(get("/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    public void shouldReturnCorrectFuncionarioData_WhenListarIsCalled() throws Exception {

        Mockito.when(service.executar()).thenReturn(List.of(
                FuncionarioFactory.create()
        ));

        mockMvc.perform(get("/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria"))
                .andExpect(jsonPath("$[0].funcao").value("Operador"))
                .andExpect(jsonPath("$[0].dataNascimento").value("18/10/2000"))
                .andExpect(jsonPath("$[0].salario").value("2.210,38"));
    }
}

