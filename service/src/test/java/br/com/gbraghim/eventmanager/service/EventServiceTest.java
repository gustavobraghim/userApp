package br.com.gbraghim.eventmanager.service;

import br.com.gbraghim.eventmanager.model.dao.EventDAO;
import br.com.gbraghim.eventmanager.model.domain.Event;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest extends TestCase{
    @Mock
    private EventDAO eventDAO;
    @InjectMocks EventService eventService = new EventService();
    Event event = new Event();

    @Before
    public void initializeMockito(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegistrarEvento(){
        //o que eu preciso (cenario)
        int vagas = 30;
        Date data = new Date();
        String titulo = "Apple event";
        String descricao = "Releasing new MacBook, iPhone and iOS";
        int id = 1;
        boolean disponibilidade = true;

        //o que quero testar
        eventService.registraEvent(id, titulo, descricao, data, vagas, disponibilidade);

        //o que quero que aconteça
        event.setDisponivel(disponibilidade);
        event.setVagasTotais(vagas);
        event.setData(new Date());
        event.setTitulo(titulo);
        event.setDescricao(descricao);
        event.setId(id);

        //Mockito verifica o mock/objeto
        Mockito.verify(eventDAO).createEvent(any(Event.class));
    }

    @Test
    public void testAlteraTituloEvento(){
        String titulo = "Apple event";
        int id = 1;
        String novoTitulo = "WWDC 2017";

        event.setId(id);
        event.setTitulo(titulo);

        when(eventDAO.getById(id)).thenReturn(event);
        eventService.alterarTitulo(id,novoTitulo);

        Event newEvent = new Event();
        newEvent.setTitulo(novoTitulo);
        newEvent.setId(id);

        Mockito.verify(eventDAO).updateEvent(event);
    }

    @Test
    public void testAlteraDescricaoEvento(){
        String descricao = "abc";
        String novaDescricao = "lll";
        int id = 1;

        event.setId(id);
        event.setDescricao(descricao);

        when(eventDAO.getById(id)).thenReturn(event);
        eventService.alterarDescricao(id,novaDescricao);

        Event newEvent = new Event();
        newEvent.setId(id);
        newEvent.setDescricao(novaDescricao);

        Mockito.verify(eventDAO).updateEvent(event);
    }

    @Test
    public void testAlteraVagasEvento(){
        int vagas = 23;
        int novasVagas = 36;
        int id = 1;

        event.setId(id);
        event.setVagasTotais(vagas);

        when(eventDAO.getById(id)).thenReturn(event);
        eventService.alterarVagas(id,novasVagas);

        Event newEvent = new Event();
        newEvent.setId(id);
        newEvent.setVagasTotais(novasVagas);

        Mockito.verify(eventDAO).updateEvent(event);
    }

    @Test
    public void testAlteraDataEvento(){
        Date data = new Date();
        int id = 1;

        event.setId(id);
        event.setData(data);
        Date novaData = new Date();

        when(eventDAO.getById(id)).thenReturn(event);
        eventService.alterarData(id,novaData);

        Event newEvent = new Event();
        newEvent.setId(id);
        newEvent.setData(novaData);

        Mockito.verify(eventDAO).updateEvent(event);
    }

    @Test
    public void testAlteraDisponibilidade(){
        boolean disponivel = true;
        int id = 1;
        boolean novoDisponivel = false;

        event.setId(id);
        event.setDisponivel(disponivel);

        when(eventDAO.getById(id)).thenReturn(event);
        eventService.alterarDisponibilidade(id,novoDisponivel);

        Event newEvent = new Event();
        newEvent.setId(id);
        newEvent.setDisponivel(novoDisponivel);

        Mockito.verify(eventDAO).updateEvent(event);
    }


}