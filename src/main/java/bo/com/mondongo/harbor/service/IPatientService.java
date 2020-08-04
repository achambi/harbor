package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.request.RecordRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.PatientResponse;
import java.util.List;

public interface IPatientService extends IPersonService {
    MessageResponse create(PersonRequest patient);
    MessageResponse addRecord(int patientId, RecordRequest recordRequest);
    List<PatientResponse> getRecords(int id);
}
