package br.com.gbraghim.eventmanager.service;

import br.com.gbraghim.eventmanager.model.dao.EventDAO;
import br.com.gbraghim.eventmanager.model.domain.Event;
import br.com.gbraghim.eventmanager.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventDAO eventDAO;

    public Event registraEvent(int id, String titulo, String descricao, Date data, int nVagas, boolean disponibilidade){
        Event event = new Event();
        event.setId(id);
        event.setTitulo(titulo);
        event.setData(data);
        event.setDescricao(descricao);
        event.setVagasTotais(nVagas);
        event.setDisponivel(disponibilidade);

        eventDAO.createEvent(event);
        return event;
    }

    public void alterarId(int id, int novoId){
        Event existingEvent = eventDAO.getById(id);
        existingEvent.setId(novoId);
        eventDAO.updateEvent(existingEvent);
    }

    public void alterarDescricao(int id, String novaDescricao){
        Event existingEvent = eventDAO.getById(id);
        existingEvent.setDescricao(novaDescricao);
        eventDAO.updateEvent(existingEvent);
    }

    public void alterarTitulo(int id, String novoTitulo){
        Event existingEvent = eventDAO.getById(id);
        existingEvent.setTitulo(novoTitulo);
        eventDAO.updateEvent(existingEvent);
    }

    public void alterarData(int id, Date novaData){
        Event existingEvent = eventDAO.getById(id);
        existingEvent.setData(novaData);
        eventDAO.updateEvent(existingEvent);
    }

    public void alterarVagas(int id, int novoNmrVagas){
        Event existingEvent = eventDAO.getById(id);
        existingEvent.setVagasTotais(novoNmrVagas);
        eventDAO.updateEvent(existingEvent);
    }

    public void alterarDisponibilidade (int id, boolean novaVisibilidade){
        Event existingEvent = eventDAO.getById(id);
        existingEvent.setDisponivel(novaVisibilidade);
        eventDAO.updateEvent(existingEvent);
    }

    public void deletaEvent(int id){
        Event existingEvent= eventDAO.getById(id);
        eventDAO.deleteEvent(existingEvent);
    }

    public List<Event> findAll() {
        return eventDAO.findAll();
    }

@Test
public void testingFlow() throws IOException {
/*
        httprequest('localhost:8080/users', PUT, '{name: bla, senha: bla, email: bla}')
        User user = httprequest('localhost:8080/users/14372458732947', GET);
        httprequest('localhost:8080/users/14372458732947', POST, '{name: bla2}')
*/
    String titulo = "Apple event";
    String descricao = "New release";
    String data = String.valueOf(new Date());
    String vagas= String.valueOf(30);

    URL url = new URL("http://localhost:8080/events");
    HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
    httpCon.setDoOutput(true);
    httpCon.setRequestMethod("PUT");
    httpCon.setRequestProperty( "Content-Type", "application/json");
    httpCon.setRequestProperty( "charset", "utf-8");

    // como passar data, email, password, titulo
    httpCon.setRequestProperty(titulo, titulo);
    httpCon.setRequestProperty(descricao, descricao);
    httpCon.setRequestProperty(vagas, vagas);
    httpCon.setRequestProperty(data, data);

    httpCon.getInputStream();
}

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Event findById(int eventId) throws ResourceNotFoundException {
        return Optional.ofNullable(eventDAO.getById(eventId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + eventId));
    }
}