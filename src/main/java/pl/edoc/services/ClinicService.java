package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.entity.Clinic;
import pl.edoc.entity.OpeningHours;
import pl.edoc.repository.ClinicRepository;
import pl.edoc.repository.OpeningHoursRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final OpeningHoursRepository openingHoursRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository, OpeningHoursRepository openingHoursRepository) {
        this.clinicRepository = clinicRepository;
        this.openingHoursRepository = openingHoursRepository;
    }

    public OpeningHours getOpeningHoursForClinic(int clinicId) {
        return openingHoursRepository.getOne(clinicId);
    }

    public Iterable<String> getAllSpecialitiesInClinic(int clinicId) {
        return clinicRepository.getAllSpecialitiesInClinic(clinicId);
    }

    public Iterable<Clinic> findAll(String city, Optional<String> clinicName) {
        if (clinicName.isPresent()) {
            return Collections.singletonList(clinicRepository.findByCityAndName(city, clinicName.get()));
        }
        return clinicRepository.findAllByCity(city);
    }

    public Iterable<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public Clinic getOne(int id) {
        return clinicRepository.getOne(id);
    }

    public Iterable<String> getCities() {
        return clinicRepository.getCities();
    }

    public Iterable<String> getClinicsNames(String city, Optional<String> doctorName) {
        if (doctorName.isPresent()) {
            return clinicRepository.findAllClinicNamesByCityAndDoctorName(city, doctorName.get());
        }
        return clinicRepository.getClinicNames();
    }
}
