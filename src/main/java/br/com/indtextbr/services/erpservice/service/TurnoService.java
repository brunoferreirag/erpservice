package br.com.indtextbr.services.erpservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indtextbr.services.erpservice.entity.Turno;
import br.com.indtextbr.services.erpservice.repository.TurnoRepository;

@Service
public class TurnoService {
	private TurnoRepository repository;
	@Autowired
	public TurnoService(TurnoRepository repository) {
		this.repository = repository;
	}
	
	public List<Turno> getAllTurnos(){
		return repository.findAll();
	}

}
