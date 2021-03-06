package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Speciality;
import bo.com.mondongo.harbor.payload.request.GeneralRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ISpecialityService {
    Page<Speciality> getAll(int page, int pageLimit);
    List<OptionResponse> getAll();
    MessageResponse create(GeneralRequest specialityRequest);
}
