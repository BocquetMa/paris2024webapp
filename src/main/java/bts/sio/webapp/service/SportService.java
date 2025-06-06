package bts.sio.webapp.service;

import bts.sio.webapp.model.Sport;
import bts.sio.webapp.repository.SportProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SportService {

    @Autowired
    private SportProxy sportProxy;

    // Méthode unique pour récupérer les sports
    public Iterable<Sport> getLesSports() {
        return sportProxy.getSports();
    }

    public Sport getSport(final int id) {
        return sportProxy.getSport(id);
    }

    public void deleteSport(final int id) {
        sportProxy.deleteSport(id);
    }

    public Sport saveSport(Sport sport) {

        if (sport.getNom() == null || sport.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du sport ne peut pas être vide.");
        }

        sport.setNom(sport.getNom().toUpperCase());

        if (sport.getId() == null) {

            return sportProxy.createSport(sport);
        } else {
            return sportProxy.updateSport(sport);
        }
    }

}
