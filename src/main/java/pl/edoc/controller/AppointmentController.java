package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edoc.entity.Appointment;
import pl.edoc.services.AppointmentService;
import pl.edoc.services.TermService;

import java.time.LocalTime;
import java.util.Date;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private TermService termService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, TermService termService) {
        this.appointmentService = appointmentService;
        this.termService = termService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Appointment>> getPatientAppointments(Authentication authentication) {
        String userPesel = (String) authentication.getPrincipal();
        return new ResponseEntity<>(appointmentService.findAllByPatient_Pesel(userPesel), HttpStatus.OK);
    }

    // TODO: Check naming convention of REST endpoints that are not connected to table.
    @GetMapping("/free-terms")
    public ResponseEntity<Iterable<LocalTime>> getFreeTermsForGivenDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date date, int doctorId, int clinicId) {
        return new ResponseEntity<>(termService.getAllFreeTermsForGivenDate(date, clinicId, doctorId), HttpStatus.OK);
        //return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/free-terms")
//    public ResponseEntity getFreeTermsForGivenDate(int doctorId, int clinicId) {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
