package Felipe.API2.Controller;

import Felipe.API2.entity.Paciente;

import Felipe.API2.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/Pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;
    @GetMapping
    public List<Paciente> obterTodos(){
        return pacienteService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obterPacientePeloId(@Valid @PathVariable String id) {
        Optional<Paciente> paciente = pacienteService.findByid(id);

        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(paciente.get());
    }

    @PostMapping
    public ResponseEntity<Paciente> inserir(@Valid @RequestBody Paciente paciente) {
        pacienteService.inserir(paciente);
        return ResponseEntity.created(null).body(paciente);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@RequestBody Paciente novosDadosPaciente, @PathVariable String id) {
        Optional<Paciente> paciente = pacienteService.findByid(id);

        if (paciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Paciente responsePaciente = pacienteService.atualizar(id, novosDadosPaciente);
        return ResponseEntity.ok().body(novosDadosPaciente);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Paciente> atualizarCpf(@RequestParam("cpf") String cpf, @PathVariable String id) {

        Optional<Paciente> paciente = pacienteService.findByid(id);

        if (paciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Paciente novosDadosdoPaciente = paciente.get();
        novosDadosdoPaciente.setCpf(cpf);

        pacienteService.atualizar(id, novosDadosdoPaciente);

        return ResponseEntity.ok().body(novosDadosdoPaciente);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> deletar(@PathVariable String id) {
       Optional<Paciente> paciente = pacienteService.findByid(id);


        if (paciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pacienteService.remove(id);

        return ResponseEntity.ok().body(null);
    }
}