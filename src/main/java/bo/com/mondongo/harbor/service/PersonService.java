package bo.com.mondongo.harbor.service;

import bo.com.mondongo.harbor.entity.Person;
import bo.com.mondongo.harbor.exception.InternalServerErrorException;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import bo.com.mondongo.harbor.payload.response.PersonResponse;
import bo.com.mondongo.harbor.repository.IPersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class PersonService implements IPersonService {
    protected final IPersonRepository personRepository;

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
    public Page<PersonResponse> getAll(String type, int hospitalId, int page, int limit) {
        return personRepository.getAll(type, hospitalId, PageRequest.of(page, limit));
    }

    @Override
    public MessageResponse delete(Person person) {
        person.inactive();
        personRepository.save(person);
        return new MessageResponse(person.getCapitalizeType() + " was deleted successfully.");
    }
}
