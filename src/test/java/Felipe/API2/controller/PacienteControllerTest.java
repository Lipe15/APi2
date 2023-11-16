package Felipe.API2.controller;


import Felipe.API2.Exception.PacienteNotFoundException;
import Felipe.API2.HttpCliente.CepHttpCliente;
import Felipe.API2.Repository.PacienteRepository;
import Felipe.API2.dto.PacienteDTO;
import Felipe.API2.entity.Endereco;
import Felipe.API2.entity.Paciente;
import Felipe.API2.service.PacienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
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
    @MockBean
    CepHttpCliente cepHttpCliente;
    @MockBean
    Endereco endereco;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Deve ser possivel obter todos os pacientes cadastrados")
    public void testObterTodos() throws Exception {
        // Arrange
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
        // Mock
        when(pacienteService.obterTodos()).thenReturn(pacientes);
        // Act e Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Pacientes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value(paciente1.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sobrenome").value(paciente1.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sexo").value(paciente1.getSexo()));
        // Verify
        verify(pacienteService, times(1)).obterTodos();
    }

    @Test
    @DisplayName("Deve retornar um array vazio quando busca não retornar pacientes")
    public void testObterListagemEmBranco() throws Exception{
        // Arrange
        List<Paciente> pacientes = new ArrayList<>();
        // Mock
        when(pacienteService.obterTodos()).thenReturn(pacientes);
        // Act e Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Pacientes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));

        // Verify
        verify(pacienteService,times(1)).obterTodos();
    }

    @Test
    @DisplayName("Deve ser possivel obter um paciente pelo ID")
    public void testObterPeloId( )throws Exception{
        //Arrange
        Paciente paciente1 = new Paciente();
        paciente1.setId("653d254a70793512735a8edf");
        paciente1.setNome("Felipe");
        paciente1.setSobrenome("santos");
        paciente1.setDataNascimento(LocalDate.of(2002,2,2));
        paciente1.setCpf("085.592.645.-74");
        paciente1.setSexo("Male");
        //Mock
        when(pacienteService.findByid(paciente1.getId())).thenReturn(Optional.of(paciente1));
        //Act e Assert

        mockMvc.perform(MockMvcRequestBuilders.get("/api/Pacientes/"+ paciente1.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(paciente1.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(paciente1.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sexo").value(paciente1.getSexo()));

        // Verify
        verify(pacienteService,times(1)).findByid(paciente1.getId());
    }
    @Test
    @DisplayName("Deve inserir um novo paciente")
    public void testInserirPaciente() throws Exception {
        // Arrange
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Felipe");
        pacienteDTO.setSobrenome("Santos");
        // Act e Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/Pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(pacienteDTO.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(pacienteDTO.getSobrenome()));
    }


    @Test
    @DisplayName("Deve lançar uma Exceção ao buscar um ID inexistente")
    public void testObterPeloIdInexistente  ( )throws Exception{
        //Arrange
        Paciente paciente1 = new Paciente();
        String id= "653d254a70793512735a8edf";

        //Mock
        when(pacienteService.findByid(id)).thenAnswer(invocation -> { throw new Exception("Mensagem da exceção");
    });
        //Act e Assert

        mockMvc.perform(MockMvcRequestBuilders.get("/api/Pacientes/"+ id))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // Verify
        verify(pacienteService,times(1)).findByid(paciente1.getId());
    }

    @Test
    @DisplayName("Deve ser possível remover um paciente existente")
    public void testRemover() throws Exception {
        // Arrange

        Paciente paciente1 = new Paciente();
        paciente1.setId("653d254a70793512735a8edf");
        // Mock
        when(pacienteRepository.findById(paciente1.getId())).thenReturn(Optional.of(paciente1));

        // Act e Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/Pacientes/"+ paciente1.getId()))
                .andExpect(status().isNoContent());
        // Verify
        verify(pacienteService, times(1)).remove(paciente1.getId());
    }
    @Test
    @DisplayName("Não deve Remover caso não ache o ID")
    public void testNotRemove() throws Exception {
        // Arrange
        Paciente paciente1 = new Paciente();
        paciente1.setId("653d254a70793512735a8edf");

      //Mock
        when(pacienteRepository.findById(paciente1.getId())).thenReturn(Optional.empty());

        // Act e Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/Pacientes/"+ paciente1.getId()))
                .andExpect(status().isNotFound());
        //verify
        verify(pacienteService, never()).remove(paciente1.getId());
    }
    @Test
    @DisplayName("Deve atualizar um paciente existente pelo ID")
    public void testAtualizar() throws Exception {
        // Arrange
        String pacienteId = "653d254a70793512735a8edf";
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("NovoNome");
        pacienteDTO.setSobrenome("NovoSobrenome");

        Paciente pacienteAtualizado = new Paciente(pacienteDTO);
        when(pacienteService.atualizar(pacienteDTO, pacienteId)).thenReturn(pacienteAtualizado);
        // Act e Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/api/Pacientes/{id}", pacienteId)
                        .content(asJsonString(pacienteDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("NovoNome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value("NovoSobrenome"));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar paciente com ID inexistente")
    public void testAtualizarPacienteIdInexistente() throws Exception {
        // Arrange
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("NovoNome");
        pacienteDTO.setSobrenome("NovoSobrenome");

        when(pacienteService.atualizar(any(), any())).thenThrow(new PacienteNotFoundException("Paciente não encontrado"));

        // Act e Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/api/Pacientes/IDInexistente")
                        .content(asJsonString(pacienteDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json("{\"message\":\"Paciente não encontrado com o ID: IDInexistente\"}"));
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}

