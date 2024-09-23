package bts.sio.webapp.controller;

import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Olympiade;
import bts.sio.webapp.model.Pays;
import bts.sio.webapp.model.Sport;
import bts.sio.webapp.service.AthleteService;
import bts.sio.webapp.service.PaysService;
import bts.sio.webapp.service.SportService;
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
public class AthleteController {


    @Autowired
    private AthleteService athleteservice;

    @Autowired
    private PaysService paysService;

    @Autowired
    private SportService sportService;

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

        return "athlete/formNewAthlete";
    }

    @GetMapping("/updateAthlete/{id}")
    public String updateAthlete(@PathVariable("id") final int id, Model model) {
        Athlete a = athleteservice.getAthlete(id);
        model.addAttribute("athlete", a);
        model.addAttribute("listPays", paysService.getLesPays());
        model.addAttribute("listSport", sportService.getLesSports());
        return "athlete/formUpdateAthlete";
    }

    @GetMapping("/deleteAthlete/{id}")
    public ModelAndView deleteAthlete(@PathVariable("id") final int id) {
        athleteservice.deleteAthlete(id);
        return new ModelAndView("redirect:/");
    }
    @PostMapping("/athlete/{id}/addOlympiade")
    public ModelAndView addOlympiade(@PathVariable("id") int id, @ModelAttribute Olympiade olympiade) {
        Athlete athlete = athleteservice.getAthlete(id);
        athlete.getOlympiades().add(olympiade);
        athleteservice.saveAthlete(athlete);
        return new ModelAndView("redirect:/athlete/" + id);
    }

    @PostMapping("/saveAthlete")
    public ModelAndView saveAthlete(@ModelAttribute Athlete athlete) {
        System.out.println("controller save=" + athlete.getNom());
        if(athlete.getId() != null) {
            Athlete current = athleteservice.getAthlete(athlete.getId());
            athlete.setNom(current.getNom());
        }
        athleteservice.saveAthlete(athlete);
        return new ModelAndView("redirect:/");
    }
}
