package br.com.indtextbr.services.erpservice.dto;

import java.math.BigDecimal;

import br.com.indtextbr.services.erpservice.entity.FaseIndustrial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusFaseIndustrialDTO {
	private FaseIndustrial fase;
	private BigDecimal percentualConcluido;
	private Boolean possuiParadaProducao;
}
