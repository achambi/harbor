package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Patient;
import bo.com.mondongo.harbor.exception.InternalServerErrorException;
import bo.com.mondongo.harbor.payload.request.PatientRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class PatientService implements IPatientService {

    private final IPatientRepository patientRepository;

    @Autowired
    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    @Override
    public MessageResponse create(PatientRequest patient) {
        try {
            patientRepository.save(new Patient(patient));
            return new MessageResponse("Patient created successfully");
        } catch (Exception ex) {
            throw new InternalServerErrorException();
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
