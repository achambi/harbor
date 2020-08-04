package bo.com.mondongo.harbor.repository;

import bo.com.mondongo.harbor.entity.Record;
import bo.com.mondongo.harbor.payload.response.PatientResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface IRecordRepository extends JpaRepository<Record, Serializable> {
    @Query("SELECT " +
        "new bo.com.mondongo.harbor.payload.response.PatientResponse(d.id, d.name , d.lastName," +
        " r.description, r.recordDate)" +
        " FROM Patient p INNER JOIN p.records r INNER JOIN r.doctor d WHERE p.id = :id")
    List<PatientResponse> getRecordsByPatient(@Param("id") int id);
}
