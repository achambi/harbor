package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Doctor;
import bo.com.mondongo.harbor.entity.Hospital;
import bo.com.mondongo.harbor.entity.Patient;
import bo.com.mondongo.harbor.entity.Record;
import bo.com.mondongo.harbor.exception.InternalServerErrorException;
import bo.com.mondongo.harbor.exception.ResourceNotFoundException;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.request.RecordRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.PatientResponse;
import bo.com.mondongo.harbor.payload.response.PersonResponse;
import bo.com.mondongo.harbor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientService extends PersonService implements IPatientService {

    private final IPatientRepository patientRepository;
    private final IDoctorRepository doctorRepository;
    private final IRecordRepository recordRepository;
    private final IHospitalRepository hospitalRepository;

    @Autowired
    public PatientService(IPersonRepository personService,
                          IPatientRepository patientRepository,
                          IRecordRepository recordRepository,
                          IDoctorRepository doctorRepository,
                          IHospitalRepository hospitalRepository) {
        super(personService);
        this.patientRepository = patientRepository;
        this.recordRepository = recordRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    @Override
    public MessageResponse create(PersonRequest patient) {
        try {
            Hospital hospital = hospitalRepository.findById(patient.getHospitalId())
                                                  .orElseThrow(
                                                      () -> new ResourceNotFoundException("Hospital does not found."));
            patientRepository.save(new Patient(patient, hospital));
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

        Doctor doctor = doctorRepository.findById(recordRequest.getDoctorId())
                                        .orElseThrow(
                                            () -> new ResourceNotFoundException("Doctor does not found."));
        patient.verify();
        doctor.verify();
        patient.verifyActive();
        doctor.verifyActive();
        try {
            recordRepository.save(new Record(patient, doctor, recordRequest));
            return new MessageResponse("Record Added");
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public List<PatientResponse> getRecords(int id) {
        Patient patient = patientRepository.findById(id)
                                           .orElseThrow(
                                               () -> new ResourceNotFoundException("Patient does not exists."));
        patient.verify();
        return this.recordRepository.getRecordsByPatient(id);
    }

    @Override
    public MessageResponse update(int id, PersonRequest personRequest) {
        Patient patient = patientRepository.findById(id)
                                           .orElseThrow(
                                               () -> new ResourceNotFoundException("Patient does not exists."));
        return this.update(id, patient, personRequest);
    }

    @Override
    public Page<PersonResponse> getAll(int hospitalId, int page, int limit) {
        return this.getAll("patient", hospitalId, page, limit);
    }

    @Override
    public MessageResponse delete(int id) {
        Patient patient = patientRepository.findById(id)
                                           .orElseThrow(
                                               () -> new ResourceNotFoundException("Patient does not exists."));
        patient.verify();
        return this.delete(patient);
    }
}
