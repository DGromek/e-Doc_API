package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edoc.dto.PatientDTO;
import pl.edoc.entity.Patient;
import pl.edoc.services.PatientService;

import javax.validation.Valid;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Patient>> getPatient() {
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> postPatient(@RequestBody PatientDTO patientDTO) {
        Patient patientToSave = new Patient(patientDTO);

        return new ResponseEntity<>(patientService.save(patientToSave), HttpStatus.OK);
    }

}
