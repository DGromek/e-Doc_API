package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edoc.entity.Clinic;
import pl.edoc.services.ClinicService;

@Controller
@RequestMapping("/clinics")
public class ClinicController {
    private ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/specialities/{clinicId}")
    public ResponseEntity<Iterable<String>> getAllSpecialitiesInClinic(@PathVariable int clinicId) {
        return new ResponseEntity<>(clinicService.getAllSpecialitiesInClinic(clinicId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Clinic>> findAll() {
        return new ResponseEntity<>(clinicService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<Clinic> getOne(@PathVariable int clinicId) {
        return new ResponseEntity<>(clinicService.getOne(clinicId), HttpStatus.OK);
    }
}
