package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Doctor;
import bo.com.mondongo.harbor.entity.Hospital;
import bo.com.mondongo.harbor.entity.Speciality;
import bo.com.mondongo.harbor.exception.InternalServerErrorException;
import bo.com.mondongo.harbor.exception.ResourceNotFoundException;
import bo.com.mondongo.harbor.payload.request.DoctorRequest;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.response.*;
import bo.com.mondongo.harbor.repository.IDoctorRepository;
import bo.com.mondongo.harbor.repository.IHospitalRepository;
import bo.com.mondongo.harbor.repository.IPersonRepository;
import bo.com.mondongo.harbor.repository.ISpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DoctorService extends PersonService implements IDoctorService {
    private final IDoctorRepository doctorRepository;
    private final ISpecialityRepository specialityRepository;
    private final IHospitalRepository hospitalRepository;

    @Autowired
    public DoctorService(IPersonRepository personRepository, IDoctorRepository doctorRepository,
                         ISpecialityRepository specialityRepository, IHospitalRepository hospitalRepository) {
        super(personRepository);
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public MessageResponse create(DoctorRequest doctorRequest) {
        try {
            Speciality specialty = specialityRepository.findById(doctorRequest.getSpecialityId())
                                                       .orElseThrow(() -> new ResourceNotFoundException(
                                                           "Speciality does not exists."));
            Hospital hospital = hospitalRepository.findById(doctorRequest.getHospitalId())
                                                  .orElseThrow(
                                                      () -> new ResourceNotFoundException("Hospital does not exists."));
            doctorRepository.save(new Doctor(hospital, specialty, doctorRequest));
            return new MessageResponse("Doctor created successfully");
        } catch (Exception ex) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    @Transactional
    public MessageResponse addSpeciality(int id, int specialityId) {
        Doctor doctor = doctorRepository.findById(id)
                                        .orElseThrow(
                                            () -> new ResourceNotFoundException("Doctor does not exists."));
        Speciality speciality = specialityRepository.findById(specialityId)
                                                    .orElseThrow(
                                                        () -> new ResourceNotFoundException(
                                                            "Speciality does not exists."));
        doctor.addSpeciality(speciality);
        doctorRepository.save(doctor);
        return new MessageResponse("Speciality Added");
    }

    @Override
    public List<SpecialityResponse> getSpecialities(int id) {
        return doctorRepository.getSpecialities(id);
    }

    @Override
    public List<PatientResponse> getPatients(int id) {
        return doctorRepository.getPatients(id);
    }

    @Override
    public List<OptionResponse> getAllByHospital(int hospitalId) {
        return this.personRepository.getAll(hospitalId, "doctor");
    }

    @Override
    public MessageResponse update(int id, PersonRequest personRequest) {
        Doctor doctor = doctorRepository.findById(id)
                                        .orElseThrow(
                                            () -> new ResourceNotFoundException("Doctor does not exists."));
        return this.update(id, doctor, personRequest);
    }

    @Override
    public Page<PersonResponse> getAll(int hospitalId, int page, int limit) {
        return this.getAll("doctor", hospitalId, page, limit);
    }

    @Override
    public MessageResponse delete(int id) {
        Doctor doctor = doctorRepository.findById(id)
                                        .orElseThrow(
                                            () -> new ResourceNotFoundException("Doctor does not exists."));
        doctor.verify();
        return this.delete(doctor);
    }
}
