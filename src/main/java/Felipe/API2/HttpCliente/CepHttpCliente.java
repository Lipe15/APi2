package Felipe.API2.HttpCliente;

import Felipe.API2.entity.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cep-cliente", url = "https://brasilapi.com.br/api")
public interface CepHttpCliente {

    @GetMapping("/cep/v2/{cep}")
    public Endereco obterEnderecoPeloCep(@PathVariable("cep") String cep);

}
