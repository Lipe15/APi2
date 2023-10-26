package Felipe.API2.entity;

import Felipe.API2.dto.PacienteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pacientes")
public class Paciente {
    @Id
    private String id;
    @NotEmpty(message = "O nome não foi informado")
    private String nome;
    @NotEmpty(message = "O sobrenome não foi informado")
    private String sobrenome;
    @NotEmpty(message = "O cpf não foi informado")
    private String cpf;
    @NotEmpty(message = "a Data de Nascimento não foi informada")
    private LocalDate dataNascimento;
    @NotEmpty(message = "O Contato não foi informado")
    private String contato;

    private Endereco endereco;
    public Paciente(PacienteDTO pacienteDTO){
        setNome(pacienteDTO.getNome());
        setSobrenome(pacienteDTO.getSobrenome());
        setCpf(pacienteDTO.getCpf());
        setDataNascimento(pacienteDTO.getDataNascimento());
        setContato(pacienteDTO.getContato());

    }

}
