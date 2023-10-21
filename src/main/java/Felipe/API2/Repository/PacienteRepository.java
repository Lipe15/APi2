package Felipe.API2.Repository;

import Felipe.API2.Entity.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository  extends MongoRepository<Paciente, String> {
}
