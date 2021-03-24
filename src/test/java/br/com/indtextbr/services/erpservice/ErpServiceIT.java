package br.com.indtextbr.services.erpservice;

import java.net.MalformedURLException;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.indtextbr.services.erpservice.dto.LinhaProducaoDTO;
import br.com.indtextbr.services.erpservice.dto.ParadaProducaoDTO;
import br.com.indtextbr.services.erpservice.dto.TurnoDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ErpServiceIT {

    @Autowired
    private TestRestTemplate restTemplate;
    
	@Test
	public void deveRetornarTurno() {
        String uri = "/turno";
        ResponseEntity response = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        System.out.println(response.getBody());
	}
	
	@Test
	public void deveRetornarLinhaProducao() {
        String uri = "/linha-producao";
        ResponseEntity response = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        System.out.println(response.getBody());
	}
	
	@Test
	public void deveConsultarStatusProducao() {
        String uri = "/status-producao";
        ResponseEntity response = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        System.out.println(response.getBody());
	}
	
	@Test
	public void deveConsultarParadaProducao() {
        String uri = "/parada-producao?page=0&size=10";
        ResponseEntity response = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        System.out.println(response.getBody());
	}
	
	@Test
	public void deveIncluir() throws MalformedURLException {
        String uri = "/parada-producao";
        ParadaProducaoDTO dto = new ParadaProducaoDTO();
        dto.setDataHoraInicio(LocalDateTime.now());
        LinhaProducaoDTO linha = new LinhaProducaoDTO();
        linha.setId((long)1);
        dto.setLinha(linha);
        TurnoDTO turno = new TurnoDTO();
        turno.setId((long)1);
        dto.setTurno(turno);
        
        ResponseEntity<String> response = restTemplate.postForEntity(uri,dto, String.class);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        String urlDelete = uri + response.getHeaders().getLocation(); 
        //restTemplate.delete(urlDelete);
        
	}
}
