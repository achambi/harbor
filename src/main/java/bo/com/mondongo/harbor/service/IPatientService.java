package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Patient;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface IPatientService {
    ResponseEntity<Map<String, Object>> create(Patient patient);
}
