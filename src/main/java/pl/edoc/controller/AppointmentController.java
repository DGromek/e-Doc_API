package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edoc.dto.AppointmentDTO;
import pl.edoc.entity.Appointment;
import pl.edoc.services.AppointmentService;
import pl.edoc.services.TermService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final TermService termService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, TermService termService) {
        this.appointmentService = appointmentService;
        this.termService = termService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Appointment>> getPatientAppointments(Authentication authentication) {
        String userPesel = (String) authentication.getPrincipal();
        return new ResponseEntity<>(appointmentService.findAllByPatientPesel(userPesel), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Appointment> savePatientAppointment(@RequestBody AppointmentDTO appointmentDto, Authentication authentication) {
        String userPesel = (String) authentication.getPrincipal();
        return new ResponseEntity<>(appointmentService.save(appointmentDto, userPesel), HttpStatus.OK);
    }

    // TODO: Check naming convention of REST endpoints that are not connected to table.
    @GetMapping("/free-terms")
    public ResponseEntity<Iterable<LocalTime>> getFreeTermsForGivenDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, int clinicId, int doctorId) {
        return new ResponseEntity<>(termService.getAllFreeTermsForGivenDate(date, clinicId, doctorId), HttpStatus.OK);
    }

    @GetMapping("/free-appointments")
    public ResponseEntity<Iterable<Appointment>> getFreeAppointments(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                     @RequestParam String city,
                                                                     @RequestParam String speciality,
                                                                     @RequestParam Optional<String> clinicName,
                                                                     @RequestParam Optional<String> doctorName) {
        return new ResponseEntity<>(appointmentService.getFreeAppointments(date, city, speciality, clinicName, doctorName), HttpStatus.OK);
    }
}
