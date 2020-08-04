package bo.com.mondongo.harbor.payload.response;

import io.swagger.annotations.ApiModelProperty;

public abstract class GeneralResponse {
    @ApiModelProperty(required = true, value = "id correlative")
    private int id;

    @ApiModelProperty(required = true, value = "name")
    private String name;

    @ApiModelProperty(required = true, value = "description")
    private String description;

    public GeneralResponse(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
