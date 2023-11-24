package Felipe.API2.service;

import Felipe.API2.Exception.*;
import Felipe.API2.HttpCliente.CepHttpCliente;
import Felipe.API2.Repository.PacienteRepository;
import Felipe.API2.dto.Estado;
import Felipe.API2.dto.PacienteDTO;
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
    @Autowired
    CepHttpCliente cepHttpCliente;



    public List<Paciente> obterTodos() {

        return pacienteRepository.findAll();
    }

    public Paciente inserir(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
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
                                estado.setBairro(paciente.getEndereco().getBairro());
                                estado.setLocalidade(paciente.getEndereco().getLocalidade());
                                estado.setUf(paciente.getEndereco().getUf());
                                estado.calcularIdade(paciente.getDataNascimento());
                                estado.setCpf(paciente.getCpf());
                                return estado;
                            })
                            .collect(Collectors.toList());
                } else {
                    return Collections.emptyList();
                }
            } else {

                pacientes = pacienteRepository.findByEndereco_Uf(uf);

                if (!pacientes.isEmpty()) {

                    return pacientes.stream()
                            .map(paciente -> {
                                Estado estado = new Estado();
                                estado.setNome(paciente.getNome() + " " + paciente.getSobrenome());
                                estado.setBairro(paciente.getEndereco().getBairro());
                                estado.setLocalidade(paciente.getEndereco().getLocalidade());
                                estado.setUf(paciente.getEndereco().getUf());
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

    public Paciente atualizar(PacienteDTO pacienteDTO, String id) {
        Optional<Paciente> optionalPaciente = findByid(id);

        if (optionalPaciente.isPresent()) {
            Paciente pacienteExistente = optionalPaciente.get();

            if (!pacienteDTO.getCpf().equals(pacienteExistente.getCpf())) {

                if (pacienteRepository.existsByCpf(pacienteDTO.getCpf())) {
                    throw new CpfDuplicadoException("Não é possível atualizar o CPF para um já existente.");
                }
            }
            if (pacienteRepository.existsByCpf(pacienteDTO.getCpf())) {
                throw new CpfDuplicadoException("Já existe um paciente com o mesmo número de CPF: " + pacienteDTO.getCpf());
            }
            LocalDate dataNascimento = pacienteDTO.getDataNascimento();
            if (dataNascimento != null && dataNascimento.isAfter(LocalDate.now())) {
                throw new DataNascimentoFuturaException("A data de nascimento não pode ser no futuro.");
            }
            if (!"masculino".equalsIgnoreCase(pacienteDTO.getSexo()) && !"feminino".equalsIgnoreCase(pacienteDTO.getSexo())) {
                throw new SexoInvalidoException("O sexo do paciente deve ser 'masculino' ou 'feminino' " );
            }
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