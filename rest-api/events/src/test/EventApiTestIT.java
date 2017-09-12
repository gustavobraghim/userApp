import br.com.gbraghim.eventmanager.api.application.EventApplication;
import br.com.gbraghim.eventmanager.model.domain.Event;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {EventApplication.class})
public class EventApiTestIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testeEvento(){
        String url = "/events";
        List forObject = this.restTemplate.getForObject(url, List.class);
        Assert.assertEquals(0, forObject.size());

        Event eventToCreate = new Event();
        eventToCreate.setId(1);
        eventToCreate.setDisponivel(true);
        eventToCreate.setData(new Date());
        eventToCreate.setVagasTotais(40);
        eventToCreate.setTitulo("Novo evento");
        eventToCreate.setDescricao("Um novo evento que esta sendo criado");

        ResponseEntity<Event> eventResponseEntity = this.restTemplate.postForEntity(url, eventToCreate , Event.class);

        Assert.assertNotNull(eventResponseEntity.getBody().getId());
        Assert.assertEquals(eventToCreate.getId(), eventResponseEntity.getBody().getId());

        Assert.assertNotNull(eventResponseEntity.getBody().getTitulo());
        Assert.assertNotNull(eventToCreate.getTitulo(), eventResponseEntity.getBody().getTitulo());

        Assert.assertNotNull(eventResponseEntity.getBody().getDescricao());
        Assert.assertEquals(eventToCreate.getDescricao(), eventResponseEntity.getBody().getDescricao());

        Assert.assertNotNull(eventResponseEntity.getBody().getDisponivel());
        Assert.assertEquals(eventToCreate.getDisponivel(), eventResponseEntity.getBody().getDisponivel());

        Assert.assertNotNull(eventResponseEntity.getBody().getVagasTotais());
        Assert.assertEquals(eventToCreate.getVagasTotais(), eventResponseEntity.getBody().getVagasTotais());

        Assert.assertNotNull(eventResponseEntity.getBody().getData());
        Assert.assertEquals(eventToCreate.getData().toString(), eventResponseEntity.getBody().getData().toString());
    }

    @Test
    public void alteraTitulo(){
        Event eventToCreate = new Event();
        eventToCreate.setId(1);
        eventToCreate.setTitulo("Titulo 1");
        String newTitulo = "Titulo 2";

        eventToCreate.setTitulo(newTitulo);
        Assert.assertEquals(newTitulo,eventToCreate.getTitulo());

    }

    @Test
    public void alteraData(){
        Event eventToCreate = new Event();
        eventToCreate.setId(1);
        eventToCreate.setData(new Date());
        Date newData = new Date();

        eventToCreate.setData(newData);
        Assert.assertEquals(newData, eventToCreate.getData());
    }

    @Test
    public void alteraDescricao(){
        Event eventToCreate = new Event();
        eventToCreate.setId(1);
        eventToCreate.setDescricao("Primeira descrição");
        String novaDescricao = "Segunda descrição";

        eventToCreate.setDescricao(novaDescricao);
        Assert.assertEquals(novaDescricao, eventToCreate.getDescricao());
    }

    @Test
    public void alteraDisponibilidade(){
        Event eventToCreate = new Event();
        eventToCreate.setId(1);
        eventToCreate.setDisponivel(true);
        Boolean novaDisponibilidade = false;

        eventToCreate.setDisponivel(novaDisponibilidade);
        Assert.assertEquals(novaDisponibilidade, eventToCreate.getDisponivel());
    }

    @Test
    public void alteraVagasTotais(){
        Event eventToCreate = new Event();
        eventToCreate.setId(1);
        eventToCreate.setVagasTotais(40);
        int novasVagas = 50;

        eventToCreate.setVagasTotais(novasVagas);
        Assert.assertEquals(novasVagas, eventToCreate.getVagasTotais());
    }
}