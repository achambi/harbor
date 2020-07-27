package bo.com.mondongo.harbor.payload.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class SpecialityRequest implements Serializable {
    @NotNull(message = "Speciality id can not be null.")
    @ApiModelProperty(required = true, value = "Speciality name")
    @Size(max = 20, message = "record name must have a maximum of 200 characters.")
    private String name;

    @NotNull(message = "Speciality Description can not be null.")
    @ApiModelProperty(required = true, value = "Record description")
    @Size(max = 150, message = "record name must have a maximum of 200 characters.")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
