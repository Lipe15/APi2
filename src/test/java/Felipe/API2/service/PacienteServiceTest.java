package Felipe.API2.service;

import Felipe.API2.Exception.PacienteNotFoundException;
import Felipe.API2.HttpCliente.CepHttpCliente;
import Felipe.API2.Repository.PacienteRepository;
import Felipe.API2.dto.PacienteDTO;
import Felipe.API2.entity.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PacienteServiceTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PacienteRepository pacienteRepository;

    @MockBean
     CepHttpCliente cepHttpCliente;

    @MockBean
     PacienteService pacienteService;
    @MockBean
    Paciente paciente;


    @Test
    @DisplayName("Deve retornar todos os pacientes cadastrados")
    public void testObterTodos() {
        // Arrange
        List<Paciente> pacientesMock = new ArrayList<>();
        when(pacienteRepository.findAll()).thenReturn(pacientesMock);

        // Act
        List<Paciente> pacientes = pacienteService.obterTodos();

        // Assert
        assertThat(pacientes).isEqualTo(pacientesMock);
    }

    @Test
    @DisplayName("Deve inserir um novo paciente")
    public void testInserir() {
        // Arrange
        Paciente pacienteMock = new Paciente();
        when(pacienteRepository.insert((Paciente) any())).thenReturn(pacienteMock);

        // Act
        Paciente paciente = pacienteService.inserir(new Paciente());

        // Assert
        assertThat(paciente).isEqualTo(pacienteMock);
    }

    @Test
    @DisplayName("Deve ser possível remover um paciente existente")
    public void testRemover() throws Exception {

        // Arrange
        Paciente paciente1 = new Paciente();
        paciente1.setId("653d254a70793512735a8edf");

        // Mock
        when(pacienteRepository.findById(paciente1.getId())).thenReturn(Optional.of(paciente1));

        // Act
        pacienteService.remove(paciente1.getId());

        // Verify
        verify(pacienteService, times(1)).remove(paciente1.getId());
    }


}
