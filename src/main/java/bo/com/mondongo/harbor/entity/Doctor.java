package bo.com.mondongo.harbor.entity;

import bo.com.mondongo.harbor.exception.ResourceNotFoundException;
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
    private Set<Speciality> specialities = new HashSet<>();

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Set<Record> records;

    public Doctor() {
    }

    @Override
    public void verify() {
        if (!this.verify("doctor")) {
            throw new ResourceNotFoundException("Doctor does not found.");
        }
    }

    public Doctor(String name, String lastName, String birthDate, String address, Hospital hospital) throws ParseException {
        super(name, lastName, birthDate, address, "doctor", hospital);
    }

    public Doctor(Hospital hospital, Speciality specialty, DoctorRequest doctorRequest) throws ParseException {
        this(doctorRequest.getName(),
             doctorRequest.getLastName(),
             doctorRequest.getBirthDate(),
             doctorRequest.getAddress(),
             hospital
        );
        this.specialities.add(specialty);
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void addSpeciality(Speciality speciality){
        specialities.add(speciality);
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }

    public Set<Record> getRecords() {
        return records;
    }
}
