package Felipe.API2.Repository;

import Felipe.API2.entity.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends MongoRepository <Paciente, String> {

    Paciente save(Paciente paciente);
    List<Paciente> findByEndereco_Uf(String uf);

    boolean existsByCpf(String cpf);
}
