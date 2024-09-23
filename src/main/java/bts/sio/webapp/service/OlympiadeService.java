package bts.sio.webapp.service;

import bts.sio.webapp.model.Olympiade;
import bts.sio.webapp.repository.OlympiadeProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class OlympiadeService {

    @Autowired
    private OlympiadeProxy olympiadeProxy;
    public Iterable<Olympiade> getLesOlympiades() {
        return olympiadeProxy.getLesOlympiades();
    }
}
