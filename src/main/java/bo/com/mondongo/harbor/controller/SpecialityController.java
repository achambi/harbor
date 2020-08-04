package bo.com.mondongo.harbor.controller;

import bo.com.mondongo.harbor.entity.Speciality;
import bo.com.mondongo.harbor.payload.request.GeneralRequest;
import bo.com.mondongo.harbor.payload.response.MessageResponse;
import bo.com.mondongo.harbor.payload.response.OptionResponse;
import bo.com.mondongo.harbor.service.ISpecialityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/specialities")
@Api(value = "Operations pertaining to patients")
public class SpecialityController {

    private final ISpecialityService specialityService;

    @Autowired
    public SpecialityController(ISpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @ApiOperation(value = "Create a patient", response = MessageResponse.class)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public MessageResponse create(@Valid @RequestBody GeneralRequest specialityRequest) {
        return specialityService.create(specialityRequest);
    }

    @ApiOperation(value = "Get all specialities", response = OptionResponse.class, responseContainer = "List")
    @GetMapping(produces = "application/json")
    public List<OptionResponse> getAll() {
        return specialityService.getAll();
    }

    @ApiOperation(value = "Get all patients", response = Page.class)
    @GetMapping(value = "/{page}/{limit}", produces = "application/json")
    public Page<Speciality> getAll(@PathVariable(value = "page") int page,
                                   @PathVariable(value = "limit") int limit) {
        return specialityService.getAll(page, limit);
    }
}
