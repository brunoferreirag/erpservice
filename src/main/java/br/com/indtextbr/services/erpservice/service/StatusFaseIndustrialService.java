package br.com.indtextbr.services.erpservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indtextbr.services.erpservice.dto.StatusProducaoDTO;
import br.com.indtextbr.services.erpservice.entity.ParadaProducao;
import br.com.indtextbr.services.erpservice.repository.FaseIndustrialRepository;

@Service
public class StatusFaseIndustrialService {
	private ParadaProducaoService paradaService;
	private FaseIndustrialRepository faseRepository;
	private BigDecimal quantidadeMinutosDia = new BigDecimal(1440);
	
	@Autowired
	public StatusFaseIndustrialService(ParadaProducaoService paradaProducaoService, FaseIndustrialRepository faseRepository) {
		this.paradaService = paradaProducaoService;
		this.faseRepository = faseRepository;
	}
	
	public List<StatusProducaoDTO> getStatusProducao(){
		var fasesIndustriais = this.faseRepository.findAll();
		List<ParadaProducao> paradas= this.paradaService.getAllAtivos();
		List<StatusProducaoDTO> statusFases = new ArrayList<>(); 
		fasesIndustriais.forEach(f -> {
			StatusProducaoDTO dto = new StatusProducaoDTO();
			dto.setFase(f);
			Boolean possuiParadaProducaoAtiva = paradas.stream().anyMatch(q->q.getLinha().getFase().getId().equals(f.getId()));
			dto.setPossuiParadaProducao(possuiParadaProducaoAtiva);
			LocalDateTime dataAtual = LocalDateTime.now();
		    LocalDateTime dataInicial = LocalDateTime.of(dataAtual.getYear(), dataAtual.getMonth(), dataAtual.getDayOfMonth(), 0, 0);
		    BigDecimal diferencaMinutos =new BigDecimal(ChronoUnit.MINUTES.between(dataInicial, dataAtual));
		    BigDecimal percentual =  diferencaMinutos.divide(quantidadeMinutosDia, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
		    dto.setPercentualConcluido(percentual);
		    dto.setPercentualPlanejado(percentual);
		    statusFases.add(dto);
		});
		
		return statusFases;
	}
}
