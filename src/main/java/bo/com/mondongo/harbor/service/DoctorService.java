package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Doctor;
import bo.com.mondongo.harbor.entity.Specialty;
import bo.com.mondongo.harbor.exception.InternalServerErrorException;
import bo.com.mondongo.harbor.exception.ResourceNotFoundException;
import bo.com.mondongo.harbor.payload.request.DoctorRequest;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.repository.IDoctorRepository;
import bo.com.mondongo.harbor.repository.IPersonRepository;
import bo.com.mondongo.harbor.repository.ISpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService extends PersonService implements IDoctorService {
    private final IDoctorRepository doctorRepository;
    private final ISpecialityRepository specialityRepository;

    @Autowired
    public DoctorService(IPersonRepository personRepository, IDoctorRepository doctorRepository, ISpecialityRepository specialityRepository) {
        super(personRepository);
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public MessageResponse create(DoctorRequest doctorRequest) {
        try {
            Specialty specialty = specialityRepository.findById(doctorRequest.getSpecialityId()).orElseThrow(
                () -> new ResourceNotFoundException("Speciality does not exists."));
            doctorRepository.save(new Doctor(specialty, doctorRequest));
            return new MessageResponse("Doctor created successfully");
        } catch (Exception ex) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public MessageResponse update(int id, PersonRequest personRequest) {
        Doctor doctor = doctorRepository.findById(id)
                                        .orElseThrow(
                                            () -> new ResourceNotFoundException("Doctor does not exists."));
        return this.update(id, doctor, personRequest);
    }
}
