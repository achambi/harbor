package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Person;
import bo.com.mondongo.harbor.exception.InternalServerErrorException;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.PersonResponse;
import bo.com.mondongo.harbor.repository.IPersonRepository;
import java.util.Set;

public abstract class PersonService implements IPersonService {
    private final IPersonRepository personRepository;

    PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public MessageResponse update(int id, Person person, PersonRequest personRequest) {
        person.verify();
        try {
            person.update(personRequest);
            personRepository.save(person);
            return new MessageResponse(person.getCapitalizeType() + " updated");
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Set<PersonResponse> getAll(String type) {
        return personRepository.getAll(type);
    }

    @Override
    public MessageResponse delete(Person person) {
        person.inactive();
        personRepository.save(person);
        return new MessageResponse(person.getCapitalizeType() + " was deleted successfully.");
    }
}
