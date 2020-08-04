package bo.com.mondongo.harbor.controller;

import bo.com.mondongo.harbor.payload.request.PatientRequest;
import bo.com.mondongo.harbor.payload.request.PersonRequest;
import bo.com.mondongo.harbor.payload.request.RecordRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.PatientResponse;
import bo.com.mondongo.harbor.payload.response.PersonResponse;
import bo.com.mondongo.harbor.service.IPatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patients")
@Api(value = "Operations pertaining to patients")
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @ApiOperation(value = "Create a patient", response = MessageResponse.class)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public MessageResponse create(@Valid @RequestBody PatientRequest patientRequest) {
        return patientService.create(patientRequest);
    }

    @ApiOperation(value = "Update a patient", response = MessageResponse.class)
    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public MessageResponse update(@PathVariable(value = "id") int id,
                                  @Valid @RequestBody PersonRequest patientRequest) {
        return patientService.update(id, patientRequest);
    }

    @ApiOperation(value = "Add record to a patient", response = MessageResponse.class)
    @PostMapping(value = "/{id}/record", produces = "application/json", consumes = "application/json")
    public MessageResponse addRecord(@PathVariable(value = "id") int id,
                                     @Valid @RequestBody RecordRequest recordRequest) {
        return patientService.addRecord(id, recordRequest);
    }

    @ApiOperation(value = "Delete a patient", response = MessageResponse.class)
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public MessageResponse delete(@PathVariable(value = "id") int id) {
        return patientService.delete(id);
    }

    @ApiOperation(value = "Get all records of a patient", response = PersonResponse.class,
        responseContainer = "List")
    @GetMapping(value = "{id}/records", produces = "application/json")
    public List<PatientResponse> getAll(
        @PathVariable(value = "id") int id) {
        return patientService.getRecords(id);
    }
}
