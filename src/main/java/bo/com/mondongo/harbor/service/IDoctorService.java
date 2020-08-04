package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.payload.request.DoctorRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import bo.com.mondongo.harbor.payload.response.PatientResponse;
import bo.com.mondongo.harbor.payload.response.SpecialityResponse;
import java.util.List;

public interface IDoctorService extends IPersonService {
    MessageResponse create(DoctorRequest doctorRequest);
    MessageResponse addSpeciality(int id, int specialityId);
    List<SpecialityResponse> getSpecialities(int id);
    List<PatientResponse> getPatients(int id);
    List<OptionResponse> getAllByHospital(int hospitalId);
}

