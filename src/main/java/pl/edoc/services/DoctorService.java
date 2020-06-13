package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.entity.Doctor;
import pl.edoc.repository.DoctorRepository;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Iterable<Doctor> findAllByClinicId(int clinicId) {
        return doctorRepository.findAllByClinicId(clinicId);
    }
}
