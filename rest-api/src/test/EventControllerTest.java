import br.com.gbraghim.eventmanager.model.domain.Event;
import br.com.gbraghim.eventmanager.service.EventService;
import br.gbraghim.eventmanager.api.controller.EventController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {
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
    public void testCreateEvent(){
        String titulo = "Titulo";
        String descricao = "Descricao";
        int vagas = 40;
        boolean disponibilidade = true;
        int id = 1;
        Date data = new Date();


        event.setVagasTotais(vagas);
        event.setId(id);
        event.setTitulo(titulo);
        event.setDescricao(descricao);
        event.setData(data);
        event.setDisponivel(disponibilidade);

        eventController.createEvent(event);

        Mockito.verify(eventService).registraEvent(id,titulo,descricao, data, vagas, disponibilidade);


    }

    @Test
    public void testEditEventTitulo(){
        String titulo = "Titulo";
        int id = 1;

        event.setId(id);
        event.setTitulo(titulo);

        eventController.editEventTitulo(id, event);

        Mockito.verify(eventService).alterarTitulo(id,titulo);
    }

    @Test
    public  void testEditDataEvent(){
        Date data = new Date();
        int id = 1;

        event.setId(id);
        event.setData(data);

        eventController.editDataEvent(id, event);

        Mockito.verify(eventService).alterarData(id, data);
    }

    @Test
    public void testEditDescricaoEvento(){

        String descricao = "Desc";
        int id = 1;

        event.setId(id);
        event.setDescricao(descricao);

        eventController.editDescricaoEvent(id, event);

        Mockito.verify(eventService).alterarDescricao(id, descricao);

    }
    @Test
    public void testEditDisponibilidadeEvento(){
        boolean disponibilidade = true;
        int id = 1;

        event.setId(id);
        event.setDisponivel(true);

        eventController.editDisponibilidadeEvent(id, event);

        Mockito.verify(eventService).alterarDisponibilidade(id, disponibilidade);
    }
    @Test
    public void testEditCapacidadeEvento(){
        int capacidade = 40;
        int id = 1;

        event.setId(id);
        event.setVagasTotais(capacidade);

        eventController.editCapacidadeEvent(id, event);
        Mockito.verify(eventService).alterarVagas(id, capacidade);
    }
}
