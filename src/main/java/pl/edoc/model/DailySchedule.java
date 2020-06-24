package pl.edoc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class DailySchedule {

    private LocalTime startingHour;
    private LocalTime endingHour;
}
