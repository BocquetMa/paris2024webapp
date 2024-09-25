package bts.sio.webapp.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class AthleteEpreuveService {

    @Autowired
    private AthleteEpreuveProxy athleteEpreuveProxy;

    /**
     * Add an association between an athlete and an event
     * @param association The association to be added
     */
    public AthleteEpreuve addAssociation(AthleteEpreuve association) {
        return athleteEpreuveProxy.createAthleteEpreuve(association);
    }
}
