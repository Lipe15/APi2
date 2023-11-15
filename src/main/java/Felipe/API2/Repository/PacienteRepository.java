package Felipe.API2.Repository;

import Felipe.API2.entity.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PacienteRepository extends MongoRepository <Paciente, String> {
    Paciente save(Paciente paciente);


}
