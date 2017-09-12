package br.com.gbraghim.eventmanager.api.controller;

import br.com.gbraghim.eventmanager.model.domain.Event;
import br.com.gbraghim.eventmanager.service.EventService;
import br.com.gbraghim.eventmanager.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController{
    @Autowired
    EventService eventService;

    //get geral
    @RequestMapping(method = RequestMethod.GET)
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    //get especifico de um event
    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable int eventId) throws ResourceNotFoundException {
        Event event = eventService.findById(eventId);
        return event;
    }

//posting
    @RequestMapping(method = RequestMethod.POST)
    public Event createEvent(@RequestBody Event event){
        return eventService.registraEvent(event.getId(), event.getTitulo(), event.getDescricao(),event.getData(), event.getVagasTotais(),event.getDisponivel());
    }

    //edição
    @RequestMapping(value = "/{eventId}/eventTitulo", method = RequestMethod.PUT)
    public void editEventTitulo(@PathVariable int eventId, @RequestBody Event event){
        String novoTitulo = event.getTitulo();
        eventService.alterarTitulo(eventId, novoTitulo);
    }

    @RequestMapping(value = "/{eventId}/eventData", method = RequestMethod.PUT)
    public void editDataEvent(@PathVariable int eventId, @RequestBody Event event){
        Date data = new Date();
        eventService.alterarData(eventId, data);
    }

    @RequestMapping(value = "/{eventId}/eventDescricao", method = RequestMethod.PUT)
    public void editDescricaoEvent(@PathVariable int eventId, @RequestBody Event event){
        String descricao = event.getDescricao();
        eventService.alterarDescricao(eventId, descricao);
    }

    @RequestMapping(value = "/{eventId}/eventDisponibilidade", method = RequestMethod.PUT)
    public void editDisponibilidadeEvent(@PathVariable int eventId, @RequestBody Event event){
        boolean disponibilidade = event.getDisponivel();
        eventService.alterarDisponibilidade(eventId, disponibilidade);
    }

    @RequestMapping(value = "/{eventId}/eventCapacidade", method = RequestMethod.PUT)
    public void editCapacidadeEvent(@PathVariable int eventId, @RequestBody Event event){
        int capacidade = event.getVagasTotais();
        eventService.alterarVagas(eventId, capacidade);
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable int eventId)
    {
        eventService.deletaEvent(eventId);
    }


}

