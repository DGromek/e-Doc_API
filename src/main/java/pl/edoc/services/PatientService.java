package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edoc.dto.PatientDTO;
import pl.edoc.entity.Patient;
import pl.edoc.repository.PatientRepository;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PatientService(PatientRepository patientRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.patientRepository = patientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient save(PatientDTO patientDTO) {
        Patient patientToSave = new Patient(patientDTO);
        patientToSave.setPassword(bCryptPasswordEncoder.encode(patientToSave.getPassword()));
        return patientRepository.save(patientToSave);
    }

    public Iterable<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findByPesel(String pesel) {
        return patientRepository.findByPesel(pesel);
    }

    public Patient findByAppointmentId(int ratingId) {
        return patientRepository.findByAppointmentId(ratingId);
    }
}
