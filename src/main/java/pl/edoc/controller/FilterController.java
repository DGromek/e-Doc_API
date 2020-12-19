package pl.edoc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edoc.services.ClinicService;
import pl.edoc.services.DoctorService;

import java.util.Optional;

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
    public ResponseEntity<Iterable<String>> getClinicsNames(@RequestParam String city, @RequestParam Optional<String> doctorName) {
        return new ResponseEntity<>(clinicService.getClinicsNames(city, doctorName), HttpStatus.OK);
    }

    @GetMapping("/specialities")
    public ResponseEntity<Iterable<String>> getSpecialities() {
        return new ResponseEntity<>(doctorService.getSpecialities(), HttpStatus.OK);
    }

    @GetMapping("/doctorsNames")
    public ResponseEntity<Iterable<String>> getDoctorsNames(@RequestParam String city, @RequestParam String speciality, @RequestParam Optional<String> clinicName) {
        return new ResponseEntity<>(doctorService.getDoctorsNames(city, speciality, clinicName), HttpStatus.OK);
    }
}
