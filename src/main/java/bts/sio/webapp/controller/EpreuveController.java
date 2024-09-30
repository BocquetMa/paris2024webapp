package bts.sio.webapp.controller;

import bts.sio.webapp.model.Epreuve;
import bts.sio.webapp.service.EpreuveService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class EpreuveController {


    @Autowired
    private EpreuveService epreuveservice;

    @GetMapping("/epreuves")
    public String Sport(Model model) {
        Iterable<Epreuve> listEpreuves = epreuveservice.getLesEpreuves();
        model.addAttribute("epreuves", listEpreuves);
        return "epreuve/formHomeEpreuve";
    }

    @GetMapping("/createEpreuve")
    public String createEpreuve(Model model) {
        Epreuve e = new Epreuve();
        model.addAttribute("epreuve", e);

        return "epreuve/formNewEpreuve";
    }

    @GetMapping("/updateEpreuve/{id}")
    public String updateEpreuve(@PathVariable("id") final int id, Model model) {
        Epreuve e = epreuveservice.getEpreuve(id);
        model.addAttribute("epreuve", e);
        return "epreuve/formUpdateEpreuve";
    }

    @GetMapping("/deleteEpreuve/{id}")
    public ModelAndView deleteEpreuve(@PathVariable("id") final int id) {
        epreuveservice.deleteEpreuve(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveEpreuve")
    public ModelAndView saveEpreuve(@ModelAttribute Epreuve epreuve) {
        System.out.println("controller save=" + epreuve.getLibelle());
        if(epreuve.getId() != null) {
            Epreuve current = epreuveservice.getEpreuve(epreuve.getId());
            epreuve.setLibelle(current.getLibelle());
        }
        epreuveservice.saveEpreuve(epreuve);
        return new ModelAndView("redirect:/");
    }
}
