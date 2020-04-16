package pl.edoc.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edoc.dto.Credintials;
import pl.edoc.dto.PatientDTO;
import pl.edoc.entity.Patient;
import pl.edoc.services.PatientService;

import java.util.Date;

import static pl.edoc.security.SecurityConfig.SECRET_512;

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
        return new ResponseEntity<>(patientService.save(patientDTO), HttpStatus.CREATED);
    }
}