package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.payload.request.DoctorRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;

public interface IDoctorService extends IPersonService {
    MessageResponse create(DoctorRequest doctorRequest);
}
