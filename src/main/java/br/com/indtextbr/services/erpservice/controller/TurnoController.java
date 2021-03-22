package br.com.indtextbr.services.erpservice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.indtextbr.services.erpservice.entity.Turno;
import br.com.indtextbr.services.erpservice.service.TurnoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("turno")
public class TurnoController {
	private TurnoService turnoService;
	
	@Autowired
	public TurnoController(TurnoService turnoService) {
		this.turnoService = turnoService;
	}
	
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<List<Turno>> getAll() {
		var turnos = this.turnoService.getAllTurnos();
		return new ResponseEntity<List<Turno>>(turnos, HttpStatus.OK);
	}
}
