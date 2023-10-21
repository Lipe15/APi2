package Felipe.API2.Service;

import Felipe.API2.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import Felipe.API2.Entity.Paciente;
import Felipe.API2.Repository.PacienteRepository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class PacienteService {

        @Autowired
        PacienteRepository pacienteRepository;

        public List<Paciente> obterPacientes() {
            return pacienteRepository.findAll();
        }

        public void registrarPaciente(@Valid @RequestBody Paciente paciente){
            pacienteRepository.save(paciente);
        }

        public void deletarPaciente(String id) {
            Optional<Paciente> optionalPaciente = obterPacientePeloId(id);
            pacienteRepository.deleteById(id);
        }

        public Optional<Paciente> obterPacientePeloId(String id) {
            return pacienteRepository.findById(id);
        }

        public Paciente atualizarPaciente(Paciente novoPaciente, String id) throws ResourceNotFoundException {
            Optional<Paciente> optionalPaciente = obterPacientePeloId(id);
            if(optionalPaciente.isPresent()) {
                Paciente pacienteExistente = optionalPaciente.get();
                // Atualize os campos do pacienteExistente com os valores de novoPaciente
                return pacienteRepository.save(pacienteExistente);
            } else {
                throw new ResourceNotFoundException("Paciente não encontrado com o ID: " + id);
            }
        }

    public Optional<Paciente> obterDosesPaciente(String id) {
        return pacienteRepository.findById(id);
    }
}
