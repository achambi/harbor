package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Person;
import bo.com.mondongo.harbor.exception.InternalServerErrorException;
import bo.com.mondongo.harbor.exception.ResourceNotFoundException;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.repository.IPersonRepository;

public abstract class PersonService implements IPersonService {
    private final IPersonRepository personRepository;

    PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponse update(int id, Person person, PersonRequest personRequest) {
        if (!person.verify()) {
            throw new ResourceNotFoundException("The person is not a " + person.getCapitalizeType());
        }
        try {
            person.update(personRequest);
            personRepository.save(person);
            return new MessageResponse(person.getCapitalizeType() + " updated");
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }
}
