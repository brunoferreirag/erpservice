package br.com.indtextbr.services.erpservice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

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
	public void deveStatusProducao() {
        String uri = "/status-producao";
        ResponseEntity response = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        System.out.println(response.getBody());
	}
}
