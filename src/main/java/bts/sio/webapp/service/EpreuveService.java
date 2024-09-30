package bts.sio.webapp.service;

import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Epreuve;
import bts.sio.webapp.repository.EpreuveProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class EpreuveService{

    @Autowired
    private EpreuveProxy epreuveProxy;

    public Epreuve getEpreuve(final int id) {
        return epreuveProxy.getEpreuve(id);
    }

    public Iterable<Epreuve> getLesEpreuves() {
        return epreuveProxy.getEpreuves();
    }

    public void deleteEpreuve(final int id) {
        epreuveProxy.deleteEpreuve(id);
    }

    public Epreuve saveEpreuve(Epreuve epreuve) {
        System.out.println("Service save=" + epreuve.getLibelle());
        System.out.println("Date Début: " + epreuve.getDateDebut());
        System.out.println("Date Fin: " + epreuve.getDateFin());

        epreuve.setLibelle(epreuve.getLibelle().toUpperCase());

        if (epreuve.getId() == null) {
            return epreuveProxy.createEpreuve(epreuve);
        } else {
            return epreuveProxy.updateEpreuve(epreuve);
        }
    }
}
