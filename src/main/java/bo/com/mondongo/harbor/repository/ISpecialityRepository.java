package bo.com.mondongo.harbor.repository;

import bo.com.mondongo.harbor.entity.Speciality;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface ISpecialityRepository extends PagingAndSortingRepository<Speciality, Serializable> {
    @Query("SELECT " +
        "new bo.com.mondongo.harbor.payload.response.OptionResponse(s.name, s.id)" +
        " FROM Speciality s")
    List<OptionResponse> getAll();
}
