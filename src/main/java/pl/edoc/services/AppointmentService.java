package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.entity.Appointment;
import pl.edoc.repository.AppointmentRepository;

import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> findAllByPatient_Pesel(String pesel) {
        return appointmentRepository.findAllByPatient_Pesel(pesel);
    }
}
