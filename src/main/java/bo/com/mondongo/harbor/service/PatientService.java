package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Patient;
import bo.com.mondongo.harbor.entity.Record;
import bo.com.mondongo.harbor.exception.InternalServerErrorException;
import bo.com.mondongo.harbor.exception.ResourceNotFoundException;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.request.RecordRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.repository.IPatientRepository;
import bo.com.mondongo.harbor.repository.IPersonRepository;
import bo.com.mondongo.harbor.repository.IRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class PatientService extends PersonService implements IPatientService {

    private final IPatientRepository patientRepository;
    private final IRecordRepository recordRepository;

    @Autowired
    public PatientService(IPersonRepository personService, IPatientRepository patientRepository, IRecordRepository recordRepository) {
        super(personService);
        this.patientRepository = patientRepository;
        this.recordRepository = recordRepository;
    }

    @Transactional
    @Override
    public MessageResponse create(PersonRequest patient) {
        try {
            patientRepository.save(new Patient(patient));
            return new MessageResponse("Patient created successfully");
        } catch (Exception ex) {
            throw new InternalServerErrorException();
        }
    }

    @Transactional
    @Override
    public MessageResponse addRecord(int patientId, RecordRequest recordRequest) {
        Patient patient = patientRepository.findById(patientId)
                                           .orElseThrow(
                                               () -> new ResourceNotFoundException("Patient does not found."));
        try {
            if (!patient.verify()) {
                throw new ResourceNotFoundException("Patient does not found.");
            }
            recordRepository.save(new Record(patient, recordRequest));
            return new MessageResponse("Record Added");
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public MessageResponse update(int id, PersonRequest personRequest) {
        Patient patient = patientRepository.findById(id)
                                         .orElseThrow(
                                             () -> new ResourceNotFoundException("Patient does not exists."));
        return this.update(id, patient, personRequest);
    }
}
