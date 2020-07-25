package bo.com.mondongo.harbor.controller;

import bo.com.mondongo.harbor.dto.PatientInsertDto;
import bo.com.mondongo.harbor.entity.Patient;
import bo.com.mondongo.harbor.service.IPatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/patients")
@Api(value = "Operations pertaining to accounts")
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @ApiOperation(value = "Create a account", response = ResponseEntity.class)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> createAccount(@Valid @RequestBody PatientInsertDto patientInsertDto) throws ParseException {

        return patientService.create(new Patient(patientInsertDto));
    }
}
