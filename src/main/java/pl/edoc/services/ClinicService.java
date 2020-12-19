package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.entity.Clinic;
import pl.edoc.entity.OpeningHours;
import pl.edoc.repository.ClinicRepository;
import pl.edoc.repository.OpeningHoursRepository;

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

    public Iterable<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public Clinic getOne(int id) {
        return clinicRepository.getOne(id);
    }

    public Clinic getByName(String name) {
        return clinicRepository.getByName(name);
    }

    public Iterable<String> getCities() {
        return clinicRepository.getCities();
    }

    public Iterable<String> getClinicsNames() {
        return clinicRepository.getClinicNames();
    }

    public Iterable<Clinic> findAllByCity(String city) {
        return clinicRepository.findAllByCity(city);
    }

    public Clinic findByCityAndName(String city, String clinicName) {
        return clinicRepository.findByCityAndName(city, clinicName);
    }
}
