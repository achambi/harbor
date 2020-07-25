package bo.com.mondongo.harbor.entity;

import bo.com.mondongo.harbor.payload.request.RecordRequest;
import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@Entity
@Table(name = "records")
public class Record extends EntityBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "record_date", nullable = false)
    private Date recordDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    private Doctor doctor;

    public Record() {
    }

    public Record(Patient patient, Doctor doctor, RecordRequest recordRequest) throws ParseException {
        this.description = recordRequest.getDescription();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date parsed = format.parse(recordRequest.getDate());
        this.recordDate = new java.sql.Date(parsed.getTime());
        this.patient = patient;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
