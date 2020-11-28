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
import pl.edoc.dto.AppointmentDTO;
import pl.edoc.entity.Appointment;
import pl.edoc.services.AppointmentService;
import pl.edoc.services.TermService;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    public static final int TIMEZONE_GMT_PLUS2 = 1;
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

    @PostMapping
    public ResponseEntity<Appointment> savePatientAppointment(@RequestBody AppointmentDTO appointmentDto, Authentication authentication) {
        String userPesel = (String) authentication.getPrincipal();
        // TODO: Make it more elegant way
        appointmentDto.setDateOfAppointment(appointmentDto.getDateOfAppointment()
                .plusHours(TIMEZONE_GMT_PLUS2)); //Because of the timezone
        return new ResponseEntity<>(appointmentService.save(appointmentDto, userPesel), HttpStatus.OK);
    }

    // TODO: Check naming convention of REST endpoints that are not connected to table.
    @GetMapping("/free-terms")
    public ResponseEntity<Iterable<LocalTime>> getFreeTermsForGivenDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, int clinicId, int doctorId) {
        return new ResponseEntity<>(termService.getAllFreeTermsForGivenDate(date, clinicId, doctorId), HttpStatus.OK);
    }
}
