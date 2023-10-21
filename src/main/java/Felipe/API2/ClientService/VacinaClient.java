package Felipe.API2.ClientService;

import Felipe.API2.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

public class VacinaClient {
    @Autowired
    PacienteService pacienteService;

//    @GetMapping("/pacientes/{id}/doses")
//    public ResponseEntity<List<Dose>> obterDosesPaciente(@PathVariable String id){
//        try {
//            return ResponseEntity.ok().body(pacienteService.obterDosesPaciente(id));
//        }
//        catch (Exception ex){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar as doses do paciente.", ex);
//        }
//    }
}
