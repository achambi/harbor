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
    private String birthBate;

    @ApiModelProperty(required = true, value = "address")
    private String address;

    public PersonResponse(int id, String name, String lastName, Date birthBate, String address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        this.birthBate = formatter.format(birthBate);
        this.address = address;
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

    public String getBirthBate() {
        return birthBate;
    }

    public String getAddress() {
        return address;
    }
}
