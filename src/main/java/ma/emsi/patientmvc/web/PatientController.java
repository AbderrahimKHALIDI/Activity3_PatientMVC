package ma.emsi.patientmvc.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.emsi.patientmvc.entities.Patient;
import ma.emsi.patientmvc.repositories.PatientRepositorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepositorie patientRepositorie;
@GetMapping("/user/index")
    public String patients(Model model,
                           @RequestParam(name="page",defaultValue = "0") int page ,
                           @RequestParam(name="size",defaultValue = "5") int size,
                           @RequestParam(name="keyword",defaultValue = "") String keyword){
    Page<Patient> pagePatients=patientRepositorie.findByNomContains(keyword,PageRequest.of(page,size));
    model.addAttribute("ListPatients",pagePatients.getContent());
    model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
    model.addAttribute("currentPage",page);
    model.addAttribute("keyword",keyword);
    return "patients";

    }
    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(Long id, String keyword,int page){
    patientRepositorie.deleteById(id);
    return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home(){

        return "redirect:/user/index";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){

        return patientRepositorie.findAll();
    }

@GetMapping("/admin/formPatients")
@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formPatient(Model model){
    model.addAttribute("patient",new Patient());
return "formPatients";
    }
    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "") String keyword){
    if(bindingResult.hasErrors()) return "formPatients";
    patientRepositorie.save(patient);
    return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(Model model,Long id, String keyword, int page){
    Patient patient=patientRepositorie.findById(id).orElse(null);
    if(patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }
}
