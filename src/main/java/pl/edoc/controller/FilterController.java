package pl.edoc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edoc.services.ClinicService;
import pl.edoc.services.DoctorService;

@Controller
public class FilterController {
    private final ClinicService clinicService;
    private final DoctorService doctorService;

    public FilterController(ClinicService clinicService, DoctorService doctorService) {
        this.clinicService = clinicService;
        this.doctorService = doctorService;
    }

    @GetMapping("/cities")
    public ResponseEntity<Iterable<String>> getCities() {
        return new ResponseEntity<>(clinicService.getCities(), HttpStatus.OK);
    }

    @GetMapping("/clinicNames")
    public ResponseEntity<Iterable<String>> getClinicsNames() {
        return new ResponseEntity<>(clinicService.getClinicsNames(), HttpStatus.OK);
    }

    @GetMapping("/specialities")
    public ResponseEntity<Iterable<String>> getSpecialities() {
        return new ResponseEntity<>(doctorService.getSpecialities(), HttpStatus.OK);
    }

    @GetMapping("/doctorsNames")
    public ResponseEntity<Iterable<String>> getDoctorsNames() {
        return new ResponseEntity<>(doctorService.getDoctorsNames(), HttpStatus.OK);
    }
}
