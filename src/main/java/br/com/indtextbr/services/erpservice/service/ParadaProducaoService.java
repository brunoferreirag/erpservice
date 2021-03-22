package br.com.indtextbr.services.erpservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.indtextbr.services.erpservice.entity.ParadaProducao;
import br.com.indtextbr.services.erpservice.repository.ParadaProducaoRepository;

@Service
public class ParadaProducaoService {
	private ParadaProducaoRepository repository;
	
	@Autowired
	public ParadaProducaoService(ParadaProducaoRepository repository) {
		this.repository = repository;
	}
	public List<ParadaProducao> getAllAtivos() {
		return this.repository.findByDataHoraFim(null);
	}
	
	public Page<ParadaProducao> getAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return this.repository.findAllByOrderByDataHoraInicioDesc(pageRequest);
	}
	
	public Optional<ParadaProducao> getById(Long id) {
		return this.repository.findById(id);
	}
	public ParadaProducao incluir(ParadaProducao entity) {
		return this.repository.save(entity);
	}
	
	public Boolean excluir(Long id) {
		Optional<ParadaProducao> parada = this.repository.findById(id);
		if(parada.isPresent()) {
			this.repository.delete(parada.get());
			return true;
		}
		return false;
	}
	
	public ParadaProducao alterar(ParadaProducao entity) {
		var paradaNoBanco = this.repository.findById(entity.getId());
		if(paradaNoBanco.isPresent()) {
			var paradaParaAtualizar = paradaNoBanco.get();
			paradaParaAtualizar.setDataHoraFim(entity.getDataHoraFim());
			paradaParaAtualizar.setLinha(entity.getLinha());
			paradaParaAtualizar.setTurno(entity.getTurno());
			paradaParaAtualizar.setDataHoraInicio(entity.getDataHoraInicio());
			return this.repository.save(paradaParaAtualizar);
		}
		
		return null;
		
	}
}
