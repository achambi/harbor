package bo.com.mondongo.harbor.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class EntityBase {
    @Column(name = "created_date", nullable = false)
    private final LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "active", nullable = false)
    private boolean active;

    public EntityBase() {
        this.createdDate = LocalDateTime.now();
        this.active = true;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void inactive() {
        this.active = false;
        this.updatedDate = LocalDateTime.now();
    }
}
