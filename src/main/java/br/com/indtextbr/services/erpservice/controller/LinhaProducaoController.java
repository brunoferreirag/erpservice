package br.com.indtextbr.services.erpservice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.indtextbr.services.erpservice.dto.StatusProducaoDTO;
import br.com.indtextbr.services.erpservice.entity.LinhaProducao;
import br.com.indtextbr.services.erpservice.service.LinhaProducaoService;
import br.com.indtextbr.services.erpservice.service.StatusFaseIndustrialService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("linha-producao")
public class LinhaProducaoController {
	private LinhaProducaoService service;
	
	@Autowired
	public LinhaProducaoController(LinhaProducaoService service) {
		this.service =service;
	}
	
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<List<LinhaProducao>> getAll() {
		var linhaProducao = this.service.getAllLinhasProducao();
		return new ResponseEntity<List<LinhaProducao>>(linhaProducao, HttpStatus.OK);
	}
}
