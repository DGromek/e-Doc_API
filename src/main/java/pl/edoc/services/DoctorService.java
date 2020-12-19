package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.entity.Doctor;
import pl.edoc.repository.DoctorRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Iterable<Doctor> findAllByClinicId(int clinicId) {
        return doctorRepository.findAllByClinicId(clinicId);
    }

    public Iterable<Doctor> findAll(String clinicName, String speciality, Optional<String> doctorName) {
        if (doctorName.isPresent()) {
            return Collections.singletonList(doctorRepository.findBySpecialityAndClinicNameAndDoctorName(clinicName, speciality, doctorName.get()));
        }
        return doctorRepository.findAllByClinicNameAndSpeciality(clinicName, speciality);
    }

    public Iterable<String> getSpecialities() {
        return doctorRepository.getSpecialities();
    }

    public Iterable<String> getDoctorsNames() {
        return doctorRepository.getDoctorsNames();
    }
}
