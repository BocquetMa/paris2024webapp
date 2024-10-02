package bts.sio.webapp.repository;


import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Actualite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ActualiteProxy {
    @Autowired
    private CustomProperties props;

    /**
     * Get all athletes
     * @return An iterable of all athlete
     */
    public Iterable<Actualite> getActualites() {

        String baseApiUrl = props.getApiUrl();
        String getActualitesUrl = baseApiUrl + "/actualites";
        System.out.println("url=" + getActualitesUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Actualite>> response = restTemplate.exchange(
                getActualitesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Actualite>>() {}
        );

        log.debug("Get Actualites call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Actualite getActualite(int id) {
        String baseApiUrl = props.getApiUrl();
        String getActualiteUrl = baseApiUrl + "/actualite/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Actualite> response = restTemplate.exchange(
                getActualiteUrl,
                HttpMethod.GET,
                null,
                Actualite.class
        );

        log.debug("Get Actualite call " + response.getStatusCode().toString());

        return response.getBody();
    }
    public Actualite createActualite(Actualite a) {

        String baseApiUrl = props.getApiUrl();
        String createActualiteUrl = baseApiUrl + "/actualite";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Actualite> request = new HttpEntity<Actualite>(a);
        ResponseEntity<Actualite> response = restTemplate.exchange(
                createActualiteUrl,
                HttpMethod.POST,
                request,
                Actualite.class);

        log.debug("Create Athlete call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Actualite updateActualite(Actualite e) {
        String baseApiUrl = props.getApiUrl();
        String updateActualiteUrl = baseApiUrl + "/actualite/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Actualite> request = new HttpEntity<Actualite>(e);
        ResponseEntity<Actualite> response = restTemplate.exchange(
                updateActualiteUrl,
                HttpMethod.PUT,
                request,
                Actualite.class);

        log.debug("Update Actualite call " + response.getStatusCode().toString());

        return response.getBody();
    }

}
