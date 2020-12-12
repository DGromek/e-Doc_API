package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edoc.dto.Credintials;
import pl.edoc.entity.Patient;
import pl.edoc.services.PatientService;
import pl.edoc.utils.JWTUtils;

import java.util.Collections;


@RestController
public class LoginController {
    private final PatientService patientService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginController(PatientService patientService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.patientService = patientService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("patients/login")
    public ResponseEntity login(@RequestBody Credintials credintials) {

        Patient patient = patientService.findByPesel(credintials.getPesel());

        if (patient == null || !bCryptPasswordEncoder.matches(credintials.getPassword(), patient.getPassword())) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        String token = JWTUtils.getToken(patient.getPesel(), patient.getAuthority());
        return new ResponseEntity<>(Collections.singletonMap("token", token), HttpStatus.OK);
    }
}
