package bo.com.mondongo.harbor.repository;

import bo.com.mondongo.harbor.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository
public interface ISpecialityRepository extends JpaRepository<Specialty, Serializable> {
}
