package bo.com.mondongo.harbor.payload.response;

import io.swagger.annotations.ApiModelProperty;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientResponse {
    @ApiModelProperty(required = true, value = "id correlative")
    private int id;

    @ApiModelProperty(required = true, value = "fullName")
    private String fullName;

    @ApiModelProperty(required = true, value = "note")
    private String note;

    @ApiModelProperty(required = true, value = "recordDate")
    private String recordDate;

    public PatientResponse(int id, String name, String lastName, String note, Date recordDate) {
        this.id = id;
        this.fullName = name + " " + lastName;
        this.note = note;
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        this.recordDate = formatter.format(recordDate);
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNote() {
        return note;
    }

    public String getRecordDate() {
        return recordDate;
    }
}
