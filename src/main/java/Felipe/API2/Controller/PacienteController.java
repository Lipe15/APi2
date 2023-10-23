package Felipe.API2.Controller;

import Felipe.API2.entity.Paciente;

import Felipe.API2.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/Pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;
    @GetMapping
    public List<Paciente> obterTodos(){
        return pacienteService.obterTodos();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Paciente> obterPacientePeloCpf(@PathVariable String cpf) {
        Paciente paciente = pacienteService.selecionarPacientePeloCpf(cpf);

        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(paciente);
    }

    @PostMapping
    public ResponseEntity<Paciente> inserir(@RequestBody Paciente paciente) {
        pacienteService.inserir(paciente);
        return ResponseEntity.created(null).body(paciente);

    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Paciente> atualizar(@RequestBody Paciente novosDadosPaciente, @PathVariable String cpf) {
        Paciente paciente = pacienteService.selecionarPacientePeloCpf(cpf);

        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }

        pacienteService.atualizar(cpf, novosDadosPaciente);
        return ResponseEntity.ok().body(paciente);
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<Paciente> atualizarCpf(@RequestParam("cpf") String atualizarCpf, @PathVariable String cpf) {

        Paciente paciente = pacienteService.selecionarPacientePeloCpf(cpf);

        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        paciente.setCpf(atualizarCpf);
        pacienteService.atualizar(cpf, paciente);

        return ResponseEntity.ok().body(paciente);


    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Paciente> deletar(@PathVariable String cpf) {
       Paciente paciente = pacienteService.selecionarPacientePeloCpf(cpf);



        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        pacienteService.remove(cpf);

        return ResponseEntity.ok().body(null);
    }
}