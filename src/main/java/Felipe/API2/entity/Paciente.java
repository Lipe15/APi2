package Felipe.API2.entity;

import Felipe.API2.dto.PacienteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pacientes")
public class Paciente {
    @Id
    private String id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataNascimento;
    private String sexo;
    private String contato;
    private Endereco endereco;
    public Paciente(PacienteDTO pacienteDTO){
        setNome(pacienteDTO.getNome());
        setSobrenome(pacienteDTO.getSobrenome());
        setCpf(pacienteDTO.getCpf());
        setDataNascimento(pacienteDTO.getDataNascimento());
        setSexo(pacienteDTO.getSexo());
        setContato(pacienteDTO.getContato());

    }


}
