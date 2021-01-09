package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edoc.model.DailySchedule;
import pl.edoc.services.ScheduleService;

import java.time.DayOfWeek;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<DailySchedule> getDailySchedule(@RequestParam int dayOfWeek, @RequestParam int clinicId, @RequestParam int doctorId) {
        return new ResponseEntity<>(scheduleService.findScheduleForGivenDay(DayOfWeek.of(dayOfWeek), clinicId, doctorId), HttpStatus.OK);
    }
}
