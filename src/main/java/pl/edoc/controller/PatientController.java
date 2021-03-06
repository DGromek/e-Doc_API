package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edoc.dto.PatientDTO;
import pl.edoc.model.PatientsSignature;
import pl.edoc.entity.Patient;
import pl.edoc.services.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Patient>> getPatient() {
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/patientsSignature")
    public ResponseEntity<PatientsSignature> getPatientsSignature(@RequestParam int appointmentId) {
        return new ResponseEntity<>(new PatientsSignature(patientService.findByAppointmentId(appointmentId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> postPatient(@RequestBody PatientDTO patientDTO) {
        return new ResponseEntity<>(patientService.save(patientDTO), HttpStatus.CREATED);
    }
}
