package br.com.indtextbr.services.erpservice.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.indtextbr.services.erpservice.dto.ParadaProducaoDTO;
import br.com.indtextbr.services.erpservice.entity.ParadaProducao;
import br.com.indtextbr.services.erpservice.service.ParadaProducaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("parada-producao")
public class ParadaProducaoController {
	private ParadaProducaoService paradaProducaoService;
	
	@Autowired
	public ParadaProducaoController(ParadaProducaoService paradaProducaoService) {
		this.paradaProducaoService =paradaProducaoService;
	}
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<Page<ParadaProducaoDTO>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name = "size", defaultValue = "10")int size) {
		Page<ParadaProducaoDTO> paradasProducao = this.paradaProducaoService.getAll(page, size);
		return new ResponseEntity<Page<ParadaProducaoDTO>>(paradasProducao, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}", produces = { "application/json" })
	public ResponseEntity<ParadaProducaoDTO> getParadaProducaoById(@PathVariable(value="id") Long id) {
		var paradaProducao = this.paradaProducaoService.getById(id);
		return new ResponseEntity<>((paradaProducao.isEmpty())?null:paradaProducao.get(), (paradaProducao.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@PostMapping(produces = { "application/json" })
	public ResponseEntity<Object> incluirParada(@RequestBody @Valid ParadaProducaoDTO paradaProducao){
		Long id = this.paradaProducaoService.incluir(paradaProducao).getId();
		URI location = URI.create(String.format("/parada-producao/%s", id));
		return ResponseEntity.created(location).body(id);
	}
	
	@PutMapping(value="/{id}",produces = { "application/json" })
	public ResponseEntity<Void> atualizarParada(@PathVariable(value="id") Long id, @RequestBody @Valid ParadaProducaoDTO paradaProducao){
		paradaProducao.setId(id);
		this.paradaProducaoService.alterar(paradaProducao);
		return ResponseEntity.accepted().build();
	}
	
	@DeleteMapping(value="/{id}",produces = { "application/json" })
	public ResponseEntity<Void> excluirParada(@PathVariable(value="id") Long id){
		this.paradaProducaoService.excluir(id);
		return ResponseEntity.accepted().build();
	}
}
