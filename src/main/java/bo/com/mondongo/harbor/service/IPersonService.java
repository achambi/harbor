package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Person;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.response.PersonResponse;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import java.util.Set;

public interface IPersonService {
    MessageResponse update(int id, Person person, PersonRequest personRequest);
    MessageResponse update(int id, PersonRequest personRequest);
    Set<PersonResponse> getAll(String type);
    Set<PersonResponse> getAll();
    MessageResponse delete(Person person);
    MessageResponse delete(int id);
}
