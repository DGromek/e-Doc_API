package pl.edoc.services;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.dto.AppointmentDTO;
import pl.edoc.entity.Appointment;
import pl.edoc.entity.Clinic;
import pl.edoc.entity.Doctor;
import pl.edoc.entity.Patient;
import pl.edoc.repository.AppointmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Iterable<Appointment> findAllByPatient_Pesel(String pesel) {
        return appointmentRepository.findAllByPatient_Pesel(pesel);
    }

    public Iterable<LocalDateTime> findAllDatesOfAppointmentsForGivenDate(Date dateOfAppointment, int clinicId, int doctorId) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormatted = formatter.format(dateOfAppointment);
        Iterable<Timestamp> temp =  appointmentRepository.findAllDatesOfAppointmentsOnGivenDate(dateFormatted, clinicId, doctorId);
        List<LocalDateTime> result = new ArrayList<>();

        //Parsing timestamps into LocalDateTime as repository can't do it automatically.
        for (Timestamp timestamp : temp) {
            result.add(timestamp.toLocalDateTime());
        }
        return result;
    }

    public Appointment save(AppointmentDTO appointmentDto, String userPesel) {
        Session session = entityManager.unwrap(Session.class);
        Doctor doctor = session.get(Doctor.class, appointmentDto.getDoctorId());
        Clinic clinic = session.get(Clinic.class, appointmentDto.getClinicId());
        Patient patient = session.get(Patient.class, userPesel);

        Appointment appointmentToSave = new Appointment(appointmentDto, patient, doctor, clinic);
        return appointmentRepository.save(appointmentToSave);
    }
}
