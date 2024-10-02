package bts.sio.webapp.model;


import lombok.Data;
import java.util.List;
import java.time.LocalDate;

@Data
public class Athlete {

    private Integer id;
    private String nom;
    private String prenom;
    private LocalDate datenaiss ;
    private Pays pays;
    private Sport sport;

    private List<Olympiade> olympiades;
    private List<Epreuve> epreuves;
}
