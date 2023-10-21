package Felipe.API2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @NotEmpty(message = "Campo Obrigatório")
    private String logradouro;
    @NotEmpty(message = "O número em endereço não pode está nulo!")
    private Integer numero;
    @NotEmpty(message = "Campo Obrigatório")
    private String bairro;
    @NotEmpty(message = "Campo Obrigatório")
    private String municipio;
    @NotEmpty(message = "Campo Obrigatório")
    private String estado;
}
