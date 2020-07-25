package bo.com.mondongo.harbor.entity;

import bo.com.mondongo.harbor.payload.request.PatientRequest;
import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends EntityBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Record> records;

    public Patient(PatientRequest patientInsertDto) throws ParseException {
        this.name = patientInsertDto.getName();
        this.lastName = patientInsertDto.getLastName();
        this.address = patientInsertDto.getAddress();
        this.birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(patientInsertDto.getBirthDate());
    }

    public Patient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }
}
