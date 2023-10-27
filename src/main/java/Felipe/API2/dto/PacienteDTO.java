package Felipe.API2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        private String sexo;


}
