package pl.edoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edoc.dto.PatientDTO;
import pl.edoc.entity.Patient;
import pl.edoc.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Patient save(PatientDTO patientDTO) {
        Patient patientToSave = new Patient(patientDTO);
        patientToSave.setPassword(passwordEncoder.encode(patientToSave.getPassword()));
        return patientRepository.save(patientToSave);
    }

    public Iterable<Patient> findAll() {
        return patientRepository.findAll();
    }

}
