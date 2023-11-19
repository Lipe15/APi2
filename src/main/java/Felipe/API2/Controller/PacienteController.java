package Felipe.API2.Controller;

import Felipe.API2.Exception.PacienteNotFoundException;
import Felipe.API2.HttpCliente.CepHttpCliente;
import Felipe.API2.dto.Estado;
import Felipe.API2.dto.PacienteDTO;
import Felipe.API2.entity.Endereco;
import Felipe.API2.entity.Paciente;
import Felipe.API2.service.PacienteService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Slf4j
@RestController
@RequestMapping("/api/Pacientes")
public class PacienteController {
    private static Logger logger = LoggerFactory.getLogger(PacienteController.class);
    @Autowired
    PacienteService pacienteService;
    @Autowired
    CepHttpCliente cepHttpCliente;


    @GetMapping

    public List<Paciente> obterTodos(){
        try {
            logger.info("Recebida uma solicitação para buscar pacientes");
            return pacienteService.obterTodos();
        }
        catch (Exception ex){
            logger.error("Erro ao tentar obter todos os pacientes", ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel listar todos os pacientes", ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>>findByid(@Valid @PathVariable String id) {
        try {
            logger.info("Buscando paciente pelo ID: {}", id );
            return ResponseEntity.ok().body(pacienteService.findByid(id));
        } catch (Exception ex){
            logger.error("Erro ao buscar paciente pelo ID: {}", id, ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel achar esse paciente", ex);
        }
    }
    @GetMapping("/por-uf")
    public ResponseEntity<List<Estado>> obterPacientesPorUf(@RequestParam(required = false)String uf) {
        try {
            logger.info("Buscando Pacientes por UF: {}", uf);
            List<Estado> pacientesDTO = pacienteService.obterPacientesPorUf(uf);
            return ResponseEntity.ok(pacientesDTO);
        } catch (Exception ex) {
            logger.error("Erro ao obter pacientes por UF", ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao boter pacientes por UF", ex);
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> inserir(@RequestBody @Valid PacienteDTO pacienteDTO) {
        try {
            logger.info("Recebendo solicitação para inserir um novo paciente.");
            Paciente paciente = new Paciente(pacienteDTO);
            Endereco endereco = cepHttpCliente.obterEnderecoPeloCep(pacienteDTO.getCep());
            paciente.setEndereco(endereco);
            pacienteService.inserir(paciente);

            logger.info("Novo paciente inserido com sucesso.");

            return ResponseEntity.created(null).body(paciente);
        }
        catch (Exception ex) {
            logger.error("Erro ao processar a solicitação de inserção de paciente.", ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paciente não resgistrado", ex);
            }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@RequestBody PacienteDTO pacienteDTO, @PathVariable String id) {
        try {
            logger.info("Recebendo solicitação para atualizar paciente com ID: {}", id);

            return ResponseEntity.ok().body(pacienteService.atualizar(pacienteDTO, id));
        }
        catch (PacienteNotFoundException ex) {
            logger.error("Erro ao processar a solicitação de atualização do paciente.", ex);
            throw ex;
        } catch (Exception ex) {
            logger.error("Erro ao processar a solicitação de atualização do paciente.", ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar o paciente", ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> deletar(@PathVariable String id) {
        try{
            logger.info("Recebendo solicitação para excluir paciente com ID: {}", id);

            pacienteService.remove(id);

            logger.info("Paciente excluído com sucesso.");

            return ResponseEntity.noContent().build();
        } catch (PacienteNotFoundException ex) {
            logger.error("Paciente não encontrado com o ID: {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o paciente.", ex);
        } catch (Exception ex) {
            logger.error("Erro ao processar a solicitação de exclusão do paciente.", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir o paciente", ex);
        }
    }


}