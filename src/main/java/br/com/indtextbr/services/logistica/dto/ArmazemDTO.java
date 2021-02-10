package br.com.indtextbr.services.logistica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArmazemDTO {
	private String Id;

	private String endereco;
	
	private String bairro;
	
	@JsonProperty("cidade-estado")
	private String cidadeEstado;
	
	@JsonProperty("cep")
	private String CEP;
	
	@JsonProperty("armazena-items-para-venda")
	private Boolean armazenaItemsParaVenda;
	
	@JsonProperty("armazena-items-para-compra")
	private Boolean armazenaItemsParaCompra;
	
	private String status;
}
