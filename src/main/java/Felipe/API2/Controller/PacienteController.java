package Felipe.API2.Controller;

import Felipe.API2.entity.Paciente;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Pacientes")
public class PacienteController {
    private final List<Paciente> listaDePacientes = new ArrayList<>();
    @GetMapping
    public List<Paciente> obterTodos() {
        return listaDePacientes;
    }

    @GetMapping("/{cpf}")
    public Paciente obterPacientePeloCpf(@PathVariable String cpf){
        for (Paciente paciente : listaDePacientes) {
            if (paciente.getCpf().equals(cpf)) {
                return paciente;
            }
        }

        return null;
    }

    @PostMapping
    public void inserir(@RequestBody Paciente paciente){
        listaDePacientes.add(paciente);

    }
    @PutMapping("/{cpf}")
            public void atualizar(@RequestBody Paciente atualizarPaciente, @PathVariable String cpf){
        for (Paciente paciente : listaDePacientes) {
            if (paciente.getCpf().equals(cpf)) {
                BeanUtils.copyProperties(atualizarPaciente, paciente);
            }
        }


    }
    @PatchMapping("/{cpf}")
    public void atualizar(@RequestBody Paciente atualizarPaciente, @PathVariable String cpf){
        for (Paciente paciente : listaDePacientes) {
            if (paciente.getCpf().equals(cpf)) {
                BeanUtils.copyProperties(atualizarPaciente, paciente);
            }
        }


    }
}
