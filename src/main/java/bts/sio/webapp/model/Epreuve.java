package bts.sio.webapp.model;

import lombok.Data;

import java.util.List;


@Data
public class Epreuve {

    private Integer id;
    private String libelle;
    private String dateDebut;
    private String dateFin;
    private List<Athlete> athletes;
}
