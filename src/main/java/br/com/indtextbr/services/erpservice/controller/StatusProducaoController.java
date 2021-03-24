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
import br.com.indtextbr.services.erpservice.service.StatusFaseIndustrialService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("status-producao")
public class StatusProducaoController {
	private StatusFaseIndustrialService statusFaseIndustrialService;
	
	@Autowired
	public StatusProducaoController(StatusFaseIndustrialService statusFaseIndustrialService) {
		this.statusFaseIndustrialService =statusFaseIndustrialService;
	}
	
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<List<StatusProducaoDTO>> getAll() {
		var statusProducao = this.statusFaseIndustrialService.getStatusProducao();
		return new ResponseEntity<List<StatusProducaoDTO>>(statusProducao, HttpStatus.OK);
	}
}
