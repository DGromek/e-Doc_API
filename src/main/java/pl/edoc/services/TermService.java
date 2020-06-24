package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.model.DailySchedule;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TermService {
    private AppointmentService appointmentService;
    private ScheduleService scheduleService;

    @Autowired
    public TermService(AppointmentService appointmentService, ScheduleService scheduleService) {
        this.appointmentService = appointmentService;
        this.scheduleService = scheduleService;
    }

    public Iterable<LocalTime> getAllFreeTermsForGivenDate(Date dateOfAppointment, int clinicId, int doctorId) {
//         TODO: remove takenTerms from the list of free terms.
//        Iterable<LocalDateTime> takenTerms = appointmentService.findAllDatesOfAppointmentsForGivenDate(dateOfAppointment, clinicId, doctorId);
        DailySchedule dailySchedule = scheduleService.findScheduleForGivenDate(dateOfAppointment, clinicId, doctorId);
        List<LocalTime> freeTerms = new ArrayList<>();

        for (LocalTime i = dailySchedule.getStartingHour(); i.isBefore(dailySchedule.getEndingHour()); i = i.plusMinutes(30)) {
            freeTerms.add(i);
        }

        return freeTerms;
    }
}
