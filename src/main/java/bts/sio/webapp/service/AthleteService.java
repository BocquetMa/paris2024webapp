package bts.sio.webapp.service;

import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Sport;
import bts.sio.webapp.model.Epreuve;
import bts.sio.webapp.repository.AthleteProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class AthleteService {

    @Autowired
    private AthleteProxy athleteProxy;

    public Athlete getAthlete(final int id) {
        return athleteProxy.getAthlete(id);
    }

    public Iterable<Athlete> getAthletes() {
        return athleteProxy.getAthletes();
    }

    public Iterable<Athlete> getLesAthletes() {
        return athleteProxy.getAthletes();
    }

    public Epreuve getEpreuve(int epreuveId) {
        return athleteProxy.getEpreuve(epreuveId);
    }

    public void deleteAthlete(final int id) {
        athleteProxy.deleteAthlete(id);
    }


    public Athlete saveAthlete(Athlete athlete) {
        Athlete savedAthlete;

        athlete.setNom(athlete.getNom().toUpperCase());

        if(athlete.getId() == null) {
            savedAthlete = athleteProxy.createAthlete(athlete);
        } else {
            savedAthlete = athleteProxy.updateAthlete(athlete);
        }

        return savedAthlete;
    }
}
