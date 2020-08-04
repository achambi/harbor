package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Speciality;
import bo.com.mondongo.harbor.payload.request.GeneralRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import bo.com.mondongo.harbor.repository.ISpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecialityService implements ISpecialityService {
    final private ISpecialityRepository specialityRepository;

    @Autowired
    public SpecialityService(ISpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Page<Speciality> getAll(int page, int limit) {
        return specialityRepository.findAll(PageRequest.of(page, limit));
    }

    @Override
    public List<OptionResponse> getAll() {
        return specialityRepository.getAll();
    }

    @Override
    public MessageResponse create(GeneralRequest specialityRequest) {
        specialityRepository.save(new Speciality(specialityRequest.getName(), specialityRequest.getDescription()));
        return new MessageResponse("Speciality Created");
    }
}
