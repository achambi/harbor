package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.payload.request.GeneralRequest;
import bo.com.mondongo.harbor.payload.response.HospitalResponse;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import org.springframework.data.domain.Page;
import java.util.List;

public interface IHospitalService {
    Page<HospitalResponse> getAll(int page, int limit);
    List<OptionResponse> getAll();
    MessageResponse create(GeneralRequest hospitalRequest);
}
