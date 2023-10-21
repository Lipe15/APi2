package Felipe.API2.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paciente {

    @Id
    private String id;

    @NotEmpty(message = "O nome do paciente não foi informado!")
    private String nome;

    @NotEmpty(message = "O sobrenome do paciente não foi informado!")
    private String sobrenome;

    @NotEmpty(message = "O CPF do paciente não foi informado!")
    @Pattern(regexp="\\d{3}.\\d{3}.\\d{3}-\\d{2}", message="CPF inválido!")
    private String cpf;

    @NotEmpty(message = "A data de nascimento do paciente não foi informada!")
    private String dataNascimento;

    @NotEmpty(message = "O sexo do paciente não foi informado!")
    private String sexo;

    @NotEmpty(message = "O contato do paciente não foi informado!")
    private String contato;

    @Valid
    @NotNull(message = "O endereço do paciente não foi informado!")
    private Endereco endereco;


}