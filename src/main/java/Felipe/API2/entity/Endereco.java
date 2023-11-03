package Felipe.API2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String cep;
    private String uf;
    private String localidade;
    private String logradouro;
    private String bairro;

}
