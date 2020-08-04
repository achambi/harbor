package bo.com.mondongo.harbor.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "hospitals")
public class Hospital extends EntityBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "description", length = 150, nullable = false)
    private String description;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private Set<Doctor> doctors;

    public Hospital(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Hospital() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }
}
