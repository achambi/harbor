package bo.com.mondongo.harbor.payload.response;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonResponse implements Serializable {
    @ApiModelProperty(required = true, value = "id correlative")
    private int id;

    @ApiModelProperty(required = true, value = "name")
    private String name;

    @ApiModelProperty(required = true, value = "last name")
    private String lastName;

    @ApiModelProperty(required = true, value = "birth date")
    private String birthDate;

    @ApiModelProperty(required = true, value = "address")
    private String address;

    @ApiModelProperty(required = true, value = "hospital")
    private String hospital;

    @ApiModelProperty(required = true, value = "type")
    private String type;

    public PersonResponse(int id,
                          String name,
                          String lastName,
                          Date birthDate,
                          String address,
                          String hospital,
                          String type) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        this.birthDate = formatter.format(birthDate);
        this.address = address;
        this.hospital = hospital;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getHospital() {
        return hospital;
    }

    public String getType() {
        return type;
    }
}
