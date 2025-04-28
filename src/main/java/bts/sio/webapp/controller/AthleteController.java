package bts.sio.webapp.controller;

import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Epreuve;
import bts.sio.webapp.model.Pays;
import bts.sio.webapp.model.Sport;
import bts.sio.webapp.service.AthleteService;
import bts.sio.webapp.service.PaysService;
import bts.sio.webapp.service.SportService;
import bts.sio.webapp.service.EpreuveService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Controller
public class AthleteController {


    @Autowired
    private AthleteService athleteservice;

    @Autowired
    private PaysService paysService;

    @Autowired
    private SportService sportService;

    @Autowired
    private EpreuveService epreuveService;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Athlete> listAthletes = athleteservice.getAthletes();
        model.addAttribute("athletes", listAthletes);
        return "home";
    }

    @GetMapping("/createAthlete")
    public String createAthlete(Model model) {
        Athlete a = new Athlete();
        model.addAttribute("athlete", a);

        Iterable<Pays> listPays = paysService.getLesPays();
        model.addAttribute("listPays", listPays);

        Iterable<Sport> listSport = sportService.getLesSports();
        model.addAttribute("listSport", listSport);

        Iterable<Epreuve> listEpreuve = epreuveService.getLesEpreuves();
        model.addAttribute("listEpreuve", listEpreuve);

        return "athlete/formNewAthlete";
    }

    @GetMapping("/consulterAthlete/{id}")
    public String consulterAthlete(@PathVariable("id") final int id, Model model) {
        Athlete a = athleteservice.getAthlete(id);

        System.out.println("Athlete's birth date: " + a.getDatenaiss());

        model.addAttribute("athlete", a);
        return "athlete/consulterAthlete";
    }

    @GetMapping("/updateAthlete/{id}")
    public String updateAthlete(@PathVariable("id") final int id, Model model) {
        Athlete a = athleteservice.getAthlete(id);
        model.addAttribute("athlete", a);
        model.addAttribute("listPays", paysService.getLesPays());
        model.addAttribute("listSport", sportService.getLesSports());
        model.addAttribute("listEpreuve", epreuveService.getLesEpreuves());
        return "athlete/formUpdateAthlete";
    }

    @GetMapping("/deleteAthlete/{id}")
    public ModelAndView deleteAthlete(@PathVariable("id") final int id) {
        athleteservice.deleteAthlete(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveAthlete")
    public ModelAndView saveAthlete(@ModelAttribute Athlete athlete) {
        System.out.println("controller save=" + athlete.getNom());
        if(athlete.getId() != null) {
            Athlete current = athleteservice.getAthlete(athlete.getId());
        }
        athleteservice.saveAthlete(athlete);
        return new ModelAndView("redirect:/");
    }
}
