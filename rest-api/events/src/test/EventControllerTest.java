import br.com.gbraghim.eventmanager.api.controller.EventController;
import br.com.gbraghim.eventmanager.model.domain.Event;
import br.com.gbraghim.eventmanager.service.EventService;
import br.com.gbraghim.eventmanager.service.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
@RestController
public class EventControllerTest extends TestCase{
    @Mock
    private EventService eventService;
    @InjectMocks
    EventController eventController = new EventController();
    Event event = new Event();

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void initializeMockito(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createEventTest() throws ResourceNotFoundException {
        // O que eu preciso (cenario)
        String titulo = "evento";
        String descricao = "descricao";
        int id = 1;
        Date data = new Date();
        Boolean disponivel = true;
        int vagas = 50;

        // O que quero testar
        event.setVagasTotais(vagas);
        event.setDisponivel(disponivel);
        event.setDescricao(descricao);
        event.setData(new Date());
        event.setTitulo(titulo);
        event.setId(id);
        eventController.createEvent(event);

        // O que espero que aconteca
        Mockito.verify(eventService).registraEvent(id, titulo, descricao, data, vagas, disponivel);
    }
    @Test
    public void editaTitulo(){
        int id = 1;
        String titulo = "Titulo1";
        String novoTitulo = "Titulo2";

        event.setId(id);
        event.setTitulo(titulo);

        eventController.editEventTitulo(id, event);

        Event novoEvento = new Event();
        novoEvento.setId(id);
        novoEvento.setTitulo(novoTitulo);

        Mockito.verify(eventService).alterarTitulo(id, titulo);
    }
    @Test
    public void editaDescricao(){
        int id = 1;
        String descricao = "descricao";
        String novaDescricao = "descrição 2";

        event.setId(id);
        event.setDescricao(descricao);

        eventController.editDescricaoEvent(id, event);

        Event newEvent = new Event();
        newEvent.setId(id);
        newEvent.setDescricao(novaDescricao);

        Mockito.verify(eventService).alterarDescricao(id, descricao);
    }
    @Test
    public void editaVagas(){
        int id = 1;
        int vagas = 30;
        int novasVagas = 40;

        event.setId(id);
        event.setVagasTotais(vagas);

        eventController.editCapacidadeEvent(id, event);

        Event newEvent = new Event();
        newEvent.setId(id);
        newEvent.setVagasTotais(novasVagas);

        Mockito.verify(eventService).alterarVagas(id, vagas);
    }
    @Test
    public void editDisponibilidade(){
        int id = 1;
        boolean disponibilidade = true;
        boolean novaDisponib = false;

        event.setDisponivel(disponibilidade);
        event.setId(id);

        eventController.editDisponibilidadeEvent(id, event);

        Event newEvent = new Event();
        newEvent.setId(id);
        newEvent.setDisponivel(novaDisponib);

        Mockito.verify(eventService).alterarDisponibilidade(id, disponibilidade);
    }

    @Test
    public void editData(){
        int id = 1;
        Date data = new Date();

        event.setId(id);
        event.setData(data);

        eventController.editDataEvent(id, event);
        Date newData = new Date();

        Event newEvent = new Event();
        newEvent.setId(id);
        newEvent.setData(newData);

        Mockito.verify(eventService).alterarData(id, data);
    }

    @Test
    public void deleteEventTest(){
        int id = 1;
        event.setId(1);

        eventController.deleteEvent(id);

        Mockito.verify(eventService).deletaEvent(id);
    }
}
