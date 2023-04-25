package ma.emsi.patientmvc;

import ma.emsi.patientmvc.entities.Patient;
import ma.emsi.patientmvc.repositories.PatientRepositorie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }
//@Bean
    CommandLineRunner commandLineRunner(PatientRepositorie patientRepositorie){
        return  args -> {
patientRepositorie.save(new Patient(null,"Hassan",new Date(),false,333));
            patientRepositorie.save(new Patient(null,"Mouad",new Date(),true,6666));

            patientRepositorie.save(new Patient(null,"Leila",new Date(),true,888));
            patientRepositorie.save(new Patient(null,"Salah",new Date(),false,999));
            patientRepositorie.findAll().forEach(p->{
                System.out.println(p.getNom());
            });

        };
    }
}
