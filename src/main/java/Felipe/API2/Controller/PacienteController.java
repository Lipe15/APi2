package Felipe.API2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Pacientes")
public class PacienteController {
    public String sayHello(){
        return "Olá Acampamento Dev";
    }
}
