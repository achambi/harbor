package bo.com.mondongo.harbor.payload.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class RecordRequest implements Serializable {
    @NotNull(message = "Doctor id can not be null.")
    @ApiModelProperty(required = true, value = "Doctor id")
    private int doctorId;

    @NotNull(message = "Record Description can not be null.")
    @ApiModelProperty(required = true, value = "Record description")
    @Size(max = 200, message = "record name must have a maximum of 200 characters.")
    private String description;

    @NotNull
    @ApiModelProperty(required = true, value = "Record date")
    @Size(max = 10, message = "The record date must have a maximum of 10 characters.")
    @Pattern(regexp = "^(((0)[0-9])|((1)[0-2]))(\\/)([0-2][0-9]|(3)[0-2])(\\/)\\d{4}$",
        message = "Date format is not valid.")
    private String date;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
