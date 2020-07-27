package bo.com.mondongo.harbor.repository;

import bo.com.mondongo.harbor.entity.Speciality;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository
public interface ISpecialityRepository extends PagingAndSortingRepository<Speciality, Serializable> {
}
