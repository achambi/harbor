package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Person;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.PersonResponse;
import org.springframework.data.domain.Page;

public interface IPersonService {
    MessageResponse update(int id, Person person, PersonRequest personRequest);
    MessageResponse update(int id, PersonRequest personRequest);
    Page<PersonResponse> getAll(String type, int hospitalId, int page, int limit);
    Page<PersonResponse> getAll(int hospitalId, int page, int limit);
    MessageResponse delete(Person person);
    MessageResponse delete(int id);
}
