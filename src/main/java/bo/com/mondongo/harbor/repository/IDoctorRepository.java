package bo.com.mondongo.harbor.repository;

import bo.com.mondongo.harbor.entity.Doctor;
import bo.com.mondongo.harbor.payload.response.PatientResponse;
import bo.com.mondongo.harbor.payload.response.SpecialityResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Serializable> {
    @Query("SELECT " +
        "new bo.com.mondongo.harbor.payload.response.SpecialityResponse(s.id, s.name, s.description)" +
        " FROM Doctor d INNER JOIN d.specialities s WHERE d.id = :id")
    List<SpecialityResponse> getSpecialities(@Param("id") int id);

    @Query("SELECT " +
        "new bo.com.mondongo.harbor.payload.response.PatientResponse(p.id, p.name , p.lastName, r.description, r.recordDate)" +
        " FROM Doctor d INNER JOIN d.records r INNER JOIN r.patient p WHERE d.id = :id")
    List<PatientResponse> getPatients(@Param("id") int id);
}
