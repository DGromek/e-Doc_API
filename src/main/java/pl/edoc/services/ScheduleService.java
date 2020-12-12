package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.entity.Schedule;
import pl.edoc.model.DailySchedule;
import pl.edoc.repository.ScheduleRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public DailySchedule findScheduleForGivenDate(LocalDate date, int clinicId, int doctorId) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        Schedule schedule = scheduleRepository.getByClinic_IdAndDoctor_Id(clinicId, doctorId);
        switch (dayOfWeek) {
            case MONDAY:
                return new DailySchedule(schedule.getMo_begin(), schedule.getMo_end());
            case TUESDAY:
                return new DailySchedule(schedule.getTu_begin(), schedule.getTu_end());
            case WEDNESDAY:
                return new DailySchedule(schedule.getWe_begin(), schedule.getWe_end());
            case THURSDAY:
                return new DailySchedule(schedule.getTh_begin(), schedule.getTh_end());
            case FRIDAY:
                return new DailySchedule(schedule.getFr_begin(), schedule.getFr_end());
            case SATURDAY:
                return new DailySchedule(schedule.getSa_begin(), schedule.getSa_end());
            case SUNDAY:
                return new DailySchedule(schedule.getSu_begin(), schedule.getSu_end());
        }
        return null;
    }

}
