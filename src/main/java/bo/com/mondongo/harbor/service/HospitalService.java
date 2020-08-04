package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Hospital;
import bo.com.mondongo.harbor.payload.request.GeneralRequest;
import bo.com.mondongo.harbor.payload.response.HospitalResponse;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import bo.com.mondongo.harbor.repository.IHospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HospitalService implements IHospitalService {
    final private IHospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(IHospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Page<HospitalResponse> getAll(int page, int limit) {
        return hospitalRepository.getAll(PageRequest.of(page, limit));
    }

    @Override
    public List<OptionResponse> getAll() {
        return hospitalRepository.getAll();
    }

    @Override
    public MessageResponse create(GeneralRequest hospitalRequest) {
        hospitalRepository.save(new Hospital(hospitalRequest.getName(),
                                             hospitalRequest.getDescription()
        ));
        return new MessageResponse("Hospital Created");
    }
}
