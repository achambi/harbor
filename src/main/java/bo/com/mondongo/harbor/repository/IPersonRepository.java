package bo.com.mondongo.harbor.repository;

import bo.com.mondongo.harbor.entity.Person;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import bo.com.mondongo.harbor.payload.response.PersonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.io.Serializable;
import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, Serializable> {

    @Query(value = "SELECT " +
        "new bo.com.mondongo.harbor.payload.response.PersonResponse(p.id, p.name, p.lastName," +
        "p.birthDate,p.address, h.name, p.type)" +
        " FROM Patient p INNER JOIN p.hospital h " +
        "WHERE p.type = :type AND p.active = true AND h.id = :hospitalId",
        countQuery = "SELECT count(p.id) FROM Patient p INNER JOIN p.hospital h " +
            "WHERE p.type = :type AND p.active = true AND h.id = :hospitalId")
    Page<PersonResponse> getAll(@Param("type") String type, @Param("hospitalId") int hospitalId, Pageable pageable);

    @Query("SELECT " +
        "new bo.com.mondongo.harbor.payload.response.OptionResponse( concat(p.name, p.lastName),p.id)" +
        " FROM Patient p INNER JOIN p.hospital h WHERE p.type = :type AND p.active = true AND h.id = :hospitalId")
    List<OptionResponse> getAll(@Param("hospitalId") int hospitalId, @Param("type") String type);
}
