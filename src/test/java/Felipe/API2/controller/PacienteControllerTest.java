package Felipe.API2.controller;


import Felipe.API2.Repository.PacienteRepository;
import Felipe.API2.entity.Paciente;
import Felipe.API2.service.PacienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc


public class PacienteControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PacienteService pacienteService;
    @MockBean
    PacienteRepository pacienteRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Deve ser possivel obter todos os pacientes cadastrados")
    public void testObterTodos() throws Exception {

        Paciente paciente1 = new Paciente();
        paciente1.setNome("Felipe");
        paciente1.setSobrenome("santos");
        paciente1.setDataNascimento(LocalDate.of(2002,2,2));
        paciente1.setCpf("085.592.645.-74");
        paciente1.setSexo("Male");

        Paciente paciente2 = new Paciente();
        paciente2.setNome("maria");
        paciente2.setSobrenome("Leite");
        paciente2.setDataNascimento(LocalDate.of(2001,2,2));
        paciente2.setCpf("333.333.33.-33");
        paciente2.setSexo("Female");

    List<Paciente> pacientes = Arrays.asList(paciente1, paciente2);

        when(pacienteService.obterTodos()).thenReturn(pacientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/Pacientes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value(paciente1.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sobrenome").value(paciente1.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sexo").value(paciente1.getSexo()));

        verify(pacienteService, times(1)).obterTodos();
    }

    @Test
    @DisplayName("Deve retornar um array vazio quando busca não retornar pacientes")
    public void testObterListagemEmBranco() throws Exception{

        List<Paciente> pacientes = new ArrayList<>();

        when(pacienteService.obterTodos()).thenReturn(pacientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/Pacientes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));

        verify(pacienteService,times(1)).obterTodos();
    }

    @Test
    @DisplayName("Deve ser possivel obter um paciente pelo ID")
    public void testObterPeloId( )throws Exception{

        Paciente paciente1 = new Paciente();
        paciente1.setId("653d254a70793512735a8edf");

        when(pacienteService.findByid(paciente1.getId())).thenReturn(Optional.of(paciente1));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/Pacientes/"+ paciente1.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


        verify(pacienteService,times(1)).findByid(paciente1.getId());
    }
    @Test
    @DisplayName("Deve inserir um novo paciente")
    public void testInserirPaciente() throws Exception {

            Paciente paciente1 = new Paciente();
            paciente1.setNome("Felipe");
            paciente1.setSobrenome("Santos");

        when(pacienteService.inserir(Mockito.any(Paciente.class))).thenReturn(paciente1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/Pacientes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(paciente1)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(paciente1.getNome()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(paciente1.getSobrenome()));

            verify(pacienteService,times(1)).inserir(Mockito.any());
    }

    @Test
    @DisplayName("Deve ser possível remover um paciente existente")
    public void testRemover() throws Exception {


        Paciente paciente1 = new Paciente();
        paciente1.setId("653d254a70793512735a8edf");

        when(pacienteRepository.findById(paciente1.getId())).thenReturn(Optional.of(paciente1));


        mockMvc.perform(MockMvcRequestBuilders.delete("/api/Pacientes/"+ paciente1.getId()))
                .andExpect(status().isNoContent());

        verify(pacienteService, times(1)).remove(paciente1.getId());
    }
    @Test
    @DisplayName("Deve atualizar um paciente existente pelo ID")
    public void testAtualizar() throws Exception {

        String pacienteId = "653d254a70793512735a8edf";
        Paciente paciente = new Paciente();
        paciente.setNome("NovoNome");
        paciente.setSobrenome("NovoSobrenome");

        Paciente pacienteAtualizado = new Paciente();
        when(pacienteService.atualizar(paciente, pacienteId)).thenReturn(pacienteAtualizado);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/Pacientes/{id}", pacienteId)
                        .content(asJsonString(paciente))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("NovoNome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value("NovoSobrenome"));
    }


    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}

