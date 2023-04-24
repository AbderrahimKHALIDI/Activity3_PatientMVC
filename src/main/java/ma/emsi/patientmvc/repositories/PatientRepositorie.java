package ma.emsi.patientmvc.repositories;

import ma.emsi.patientmvc.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepositorie extends JpaRepository<Patient,Long> {
}
