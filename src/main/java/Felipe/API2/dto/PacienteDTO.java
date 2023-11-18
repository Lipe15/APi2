package Felipe.API2.dto;

import Felipe.API2.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PacienteDTO {

        @Id
        private String id;
        @NotBlank(message = "O nome do paciente não pode estar em branco")
        private String nome;
        private String sobrenome;
        private String cpf;
        private LocalDate dataNascimento;
        private String contato;
        private String cep;
        private String sexo;
        private String uf;
        private String bairro;
        private String localidade;


}
