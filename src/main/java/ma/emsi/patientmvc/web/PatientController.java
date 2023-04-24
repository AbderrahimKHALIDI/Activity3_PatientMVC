package ma.emsi.patientmvc.web;

import lombok.AllArgsConstructor;
import ma.emsi.patientmvc.entities.Patient;
import ma.emsi.patientmvc.repositories.PatientRepositorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepositorie patientRepositorie;
@GetMapping("/index")
    public String patients(Model model, @RequestParam(name="page",defaultValue = "0") int page , @RequestParam(name="size",defaultValue = "5") int size ){
    Page<Patient> pagePatients=patientRepositorie.findAll(PageRequest.of(page,size));
    model.addAttribute("ListPatients",pagePatients.getContent());
    model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
    model.addAttribute("currentPage",page);
    return "patients";

    }

}
