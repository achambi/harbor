package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Person;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;

public interface IPersonService {
    MessageResponse update(int id, Person person, PersonRequest personRequest);
    MessageResponse update(int id, PersonRequest personRequest);
}
