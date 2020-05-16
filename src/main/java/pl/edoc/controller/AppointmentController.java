package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edoc.entity.Appointment;
import pl.edoc.services.AppointmentService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Appointment>> getPatientAppointments(
            Authentication authentication) {
        String userPesel = (String) authentication.getPrincipal();
        return new ResponseEntity<>(appointmentService.findAllByPatient_Pesel(userPesel), HttpStatus.OK);
    }
}
