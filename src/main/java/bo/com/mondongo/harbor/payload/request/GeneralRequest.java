package bo.com.mondongo.harbor.payload.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class GeneralRequest implements Serializable {
    @NotNull(message = "Name id can not be null.")
    @ApiModelProperty(required = true, value = "Name")
    @Size(max = 20, message = "record name must have a maximum of 200 characters.")
    private String name;

    @NotNull(message = "Description can not be null.")
    @ApiModelProperty(required = true, value = "Description")
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
