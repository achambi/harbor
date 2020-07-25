package bo.com.mondongo.harbor.entity;

import bo.com.mondongo.harbor.exception.ResourceNotFoundException;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.text.ParseException;
import java.util.Set;

@Entity
@Table(name = "people")
public class Patient extends Person {
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Record> records;

    public Patient() {
    }

    public Patient(PersonRequest personRequest) throws ParseException {
        super(personRequest.getName(),
              personRequest.getLastName(),
              personRequest.getBirthDate(),
              personRequest.getAddress(),
              "patient"
        );
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    @Override
    public void verify() {
        if (!this.verify("patient")) {
            throw new ResourceNotFoundException("Patient does not found.");
        }
    }
}
