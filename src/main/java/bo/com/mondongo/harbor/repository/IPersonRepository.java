package bo.com.mondongo.harbor.repository;

import bo.com.mondongo.harbor.entity.Person;
import bo.com.mondongo.harbor.payload.response.PersonResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.io.Serializable;
import java.util.Set;

public interface IPersonRepository extends JpaRepository<Person, Serializable> {

    @Query("SELECT " +
        "new bo.com.mondongo.harbor.payload.response.PersonResponse(p.id, p.name, p.lastName,p.birthDate,p.address)" +
        " FROM Patient p WHERE p.type= :type AND p.active = true")
    Set<PersonResponse> getAll(@Param("type") String type);
}
