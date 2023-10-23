package Felipe.API2.service;

import Felipe.API2.Repository.PacienteRepository;
import Felipe.API2.entity.Paciente;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository alunoRepository;
    private final List<Paciente> listaDePacientes = new ArrayList<>();
    public List<Paciente> obterTodos() {

        return listaDePacientes;
    }

    public Paciente inserir(Paciente paciente) {
        alunoRepository.insert(paciente);

        return paciente;

    }
    public Paciente atualizar(String cpf,Paciente novosDadosDoPaciente) {
        Paciente paciente = selecionarPacientePeloCpf(cpf);
        if(paciente != null){
            BeanUtils.copyProperties(novosDadosDoPaciente, paciente);
    }
        return paciente;
    }

    public void remove (String cpf) {
        Paciente paciente = selecionarPacientePeloCpf(cpf);
        if(paciente != null){
           listaDePacientes.remove(paciente);
        }
    }
    public Paciente selecionarPacientePeloCpf(String cpf) {
        Paciente pacienteSelecionado = null;

        for (Paciente paciente : listaDePacientes) {
            if (paciente.getCpf().equals(cpf)) {
                pacienteSelecionado = paciente;
            }
        }
        return pacienteSelecionado;
    }
}
