package br.com.indtextbr.services.erpservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.indtextbr.services.erpservice.dto.LinhaProducaoDTO;
import br.com.indtextbr.services.erpservice.dto.ParadaProducaoDTO;
import br.com.indtextbr.services.erpservice.dto.TurnoDTO;
import br.com.indtextbr.services.erpservice.entity.LinhaProducao;
import br.com.indtextbr.services.erpservice.entity.ParadaProducao;
import br.com.indtextbr.services.erpservice.entity.Turno;
import br.com.indtextbr.services.erpservice.repository.LinhaProducaoRepository;
import br.com.indtextbr.services.erpservice.repository.ParadaProducaoRepository;
import br.com.indtextbr.services.erpservice.repository.TurnoRepository;

@Service
public class ParadaProducaoService {
	private ParadaProducaoRepository repository;
	private LinhaProducaoRepository linhaRepository;
	private TurnoRepository turnoRepository;
	
	@Autowired
	public ParadaProducaoService(ParadaProducaoRepository repository, LinhaProducaoRepository linhaRepository, TurnoRepository turnoRepository) {
		this.repository = repository;
		this.linhaRepository = linhaRepository;
		this.turnoRepository = turnoRepository;
	}
	public List<ParadaProducao> getAllAtivos() {
		return this.repository.findByDataHoraFim(null);
	}
	
	public Page<ParadaProducaoDTO> getAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		var resultados = this.repository.findAllByOrderByDataHoraInicioDesc(pageRequest);
		List<ParadaProducaoDTO> lista = new ArrayList<>();
		Page<ParadaProducaoDTO> pageResult = new PageImpl<>(lista);
		resultados.getContent().forEach(resultado -> {
			ParadaProducaoDTO parada = criarParadaProducaoDTO(resultado);
			
			lista.add(parada);
		});
		return pageResult;
	}
	private ParadaProducaoDTO criarParadaProducaoDTO(ParadaProducao resultado) {
		ParadaProducaoDTO parada = new ParadaProducaoDTO();
		parada.setDataHoraFim(resultado.getDataHoraFim());
		parada.setDataHoraInicio(resultado.getDataHoraInicio());
		parada.setId(resultado.getId());
		
		LinhaProducaoDTO linhaDTO = new LinhaProducaoDTO();
		linhaDTO.setId(resultado.getLinha().getId());
		parada.setLinha(linhaDTO);
		
		TurnoDTO turno = new TurnoDTO();
		turno.setId(resultado.getTurno().getId());
		parada.setTurno(turno);
		return parada;
	}
	
	public Optional<ParadaProducaoDTO> getById(Long id) {
		Optional<ParadaProducaoDTO> dtoOptional = Optional.empty();
		var resultado = this.repository.findById(id);
		if(resultado.isPresent()) {
			return Optional.of(this.criarParadaProducaoDTO(resultado.get()));
		}
		return dtoOptional;
	}
	public ParadaProducao incluir(ParadaProducaoDTO entity) {
		ParadaProducao parada = new ParadaProducao();
		preencherParadaProducao(entity, parada);
		
		return this.repository.save(parada);
	}
	private void preencherParadaProducao(ParadaProducaoDTO entity, ParadaProducao parada) {
		parada.setDataHoraInicio(entity.getDataHoraInicio());
		parada.setDataHoraFim(entity.getDataHoraFim());
		
		Optional<LinhaProducao> linha = this.linhaRepository.findById(entity.getLinha().getId());
		Optional<Turno> turno = this.turnoRepository.findById(entity.getTurno().getId());
		
		if(linha.isEmpty() || turno.isEmpty()) {
			throw new IllegalArgumentException("Turno ou Linha de Produção Inválidos");
		}
		
		parada.setLinha(linha.get());
		parada.setTurno(turno.get());
	}
	
	public Boolean excluir(Long id) {
		Optional<ParadaProducao> parada = this.repository.findById(id);
		if(parada.isPresent()) {
			this.repository.delete(parada.get());
			return true;
		}
		return false;
	}
	
	public ParadaProducao alterar(ParadaProducaoDTO entity) {
		var paradaNoBanco = this.repository.findById(entity.getId());
		if(paradaNoBanco.isPresent()) {
			var paradaParaAtualizar = paradaNoBanco.get();
			preencherParadaProducao(entity, paradaParaAtualizar);
			return this.repository.save(paradaParaAtualizar);
		}
		
		return null;
		
	}
}
