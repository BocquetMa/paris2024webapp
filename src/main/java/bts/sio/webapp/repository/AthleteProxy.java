package bts.sio.webapp.repository;


import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Epreuve;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class AthleteProxy {
    @Autowired
    private CustomProperties props;

    /**
     * Get all athletes
     * @return An iterable of all athlete
     */
    public Iterable<Athlete> getAthletes() {

        String baseApiUrl = props.getApiUrl();
        String getAthletesUrl = baseApiUrl + "/athletes";
        System.out.println("url=" + getAthletesUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Athlete>> response = restTemplate.exchange(
                getAthletesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Athlete>>() {}
        );

        log.debug("Get Athletes call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an athlete by the id
     * @param id The id of the athlete
     * @return The athlete which matches the id
     */
    public Athlete getAthlete(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/athlete/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Athlete> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Athlete.class
        );

        log.debug("Get Athlete call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new athlete
     * @param a A new athlete (without an id)
     * @return The athlete full filled (with an id)
     */
    public Athlete createAthlete(Athlete a) {

        String baseApiUrl = props.getApiUrl();
        String createAthleteUrl = baseApiUrl + "/athlete";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Athlete> request = new HttpEntity<Athlete>(a);
        ResponseEntity<Athlete> response = restTemplate.exchange(
                createAthleteUrl,
                HttpMethod.POST,
                request,
                Athlete.class);

        log.debug("Create Athlete call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an athlete - using the PUT HTTP Method.
     * @param e Existing athlete to update
     */
    public Athlete updateAthlete(Athlete e) {
        String baseApiUrl = props.getApiUrl();
        String updateAthleteUrl = baseApiUrl + "/athlete/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Athlete> request = new HttpEntity<Athlete>(e);
        ResponseEntity<Athlete> response = restTemplate.exchange(
                updateAthleteUrl,
                HttpMethod.PUT,
                request,
                Athlete.class);

        log.debug("Update Athlete call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Epreuve getEpreuve(int epreuveId) {
        String baseApiUrl = props.getApiUrl();
        String getEpreuveUrl = baseApiUrl + "/epreuve/" + epreuveId;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Epreuve> response = restTemplate.exchange(
                getEpreuveUrl,
                HttpMethod.GET,
                null,
                Epreuve.class
        );

        log.debug("Get Epreuve call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /*
     * Delete an athlete using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param e The athlete to delete
     */
    public void deleteAthlete(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteAthleteUrl = baseApiUrl + "/athlete/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteAthleteUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Athlete call " + response.getStatusCode().toString());
    }
}
