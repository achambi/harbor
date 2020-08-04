package bo.com.mondongo.harbor.controller;

import bo.com.mondongo.harbor.payload.request.DoctorRequest;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.response.*;
import bo.com.mondongo.harbor.service.IDoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

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

    @ApiOperation(value = "Add speciality to a doctor", response = MessageResponse.class)
    @PatchMapping(value = "/{id}/speciality/{specialityId}", produces = "application/json", consumes = "application/json")
    public MessageResponse update(@PathVariable(value = "id") int id,
                                  @PathVariable(value = "specialityId") int specialityId) {
        return doctorService.addSpeciality(id, specialityId);
    }

    @ApiOperation(value = "Get specialities of a doctor", response = SpecialityResponse.class,
        responseContainer = "List")
    @GetMapping(value = "/{id}/specialities", produces = "application/json")
    public List<SpecialityResponse> getSpecialities(@PathVariable(value = "id") int id) {
        return doctorService.getSpecialities(id);
    }

    @ApiOperation(value = "Get patients of a doctor", response = PatientResponse.class,
        responseContainer = "List")
    @GetMapping(value = "/{id}/patients", produces = "application/json")
    public List<PatientResponse> getPatients(@PathVariable(value = "id") int id) {
        return doctorService.getPatients(id);
    }

    @ApiOperation(value = "Delete a doctor", response = MessageResponse.class)
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public MessageResponse delete(@PathVariable(value = "id") int id) {
        return doctorService.delete(id);
    }
}
