package Felipe.API2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Paciente {
    @NotNull
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataNascimento;
    private String contato;
    private String endereco;

    public class Contato {
        private String celular;
        private String whatsapp;
        private String email;


    }

    public class Endereco {
        private String logradouro;
        private String numero;
        private String bairro;
        private String municipio;
        private String cep;
        private String estado;


    }
}
