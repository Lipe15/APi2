package Felipe.API2.Controller;

import Felipe.API2.Exception.PacienteNotFoundException;
import Felipe.API2.HttpCliente.CepHttpCliente;
import Felipe.API2.dto.PacienteDTO;
import Felipe.API2.entity.Endereco;
import Felipe.API2.entity.Paciente;
import Felipe.API2.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/Pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;
    @Autowired
    CepHttpCliente cepHttpCliente;


    @GetMapping

    public List<Paciente> obterTodos(){
        try {
            return pacienteService.obterTodos();
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel listar todos os pacientes", ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>>findByid(@Valid @PathVariable String id) {
        try {
            return ResponseEntity.ok().body(pacienteService.findByid(id));
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel achar esse paciente", ex);
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> inserir(@RequestBody PacienteDTO pacienteDTO) {


        try {
            Paciente paciente = new Paciente(pacienteDTO);
            Endereco endereco = cepHttpCliente.obterEnderecoPeloCep(pacienteDTO.getCep());
            paciente.setEndereco(endereco);
            pacienteService.inserir(paciente);

            return ResponseEntity.created(null).body(paciente);
        }
        catch (Exception ex) {

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paciente não resgistrado", ex);
            }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@RequestBody PacienteDTO pacienteDTO, @PathVariable String id) {
        try {
            return ResponseEntity.ok().body(pacienteService.atualizar(pacienteDTO, id));
        }
        catch(Exception ex){
            throw new PacienteNotFoundException("Paciente não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> deletar(@PathVariable String id) {
        try{
            pacienteService.remove(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel excluir o paciente.", ex);
        }
    }
}