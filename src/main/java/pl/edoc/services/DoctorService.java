package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edoc.entity.Doctor;
import pl.edoc.repository.DoctorRepository;

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

    public Iterable<Doctor> findAllByClinicIdAndSpeciality(int clinicId, String speciality) {
        return doctorRepository.findAllByClinicIdAndSpeciality(clinicId, speciality);
    }

    public Iterable<String> getSpecialities() {
        return doctorRepository.getSpecialities();
    }

    public Iterable<String> getDoctorsNames() {
        return doctorRepository.getDoctorsNames();
    }

    public Doctor findBySpecialityAndClinicNameAndDoctorName(String speciality, String clinicName, String doctorName) {
        return doctorRepository.findBySpecialityAndClinicNameAndDoctorName(speciality, clinicName, doctorName);
    }

    public Iterable<Doctor> findBySpecialityAndClinicName(String speciality, String clinicName) {
        return doctorRepository.findBySpecialityAndClinicName(speciality, clinicName);
    }
}
