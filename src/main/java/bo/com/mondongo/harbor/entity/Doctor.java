package bo.com.mondongo.harbor.entity;

import bo.com.mondongo.harbor.payload.request.DoctorRequest;
import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "people")
public class Doctor extends Person implements Serializable {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "doctors_specialities", joinColumns = @JoinColumn(name = "doctor_id"),
        inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Specialty> specialities = new HashSet<>();

    public Doctor() {
    }

    public boolean verify() {
        return this.verify("doctor");
    }

    public Doctor(String name, String lastName, String birthDate, String address) throws ParseException {
        super(name, lastName, birthDate, address, "doctor");
    }

    public Doctor(Specialty specialty, DoctorRequest doctorRequest) throws ParseException {
        this(doctorRequest.getName(),
             doctorRequest.getLastName(),
             doctorRequest.getBirthDate(),
             doctorRequest.getAddress()
        );
        this.specialities.add(specialty);
    }

    public Set<Specialty> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Specialty> specialities) {
        this.specialities = specialities;
    }
}
