package Felipe.API2.dto;

import Felipe.API2.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PacienteDTO {

        @Id
        private String id;
        @NotBlank(message = "Nome do paciente não pode estar em branco.")
        @Pattern(regexp = "^[A-Z]+(.)*", message = "Campo nome deve iniciar com letra maiúscula.")
        private String nome;
        @NotBlank(message = "O Sobrenome do paciente não pode estar em branco.")
        private String sobrenome;
        @NotBlank(message = "O CPF do paciente não pode estar em branco.")
        @CPF(message = "CPF invalido")
        private String cpf;
        private LocalDate dataNascimento;
        private String contato;
        private String cep;
        @NotBlank(message = "O CPF do paciente não pode estar em branco.")
        private String sexo;
        private String uf;
        private String bairro;
        private String localidade;


}
