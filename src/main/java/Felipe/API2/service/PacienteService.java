package Felipe.API2.service;

import Felipe.API2.Repository.PacienteRepository;
import Felipe.API2.entity.Paciente;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> obterTodos() {

        return pacienteRepository.findAll();
    }

    public Paciente inserir(Paciente paciente) {
        pacienteRepository.insert(paciente);

        return paciente;

    }
    public Paciente atualizar(String id,Paciente novosDadosDoPaciente) {
        Optional<Paciente> paciente = findByid(id);
        if(paciente.isPresent()){
            Paciente novoPaciente = paciente.get();
            novoPaciente.setNome(novosDadosDoPaciente.getNome());
            novoPaciente.setSobrenome(novosDadosDoPaciente.getSobrenome());
            novoPaciente.setCpf(novosDadosDoPaciente.getCpf());
            novoPaciente.setDataNascimento(novosDadosDoPaciente.getDataNascimento());
            novoPaciente.setContato(novosDadosDoPaciente.getNome());
            novoPaciente.setEndereco(novosDadosDoPaciente.getEndereco());
            pacienteRepository.save(novoPaciente);
            return novoPaciente;
    }
        return null;
    }

    public void remove (String id) {
        Optional<Paciente> paciente = findByid(id);

        paciente.ifPresent(value -> pacienteRepository.delete(value));
    }
    public Optional<Paciente> findByid(String id) {
        return pacienteRepository.findById(id);
    }
}
