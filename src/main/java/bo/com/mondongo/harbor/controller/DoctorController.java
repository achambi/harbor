package bo.com.mondongo.harbor.controller;

import bo.com.mondongo.harbor.payload.request.DoctorRequest;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.response.PersonResponse;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.service.IDoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/doctors")
@Api(value = "Operations pertaining to patients")
public class DoctorController {

    private final IDoctorService doctorService;

    @Autowired
    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @ApiOperation(value = "Create a doctor", response = MessageResponse.class)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public MessageResponse create(@Valid @RequestBody DoctorRequest doctorRequest) {
        return doctorService.create(doctorRequest);
    }

    @ApiOperation(value = "Update a doctor", response = MessageResponse.class)
    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public MessageResponse update(@PathVariable(value = "id") int id,
                                  @Valid @RequestBody PersonRequest patientRequest) {
        return doctorService.update(id, patientRequest);
    }

    @ApiOperation(value = "Get all doctor", response = PersonResponse.class, responseContainer = "List")
    @GetMapping(produces = "application/json")
    public Set<PersonResponse> getAll() {
        return doctorService.getAll();
    }

    @ApiOperation(value = "Delete a doctor", response = MessageResponse.class)
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public MessageResponse delete(@PathVariable(value = "id") int id) {
        return doctorService.delete(id);
    }
}
