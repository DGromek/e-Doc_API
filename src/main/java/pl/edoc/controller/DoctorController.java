package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edoc.entity.Doctor;
import pl.edoc.services.DoctorService;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<Iterable<Doctor>> findAllByClinicId(@PathVariable int clinicId) {
        return new ResponseEntity<>(doctorService.findAllByClinicId(clinicId), HttpStatus.OK);
    }
}
