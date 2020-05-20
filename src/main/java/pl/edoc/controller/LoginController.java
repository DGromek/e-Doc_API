package pl.edoc.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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

import java.util.Collections;
import java.util.Date;

import static pl.edoc.security.SecurityConfig.SECRET_512;

@RestController
public class LoginController {
    private static final int TOKEN_DURATION = 1000 * 60 * 15;

    private PatientService patientService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

        String token = JWT.create()
                .withSubject(patient.getPesel())
                .withClaim("Role", patient.getAuthority())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_DURATION))
                .sign(Algorithm.HMAC512(SECRET_512));
    return new ResponseEntity<>(Collections.singletonMap("token", token), HttpStatus.OK);
    }
}
