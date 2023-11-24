package Felipe.API2.dto;
import java.time.LocalDate;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estado {
    private String nome;
    private String uf;
    private String bairro;
    private String localidade;
    private String cpf;
    private int idade;

    public Estado(String nome, String bairro, String localidade, String uf, LocalDate dataNascimento, String cpf) {
    }

    public void calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento != null) {
            LocalDate hoje = LocalDate.now();
            this.idade = Period.between(dataNascimento, hoje).getYears();
        } else {
            this.idade = 0;
        }
    }
}


