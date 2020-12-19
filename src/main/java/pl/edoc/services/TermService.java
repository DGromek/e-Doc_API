package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.entity.Clinic;
import pl.edoc.entity.Doctor;
import pl.edoc.model.DailySchedule;
import pl.edoc.model.FreeTerm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TermService {

    private final AppointmentService appointmentService;
    private final ScheduleService scheduleService;
    private final ClinicService clinicService;
    private final DoctorService doctorService;

    @Autowired
    public TermService(AppointmentService appointmentService, ScheduleService scheduleService, ClinicService clinicService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.scheduleService = scheduleService;
        this.clinicService = clinicService;
        this.doctorService = doctorService;
    }

    @Deprecated
    public Iterable<LocalTime> getAllFreeTermsForGivenDate(LocalDate dateOfAppointment, int clinicId, int doctorId) {
        Iterable<LocalDateTime> takenTerms = appointmentService.findAllDatesOfAppointmentsForGivenDate(dateOfAppointment, clinicId,
                doctorId);
        DailySchedule dailySchedule = scheduleService.findScheduleForGivenDate(dateOfAppointment, clinicId, doctorId);
        List<LocalTime> freeTerms = new ArrayList<>();

        for (LocalTime i = dailySchedule.getStartingHour(); i.isBefore(dailySchedule.getEndingHour()); i = i.plusMinutes(30)) {
            boolean isTermTaken = false;
            for (LocalDateTime j : takenTerms) {
                LocalTime temp = LocalTime.of(j.getHour(), j.getMinute());
                if (temp.equals(i)) {
                    isTermTaken = true;
                    break;
                }
            }
            if (!isTermTaken) {
                freeTerms.add(i);
            }
        }

        return freeTerms;
    }

    public List<FreeTerm> getAllFreeTerms(LocalDate sinceWhen, String city, String speciality, String clinicName, String doctorName) {
        Clinic clinic = clinicService.findByCityAndName(city, clinicName);
        Doctor doctor = doctorService.findBySpecialityAndClinicNameAndDoctorName(speciality, clinicName, doctorName);
        return new ArrayList<>(generateFreeTerms(clinic, doctor, sinceWhen));
    }

    public List<FreeTerm> getAllFreeTermsWithClinicName(LocalDate sinceWhen, String city, String speciality, String clinicName) {
        List<FreeTerm> result = new ArrayList<>();
        Clinic clinic = clinicService.findByCityAndName(city, clinicName);
        Iterable<Doctor> doctors = doctorService.findBySpecialityAndClinicName(speciality, clinicName);
        for (Doctor doctor : doctors) {
            result.addAll(generateFreeTerms(clinic, doctor, sinceWhen));
            if (result.size() >= 20) {
                return result;
            }
        }
        return result;
    }

    public List<FreeTerm> getAllFreeTermsWithDoctorName(LocalDate sinceWhen, String city, String speciality, String doctorName) {
        List<FreeTerm> result = new ArrayList<>();
        Iterable<Clinic> clinics = clinicService.findAllByCity(city);
        for (Clinic clinic : clinics) {
            Doctor doctor = doctorService.findBySpecialityAndClinicNameAndDoctorName(speciality, clinic.getName(), doctorName);
            result.addAll(generateFreeTerms(clinic, doctor, sinceWhen));
            if (result.size() >= 20) {
                return result;
            }
        }

        return result;
    }

    public List<FreeTerm> getAllFreeTerms(LocalDate sinceWhen, String city, String speciality) {
        List<FreeTerm> result = new ArrayList<>();
        Iterable<Clinic> clinics = clinicService.findAllByCity(city);
        for (Clinic clinic : clinics) {
            Iterable<Doctor> doctors = doctorService.findAllByClinicIdAndSpeciality(clinic.getId(), speciality);
            for (Doctor doctor : doctors) {
                result.addAll(generateFreeTerms(clinic, doctor, sinceWhen));
                if (result.size() >= 20) {
                    return result;
                }
            }
        }
        return result;
    }

    private List<FreeTerm> generateFreeTerms(Clinic clinic, Doctor doctor, LocalDate date) {
        List<FreeTerm> freeTerms = new ArrayList<>();
        LocalDate dateIterator = date;

        for (int i = 0; i < 14; i++) {
            DailySchedule dailySchedule = scheduleService.findScheduleForGivenDate(dateIterator, clinic.getId(), doctor.getId());
            for (LocalTime time = dailySchedule.getStartingHour(); time.isBefore(dailySchedule.getEndingHour()); time = time.plusMinutes(30)) {
                LocalDateTime localDateTime = LocalDateTime.of(date, time);
                if (appointmentService.findByClinicAndDoctorAndDateOfAppointment(clinic, doctor, localDateTime) == null) {
                    FreeTerm freeTerm = new FreeTerm(clinic, doctor, localDateTime);
                    freeTerms.add(freeTerm);
                }
            }
            dateIterator = dateIterator.plusDays(1);
        }

        return freeTerms;
    }
}
