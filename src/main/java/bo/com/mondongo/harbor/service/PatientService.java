package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Patient;
import bo.com.mondongo.harbor.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service("AccountService")
public class PatientService implements IPatientService {

    private final IPatientRepository patientRepository;

    @Autowired
    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> create(Patient patient) throws DataIntegrityViolationException {
        try {
            patientRepository.save(patient);
            Map<String, Object> response = new HashMap<>();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//
//    public List<AccountDTO> getAll() {
//        return accountConverter.FromAccountToAccountDto(accountRepository.findAll());
//    }
//
//    public List<AccountSimpleDTO> getSimpleList() {
//        return accountConverter.FromAccountToAccountSimpleDto(accountRepository.findAll());
//    }
}
