package ma.emsi.patientmvc.web;

import lombok.AllArgsConstructor;
import ma.emsi.patientmvc.entities.Patient;
import ma.emsi.patientmvc.repositories.PatientRepositorie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepositorie patientRepositorie;
@GetMapping("/index")
    public String patients(Model model,){
    List<Patient> patients=patientRepositorie.findAll();
    model.addAttribute("ListPatients",patients);
    return "patients";

    }

}
