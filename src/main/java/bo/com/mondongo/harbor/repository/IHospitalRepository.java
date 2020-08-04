package bo.com.mondongo.harbor.repository;

import bo.com.mondongo.harbor.entity.Hospital;
import bo.com.mondongo.harbor.payload.response.HospitalResponse;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;
@Repository
public interface IHospitalRepository extends PagingAndSortingRepository<Hospital, Serializable> {
    @Query("SELECT " +
        "new bo.com.mondongo.harbor.payload.response.OptionResponse(h.name, h.id)" +
        " FROM Hospital h")
    List<OptionResponse> getAll();

    @Query(value = "SELECT " +
        "new bo.com.mondongo.harbor.payload.response.HospitalResponse(h.id, h.name, h.description) " +
        "FROM Hospital h where h.active = true ",
        countQuery = "SELECT count(h.id) from Hospital h where h.active = true ")
    Page<HospitalResponse> getAll(Pageable pageable);
}
