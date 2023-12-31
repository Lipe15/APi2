package Felipe.API2.service;

import Felipe.API2.Exception.*;
import Felipe.API2.Repository.PacienteRepository;
import Felipe.API2.dto.Estado;

import Felipe.API2.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;




    public List<Paciente> obterTodos() {

        return pacienteRepository.findAll();
    }

    public Paciente inserir(Paciente paciente) {
        if (pacienteRepository.existsByCpf(paciente.getCpf())) {
            throw new CpfDuplicadoException("Já existe um paciente com o mesmo número de CPF: " + paciente.getCpf());
        }
        LocalDate dataNascimento = paciente.getDataNascimento();
        if (dataNascimento != null && dataNascimento.isAfter(LocalDate.now())) {
            throw new DataNascimentoFuturaException("A data de nascimento não pode ser no futuro.");
        }
        if (!"masculino".equalsIgnoreCase(paciente.getSexo()) && !"feminino".equalsIgnoreCase(paciente.getSexo())) {
            throw new SexoInvalidoException("O sexo do paciente deve ser 'masculino' ou 'feminino' ");
        }
        try {
            pacienteRepository.insert(paciente);
            return paciente;
        } catch (Exception ex) {
            throw new PacienteNotFoundException("Não foi possível inserir o paciente.");
        }

    }


    public void remove(String id) {
        Optional<Paciente> paciente = findByid(id);

        if (paciente.isPresent()) {
            pacienteRepository.delete(paciente.get());

        } else {
            throw new PacienteNotFoundException("Paciente não encontrado com o ID: " + id);
        }
    }

    public Optional<Paciente> findByid(String id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        if (pacienteOptional.isPresent()) {
            return pacienteOptional;
        } else {
            throw new PacienteNotFoundException("Paciente não encontrado com o ID: " + id);
        }
    }
    public Optional<Paciente> obterPacientesPorCPF(String cpf) {
        Optional<Paciente> pacienteOptional = Optional.ofNullable(pacienteRepository.findByCpf(cpf));
        if(pacienteOptional.isPresent()){
            return pacienteOptional;

        }else {
            throw new UfNotFoundException("Não foi encontrado paciente para cpf identificado: " + cpf);
        }


    }

    public List<Estado> obterPacientesPorUf(String uf) {
        try {
            List<Paciente> pacientes;

            if (StringUtils.isEmpty(uf)) {
                pacientes = pacienteRepository.findAll();
                if (!pacientes.isEmpty()) {

                    return pacientes.stream()
                            .map(paciente -> {
                                Estado estado = new Estado();
                                estado.setNome(paciente.getNome() + " " + paciente.getSobrenome());
                                estado.setBairro(paciente.getBairro());
                                estado.setLocalidade(paciente.getLocalidade());
                                estado.setUf(paciente.getUf());
                                estado.calcularIdade(paciente.getDataNascimento());
                                estado.setCpf(paciente.getCpf());
                                return estado;
                            })
                            .collect(Collectors.toList());
                } else {
                    return Collections.emptyList();
                }
            } else {

                pacientes = pacienteRepository.findByUf(uf);

                if (!pacientes.isEmpty()) {

                    return pacientes.stream()
                            .map(paciente -> {
                                Estado estado = new Estado();
                                estado.setNome(paciente.getNome() + " " + paciente.getSobrenome());
                                estado.setBairro(paciente.getBairro());
                                estado.setLocalidade(paciente.getLocalidade());
                                estado.setUf(paciente.getUf());
                                estado.calcularIdade(paciente.getDataNascimento());
                                estado.setCpf(paciente.getCpf());
                                return estado;
                            })
                            .collect(Collectors.toList());
                } else {
                    return Collections.emptyList();
                }
            }


        } catch (Exception ex) {
            throw new UfNotFoundException("Não foram encontrados pacientes para a UF especificada: " + uf);
        }


    }

    public Paciente atualizar(Paciente paciente, String id) {
        Optional<Paciente> optionalPaciente = findByid(id);

        if (optionalPaciente.isPresent()) {
            Paciente pacienteExistente = optionalPaciente.get();

            if (!paciente.getCpf().equals(pacienteExistente.getCpf())) {

                if (pacienteRepository.existsByCpf(paciente.getCpf())) {
                    throw new CpfDuplicadoException("Não é possível atualizar o CPF para um já existente.");
                }
            }
            if (pacienteRepository.existsByCpf(paciente.getCpf())) {
                throw new CpfDuplicadoException("Já existe um paciente com o mesmo número de CPF: " + paciente.getCpf());
            }
            LocalDate dataNascimento = paciente.getDataNascimento();
            if (dataNascimento != null && dataNascimento.isAfter(LocalDate.now())) {
                throw new DataNascimentoFuturaException("A data de nascimento não pode ser no futuro.");
            }
            if (!"masculino".equalsIgnoreCase(paciente.getSexo()) && !"feminino".equalsIgnoreCase(paciente.getSexo())) {
                throw new SexoInvalidoException("O sexo do paciente deve ser 'masculino' ou 'feminino' " );
            }
            pacienteExistente.setNome(paciente.getNome());
            pacienteExistente.setSobrenome(paciente.getSobrenome());
            pacienteExistente.setCpf(paciente.getCpf());
            pacienteExistente.setDataNascimento(paciente.getDataNascimento());
            pacienteExistente.setSexo(paciente.getSexo());
            pacienteExistente.setLocalidade(paciente.getLocalidade());
            pacienteExistente.setBairro(paciente.getBairro());
            pacienteExistente.setUf(paciente.getUf());


            return pacienteRepository.save(pacienteExistente);
        } else {
            return null;
        }

    }
}