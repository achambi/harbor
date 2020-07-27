package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Speciality;
import bo.com.mondongo.harbor.payload.request.SpecialityRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import org.springframework.data.domain.Page;

public interface ISpecialityService {
    Page<Speciality> getAll(int page, int pageLimit);
    MessageResponse create(SpecialityRequest specialityRequest);
}
