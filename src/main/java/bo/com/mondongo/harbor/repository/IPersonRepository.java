package bo.com.mondongo.harbor.repository;

import bo.com.mondongo.harbor.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.io.Serializable;

public interface IPersonRepository extends JpaRepository<Person, Serializable> {
}
