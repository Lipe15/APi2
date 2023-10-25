package Felipe.API2.entity;

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
@Document(collection ="paciente")
public class Paciente {
    @Id
    private String id;
    @NotEmpty(message = "O nome não foi informado")
    private String nome;
    private String sobrenome;
    @NotEmpty
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
    public static String obterNome(){
        return "eai, blz ";
    }
}
