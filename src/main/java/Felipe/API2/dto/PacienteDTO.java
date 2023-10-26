package Felipe.API2.dto;

import Felipe.API2.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PacienteDTO {


        private String nome;
        private String sobrenome;
        private String cpf;
        private LocalDate dataNascimento;
        private String contato;
        private String cep;


}
