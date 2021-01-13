package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edoc.entity.Doctor;
import pl.edoc.services.DoctorService;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Doctor>> get(@RequestParam Optional<Integer> clinicId,
                                                @RequestParam Optional<Integer> doctorId) {
        if (clinicId.isPresent()) {
            return new ResponseEntity<>(doctorService.findAllByClinicId(clinicId.get()), HttpStatus.OK);
        } else if (doctorId.isPresent()) {
            return new ResponseEntity<>(Collections.singletonList(doctorService.findById(doctorId.get())), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
