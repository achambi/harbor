package bo.com.mondongo.harbor.controller;

import bo.com.mondongo.harbor.payload.request.GeneralRequest;
import bo.com.mondongo.harbor.payload.response.HospitalResponse;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import bo.com.mondongo.harbor.payload.response.PersonResponse;
import bo.com.mondongo.harbor.service.IDoctorService;
import bo.com.mondongo.harbor.service.IHospitalService;
import bo.com.mondongo.harbor.service.IPatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hospitals")
@Api(value = "Operations pertaining to hospitals")
public class HospitalController {

    private final IHospitalService hospitalService;
    private final IDoctorService doctorService;
    private final IPatientService patientService;

    @Autowired
    public HospitalController(IHospitalService hospitalService,
                              IDoctorService doctorService, IPatientService patientService) {
        this.hospitalService = hospitalService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @ApiOperation(value = "Create a patient", response = MessageResponse.class)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public MessageResponse create(@Valid @RequestBody GeneralRequest hospitalRequest) {
        return hospitalService.create(hospitalRequest);
    }

    @ApiOperation(value = "Get all patients", response = Page.class)
    @GetMapping(value = "/{page}/{limit}", produces = "application/json")
    public Page<HospitalResponse> getAll(@PathVariable(value = "page") int page,
                                         @PathVariable(value = "limit") int limit) {
        return hospitalService.getAll(page, limit);
    }

    @ApiOperation(value = "Get all patients", response = OptionResponse.class, responseContainer = "List")
    @GetMapping(produces = "application/json")
    public List<OptionResponse> getAll() {
        return hospitalService.getAll();
    }

    @ApiOperation(value = "Get all doctors", response = Page.class)
    @GetMapping(value = "/{hospitalId}/doctors/{page}/{limit}", produces = "application/json")
    public Page<PersonResponse> getDoctors(
        @PathVariable(value = "hospitalId") int hospitalId,
        @PathVariable(value = "page") int page,
        @PathVariable(value = "limit") int limit) {
        return doctorService.getAll(hospitalId, page, limit);
    }

    @ApiOperation(value = "Get all doctors", response = OptionResponse.class, responseContainer = "List")
    @GetMapping(value = "/{id}/doctors", produces = "application/json")
    public List<OptionResponse> getDoctors(@PathVariable(value = "id") int id) {
        return doctorService.getAllByHospital(id);
    }

    @ApiOperation(value = "Get all patients", response = Page.class)
    @GetMapping(value = "/{hospitalId}/patients/{page}/{limit}", produces = "application/json")
    public Page<PersonResponse> getPatients(
        @PathVariable(value = "hospitalId") int hospitalId,
        @PathVariable(value = "page") int page,
        @PathVariable(value = "limit") int limit) {
        return patientService.getAll(hospitalId, page, limit);
    }
}
