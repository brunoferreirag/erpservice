package br.com.indtextbr.services.erpservice.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.indtextbr.services.erpservice.entity.FaseIndustrial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusProducaoDTO {
	private FaseIndustrial fase;
	@JsonProperty("percentual-concluido")
	private BigDecimal percentualConcluido;
	@JsonProperty("percentual-planejado")
	private BigDecimal percentualPlanejado;
	@JsonProperty("possui-parada-producao")
	private Boolean possuiParadaProducao;
}
