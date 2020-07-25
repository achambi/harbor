package bo.com.mondongo.harbor.payload.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PersonRequest implements Serializable {
    @NotNull(message = "patient name is required.")
    @ApiModelProperty(required = true, value = "Patient name")
    @Size(max = 20, message = "patient name must have a maximum of 30 characters.")
    private String name;

    @NotNull
    @ApiModelProperty(required = true, value = "Patient last name")
    @Size(max = 20, message = "The account holder must have a maximum of 30 characters.")
    private String lastName;

    @NotNull
    @ApiModelProperty(required = true, value = "Patient address")
    @Size(max = 100, message = "The account holder must have a maximum of 30 characters.")
    private String address;

    @NotNull
    @ApiModelProperty(required = true, value = "Patient birth date")
    @Size(max = 10, message = "The birth date must have a maximum of 10 characters.")
    @Pattern(regexp = "^(((0)[0-9])|((1)[0-2]))(\\/)([0-2][0-9]|(3)[0-2])(\\/)\\d{4}$",
        message = "Date format is not valid.")
    private String birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
