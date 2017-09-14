package br.com.gbraghim.eventmanager.model.test;

import br.com.gbraghim.eventmanager.model.dao.EventDAO;
import br.com.gbraghim.eventmanager.model.domain.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventDaoTest {

    @Mock
    EntityManager manager;

    @InjectMocks
    EventDAO eventDAO = new EventDAO();

    Event event;

    @Before
    public void setup(){
        event = new Event();
    }

    @Test
    public void testCrudEvent() {
        event.setDisponivel(true);
        int id = 1;
        event.setId(id);
        event.setData(new Date());
        event.setVagasTotais(40);
        event.setDescricao("Descricao");
        event.setTitulo("Evento");

        when(manager.getTransaction()).thenReturn(Mockito.mock(EntityTransaction.class));
        eventDAO.createEvent(event);
        Assert.assertEquals(event.getId(), id);
    }
    @Test
    public void testUpdateEvent(){
        int id = 1;
        event.setId(id);
        event.setData(new Date());
        event.setVagasTotais(40);
        event.setDescricao("Descricao");
        event.setTitulo("Evento");
        int novasVagas = 50;

        when(manager.getTransaction()).thenReturn(Mockito.mock(EntityTransaction.class));

        event.setVagasTotais(novasVagas);
        eventDAO.updateEvent(event);

        Assert.assertEquals(event.getId(), id);
    }

    @Test
    public void testDeleteEvent(){
        int id = 1;
        event.setId(id);

        when(manager.getTransaction()).thenReturn(Mockito.mock(EntityTransaction.class));
        eventDAO.deleteEvent(event);

        Assert.assertEquals(event.getId(), id);

    }
}
