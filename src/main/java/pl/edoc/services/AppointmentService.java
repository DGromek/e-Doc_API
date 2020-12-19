package pl.edoc.services;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.dto.AppointmentDTO;
import pl.edoc.entity.Appointment;
import pl.edoc.entity.Clinic;
import pl.edoc.entity.Doctor;
import pl.edoc.entity.Patient;
import pl.edoc.model.DailySchedule;
import pl.edoc.repository.AppointmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private static final int MAX_FREE_TERMS = 20;
    public static final int VISIT_DURATION = 30;

    @PersistenceContext
    private EntityManager entityManager;
    private final AppointmentRepository appointmentRepository;
    private final ClinicService clinicService;
    private final DoctorService doctorService;
    private final ScheduleService scheduleService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, ClinicService clinicService, DoctorService doctorService, ScheduleService scheduleService) {
        this.appointmentRepository = appointmentRepository;
        this.clinicService = clinicService;
        this.doctorService = doctorService;
        this.scheduleService = scheduleService;
    }

    public Iterable<Appointment> findAllByPatientPesel(String pesel) {
        return appointmentRepository.findAllByPatient_Pesel(pesel);
    }

    public Iterable<LocalDateTime> findAllDatesOfAppointmentsForGivenDate(LocalDate dateOfAppointment, int clinicId, int doctorId) {
        return appointmentRepository.findAllDatesOfAppointmentsOnGivenDate(dateOfAppointment, clinicId, doctorId);
    }

    public Appointment save(AppointmentDTO appointmentDto, String userPesel) {
        Session session = entityManager.unwrap(Session.class);
        Doctor doctor = session.get(Doctor.class, appointmentDto.getDoctorId());
        Clinic clinic = session.get(Clinic.class, appointmentDto.getClinicId());
        Patient patient = session.get(Patient.class, userPesel);

        Appointment appointmentToSave = new Appointment(appointmentDto, patient, doctor, clinic);
        return appointmentRepository.save(appointmentToSave);
    }

    public List<Appointment> getFreeAppointments(LocalDate sinceWhen, String city, String speciality, Optional<String> clinicName, Optional<String> doctorName) {
        List<Appointment> result = new ArrayList<>();
        Iterable<Clinic> clinics = clinicService.findAll(city, clinicName);
        LocalDate dateIterator = sinceWhen;

        while (true) {
            for (Clinic clinic : clinics) {
                Iterable<Doctor> doctors = doctorService.findAll(clinic.getName(), speciality, doctorName);
                for (Doctor doctor : doctors) {
                    result.addAll(generateFreeAppointments(clinic, doctor, dateIterator));
                    if (result.size() >= MAX_FREE_TERMS) {
                        return result;
                    }
                }
            }
            dateIterator = dateIterator.plusDays(1);
        }
    }

    private List<Appointment> generateFreeAppointments(Clinic clinic, Doctor doctor, LocalDate date) {
        List<Appointment> freeAppointments = new ArrayList<>();

        DailySchedule dailySchedule = scheduleService.findScheduleForGivenDate(date, clinic.getId(), doctor.getId());
        for (LocalTime time = dailySchedule.getStartingHour(); time.isBefore(dailySchedule.getEndingHour()); time = time.plusMinutes(VISIT_DURATION)) {
            LocalDateTime localDateTime = LocalDateTime.of(date, time);
            if (findByClinicAndDoctorAndDateOfAppointment(clinic, doctor, localDateTime) == null) {
                Appointment freeAppoinment = new Appointment(clinic, doctor, localDateTime);
                freeAppointments.add(freeAppoinment);
            }
        }

        return freeAppointments;
    }

    private Appointment findByClinicAndDoctorAndDateOfAppointment(Clinic clinic, Doctor doctor, LocalDateTime dateTime) {
        return appointmentRepository.findByClinicAndDoctorAndDateOfAppointment(clinic, doctor, dateTime);
    }
}
