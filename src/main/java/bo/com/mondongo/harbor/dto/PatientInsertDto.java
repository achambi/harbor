package bo.com.mondongo.harbor.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PatientInsertDto implements Serializable {
    @NotNull(message = "patient name is required.")
    @ApiModelProperty(required = true, value = "Account holder")
    @Size(max = 20, message = "patient name must have a maximum of 30 characters.")
    private String name;

    @NotNull
    @ApiModelProperty(required = true, value = "Account holder")
    @Size(max = 20, message = "The account holder must have a maximum of 30 characters.")
    private String lastName;

    @NotNull
    @ApiModelProperty(required = true, value = "Account holder")
    @Size(max = 100, message = "The account holder must have a maximum of 30 characters.")
    private String address;

    @NotNull
    @ApiModelProperty(required = true, value = "Account holder")
    @Size(max = 10, message = "The birth date must have a maximum of 10 characters.")
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
