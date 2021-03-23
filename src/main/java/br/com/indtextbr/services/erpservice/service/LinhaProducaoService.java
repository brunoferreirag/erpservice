package br.com.indtextbr.services.erpservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indtextbr.services.erpservice.entity.LinhaProducao;
import br.com.indtextbr.services.erpservice.repository.LinhaProducaoRepository;

@Service
public class LinhaProducaoService {
	private LinhaProducaoRepository repository;
	@Autowired
	public LinhaProducaoService(LinhaProducaoRepository repository) {
		this.repository = repository;
	}
	
	public List<LinhaProducao> getAllLinhasProducao(){
		return repository.findAll();
	}

}
