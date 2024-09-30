package bts.sio.webapp.repository;

import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Olympiade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class OlympiadeProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all Olympiade
     * @return An iterable of all Olympiade
     */
    public Iterable<Olympiade> getLesOlympiades() {

        String baseApiUrl = props.getApiUrl();
        String getOlympiadeUrl = baseApiUrl + "/olympiade";
        System.out.println("url=" + getOlympiadeUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Olympiade>> response = restTemplate.exchange(
                getOlympiadeUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Olympiade>>() {}
        );

        log.debug("Get Olympiade call " + response.getStatusCode().toString());

        return response.getBody();
    }

}
