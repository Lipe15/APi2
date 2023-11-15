package Felipe.API2.service;

import Felipe.API2.HttpCliente.CepHttpCliente;
import Felipe.API2.Repository.PacienteRepository;
import Felipe.API2.dto.PacienteDTO;
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
    @Autowired
    CepHttpCliente cepHttpCliente;


    public List<Paciente> obterTodos() {

        return pacienteRepository.findAll();
    }

    public Paciente inserir(Paciente paciente) {
        pacienteRepository.insert(paciente);

        return paciente;

    }

    public void remove (String id) {
        Optional<Paciente> paciente = findByid(id);

        paciente.ifPresent(value -> pacienteRepository.delete(value));
    }
    public Optional<Paciente> findByid(String id) {

        return pacienteRepository.findById(id);
    }
    public Paciente atualizar(PacienteDTO pacienteDTO, String id) {
        Optional<Paciente> optionalPaciente = findByid(id);

        if (optionalPaciente.isPresent()) {
            Paciente pacienteExistente = optionalPaciente.get();

            pacienteExistente.setNome(pacienteDTO.getNome());
            pacienteExistente.setSobrenome(pacienteDTO.getSobrenome());
            pacienteExistente.setCpf(pacienteDTO.getCpf());
            pacienteExistente.setDataNascimento(pacienteDTO.getDataNascimento());
            pacienteExistente.setSexo(pacienteDTO.getSexo());
            pacienteExistente.setContato(pacienteDTO.getContato());

            return pacienteRepository.save(pacienteExistente);
        } else {
            return null;
        }
    }

}
