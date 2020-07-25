package bo.com.mondongo.harbor.payload.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DoctorRequest extends PersonRequest implements Serializable {

    @NotNull(message = "Speciality id is required.")
    @ApiModelProperty(required = true, value = "Speciality id")
    private int specialityId;

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }
}
