package br.com.indtextbr.services.erpservice.dto;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParadaProducaoDTO {
	private Long id;
	@NotNull
	private TurnoDTO turno;
	@NotNull
	private LinhaProducaoDTO linha;
	
	@NotNull
	private LocalDateTime dataHoraInicio;
	
	private LocalDateTime dataHoraFim;
	
}
