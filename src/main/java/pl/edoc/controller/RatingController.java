package pl.edoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edoc.entity.Rating;
import pl.edoc.services.RatingService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ratings")
public class RatingController {
    private RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<List<Rating>> get(@RequestParam Optional<Integer> doctorId,
                                            @RequestParam Optional<Integer> appointmentId ) {
        if (doctorId.isPresent()) {
            return new ResponseEntity<>(ratingService.getAll(doctorId.get()), HttpStatus.OK);
        } else if (appointmentId.isPresent()) {
            return new ResponseEntity<>(Collections.singletonList(ratingService.getOne(appointmentId.get())), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
