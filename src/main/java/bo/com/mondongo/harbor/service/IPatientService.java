package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.payload.request.PatientRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;

public interface IPatientService {
    MessageResponse create(PatientRequest patient);
}
