package bo.com.mondongo.harbor.entity;

import bo.com.mondongo.harbor.exception.ResourceNotFoundException;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@MappedSuperclass
public abstract class Person extends EntityBase implements Serializable {
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

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id", nullable = false)
    private Hospital hospital;

    public Person() {

    }

    public Person(String name,
                  String lastName,
                  String birthDate,
                  String address,
                  String type,
                  Hospital hospital) throws ParseException {
        this.name = name;
        this.lastName = lastName;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date birthDateParsed = format.parse(birthDate);
        this.birthDate = new java.sql.Date(birthDateParsed.getTime());
        this.address = address;
        this.type = type;
        this.hospital = hospital;
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

    public String getType() {
        return type;
    }

    public String getCapitalizeType() {
        return StringUtils.capitalize(this.type);
    }

    public void update(PersonRequest personRequest) throws ParseException {
        this.name = personRequest.getName();
        this.lastName = personRequest.getLastName();
        this.address = personRequest.getAddress();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date birthDateParsed = format.parse(personRequest.getBirthDate());
        this.birthDate = new java.sql.Date(birthDateParsed.getTime());
    }

    public boolean verify(String type) {
        return this.getType().equals(type);
    }

    public abstract void verify();

    public void verifyActive() {
        if (!this.isActive()) {
            throw new ResourceNotFoundException("Patient does not found.");
        }
    }
}
