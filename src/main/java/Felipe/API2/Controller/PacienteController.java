package Felipe.API2.Controller;

import Felipe.API2.Entity.Paciente;
import Felipe.API2.Entity.Vacina.Dose;
import Felipe.API2.Exceptions.ResourceNotFoundException;
import Felipe.API2.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("pacientes/registrar-paciente")
    public ResponseEntity<Paciente> registrarPaciente(
            @Valid @RequestBody Paciente paciente){
        try{
            pacienteService.registrarPaciente(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
        }
        catch (Exception ex){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Paciente não registrado", ex);
        }

    }

    @GetMapping("/pacientes")
    public ResponseEntity<List<Paciente>> obterPacientes() {
        try {
            List<Paciente> pacienteLista = pacienteService.obterPacientes();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(pacienteLista);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível listar pacientes", ex);
        }

    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<Optional<Paciente>> obterPacientePeloId(@PathVariable String id){
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(pacienteService.obterPacientePeloId(id));
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o paciente.", ex);
        }

    }
    @GetMapping("/pacientes/{id}/doses")
    public ResponseEntity<List<Dose>> obterDosesPaciente(@PathVariable String id){
        try {
            ResponseEntity.ok().body(pacienteService.obterDosesPaciente(id));
            return pacienteService.obterDosesPaciene(id);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar as doses do paciente.", ex);
        }
    }

    @PutMapping("pacientes/{id}")
    public  ResponseEntity<Paciente> atualizarPaciente(@RequestBody Paciente novoPaciente, @PathVariable String id){
        try {
            return ResponseEntity.ok().body(pacienteService.atualizarPaciente(novoPaciente, id));
        }
        catch (Exception | ResourceNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível atualizar o paciente.", ex);
        }

    }

    @DeleteMapping("pacientes/deletar-paciente/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable String id ) {
        try{
            pacienteService.deletarPaciente(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível excluir o paciente.", ex);
        }

    }
}
