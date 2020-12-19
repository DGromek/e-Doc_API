package pl.edoc.services;

import org.springframework.stereotype.Service;
import pl.edoc.model.DailySchedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TermService {
    private final AppointmentService appointmentService;
    private final ScheduleService scheduleService;

    public TermService(AppointmentService appointmentService, ScheduleService scheduleService) {
        this.appointmentService = appointmentService;
        this.scheduleService = scheduleService;
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
}
