package pl.edoc.utils;

import pl.edoc.entity.Appointment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

public class EmailUtils {

    private EmailUtils() {
    }

    public static String getAppointmentReminderTemplate(Appointment appointment) {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(new File("").getAbsolutePath() + "/src/main/resources/emailTemplates/appointmentReminder.html"));
            String template = bufferedReader.lines().collect(Collectors.joining());
            return template.replaceFirst("\\{\\{patient.firstName}}", appointment.getPatient().getFirstName())
                    .replaceFirst("\\{\\{patient.lastName}}", appointment.getPatient().getLastName())
                    .replaceFirst("\\{\\{doctor.firstName}}", appointment.getDoctor().getFirstName())
                    .replaceFirst("\\{\\{doctor.lastName}}", appointment.getDoctor().getLastName())
                    .replaceFirst("\\{\\{doctor.speciality}}", appointment.getDoctor().getSpeciality())
                    .replaceFirst("\\{\\{date}}", appointment.getDateOfAppointment().toLocalDate().toString())
                    .replaceFirst("\\{\\{time}}", appointment.getDateOfAppointment().toLocalTime().toString())
                    .replaceFirst("\\{\\{clinicName}}", appointment.getClinic().getName())
                    .replaceFirst("\\{\\{city}}", appointment.getClinic().getCity())
                    .replaceFirst("\\{\\{street}}", appointment.getClinic().getStreet())
                    .replaceFirst("\\{\\{houseNr}}", appointment.getClinic().getHouseNr());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
