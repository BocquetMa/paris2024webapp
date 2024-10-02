package bts.sio.webapp.repository;


import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Actualite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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

        log.debug("Get Athletes call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an athlete by the id
     * @param id The id of the athlete
     * @return The athlete which matches the id
     */

}
