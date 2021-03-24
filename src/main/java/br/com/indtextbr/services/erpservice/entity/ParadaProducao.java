package br.com.indtextbr.services.erpservice.entity;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_parada_producao")
public class ParadaProducao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@OneToOne
	private Turno turno;
	
	@NotNull
	@ManyToOne
	private LinhaProducao linha;
	
	@NotNull
	private LocalDateTime dataHoraInicio;
	
	private LocalDateTime dataHoraFim;
	
}
