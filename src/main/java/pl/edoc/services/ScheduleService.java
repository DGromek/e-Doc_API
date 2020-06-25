package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.entity.Schedule;
import pl.edoc.model.DailySchedule;
import pl.edoc.repository.ScheduleRepository;

import java.util.Calendar;
import java.util.Date;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public DailySchedule findScheduleForGivenDate(Date date, int clinicId, int doctorId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // We consider Monday as FIRST day of the week, and Sunday as LAST.
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        Schedule schedule = scheduleRepository.getByClinic_IdAndDoctor_Id(clinicId, doctorId);
        switch (dayOfWeek) {
            case 1:
                return new DailySchedule(schedule.getSu_begin(), schedule.getSu_end());
            case 2:
                return new DailySchedule(schedule.getMo_begin(), schedule.getMo_end());
            case 3:
                return new DailySchedule(schedule.getTu_begin(), schedule.getTu_end());
            case 4:
                return new DailySchedule(schedule.getWe_begin(), schedule.getWe_end());
            case 5:
                return new DailySchedule(schedule.getTh_begin(), schedule.getTh_end());
            case 6:
                return new DailySchedule(schedule.getFr_begin(), schedule.getFr_end());
            case 7:
                return new DailySchedule(schedule.getSa_begin(), schedule.getSa_end());
        }
        return null;
    }

}
