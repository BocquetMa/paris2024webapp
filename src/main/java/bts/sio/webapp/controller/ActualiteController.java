package bts.sio.webapp.controller;

import bts.sio.webapp.model.Actualite;
import bts.sio.webapp.service.ActualiteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Data
@Controller
public class ActualiteController {

    @Autowired
    private ActualiteService actualiteService;

    @GetMapping("/actualites")
    public String home(Model model) {
        Iterable<Actualite> listActualites = actualiteService.getActualites();
        model.addAttribute("actualites", listActualites);
        return "actualite/formHomeActualite";
    }


}
