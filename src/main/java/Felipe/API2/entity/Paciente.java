package Felipe.API2.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pacientes")
public class Paciente {
    @Id
    private String id;
    @NotBlank(message = "Nome do paciente não pode estar em branco.")
    private String nome;
    @NotBlank(message = "O Sobrenome do paciente não pode estar em branco.")
    private String sobrenome;
    @NotBlank(message = "O CPF do paciente não pode estar em branco.")
    @CPF(message = "CPF invalido")
    private String cpf;
    private LocalDate dataNascimento;
    @NotBlank(message = "O Contato do paciente não pode estar em branco.")
    private String contato;
    @NotBlank(message = "O cep do paciente não pode estar em branco.")
    @Size(min = 8, max = 8, message = "O CEP do paciente deve ter exatamente 8 dígitos.")
    private String cep;
    @NotBlank(message = "O sexo do paciente não pode estar em branco.")
    private String sexo;
    @NotBlank(message = "O uf do paciente não pode estar em branco.")
    private String uf;
    @NotBlank(message = "O bairro do paciente não pode estar em branco.")
    private String bairro;
    @NotBlank(message = "A Localidade do paciente não pode estar em branco.")
    private String localidade;




}
