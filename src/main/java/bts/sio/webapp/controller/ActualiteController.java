package bts.sio.webapp.controller;

import bts.sio.webapp.model.*;
import bts.sio.webapp.service.ActualiteService;
import bts.sio.webapp.service.EpreuveService;
import bts.sio.webapp.service.SportService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class ActualiteController {

    @Autowired
    private ActualiteService actualiteservice;

    @Autowired
    private SportService sportservice;

    @Autowired
    private EpreuveService epreuveservice;

    @GetMapping("/actualites")
    public String home(Model model) {
        Iterable<Actualite> listActualites = actualiteservice.getActualites();
        model.addAttribute("actualites", listActualites);
        return "actualite/formHomeActualite";
    }

    @GetMapping("/actualite/{id}")
    public String consulterActualite(@PathVariable("id") final int id, Model model) {
        Actualite a = actualiteservice.getActualite(id);
        model.addAttribute("actualite", a);
        return "actualite/formConsulterActualite";
    }

    @GetMapping("/createActualite")
    public String createActualite(Model model) {
        Actualite a = new Actualite();
        model.addAttribute("actualite", a);

        Iterable<Sport> listSport = sportservice.getLesSports();
        model.addAttribute("listSport", listSport);

        Iterable<Epreuve> listEpreuve = epreuveservice.getLesEpreuves();
        model.addAttribute("listEpreuve", listEpreuve);

        return "actualite/formNewActualite";
    }

    @PostMapping("/saveActualite")
    public ModelAndView saveActualite(@ModelAttribute Actualite actualite) {
        System.out.println("controller save=");
        System.out.println("controller save=" + actualite.getTitre());
        if(actualite.getId() != null) {
            Actualite current = actualiteservice.getActualite(actualite.getId());
        }
        actualiteservice.saveActualite(actualite);
        return new ModelAndView("redirect:/");
    }


}
