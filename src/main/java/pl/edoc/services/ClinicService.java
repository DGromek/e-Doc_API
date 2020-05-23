package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.entity.Clinic;
import pl.edoc.repository.ClinicRepository;

@Service
public class ClinicService {
    private ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public Iterable<String> getAllSpecialitiesInClinic(int clinicId) {
        return clinicRepository.getAllSpecialitiesInClinic(clinicId);
    }

    public Iterable<Clinic> findAll() {
        return clinicRepository.findAll();
    }
}
