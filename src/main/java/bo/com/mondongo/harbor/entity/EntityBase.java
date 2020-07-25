package bo.com.mondongo.harbor.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class EntityBase {
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "active", nullable = false)
    private boolean active;

    public EntityBase() {
        this.createdDate = LocalDateTime.now();
        this.active = true;
    }
}
