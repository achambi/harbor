package bo.com.mondongo.harbor.controller;

import bo.com.mondongo.harbor.payload.request.PatientRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.service.IPatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/patients")
@Api(value = "Operations pertaining to patients")
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @ApiOperation(value = "Create a account", response = MessageResponse.class)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public MessageResponse createAccount(@Valid @RequestBody PatientRequest patientRequest) {
        return patientService.create(patientRequest);
    }
}
