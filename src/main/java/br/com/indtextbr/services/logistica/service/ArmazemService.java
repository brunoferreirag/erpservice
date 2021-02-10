package br.com.indtextbr.services.logistica.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.indtextbr.services.logistica.common.Constants;
import br.com.indtextbr.services.logistica.dto.ArmazemDTO;
import br.com.indtextbr.services.logistica.dto.GetArmazensRequestDTO;
import br.com.indtextbr.services.logistica.dto.GetArmazensResponseDTO;
import br.com.indtextbr.services.logistica.dto.InsertUpdateDeleteRequestDTO;
import br.com.indtextbr.services.logistica.entity.Armazem;
import br.com.indtextbr.services.logistica.repository.ArmazemRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ArmazemService {

	private ObjectMapper mapper;
	private ArmazemRepository armazemRepository;

	public ArmazemService(ObjectMapper mapper, ArmazemRepository armazemRepository) {
		this.mapper = mapper;
		this.armazemRepository = armazemRepository;
	}

	@KafkaListener(topics = "${spring.kafka.armazem-insert-update-delete.topico}", groupId = "${spring.kafka.consumer.group-id}")
	public void escreverArmazemNaBase(String payload) throws Exception {
		log.info(payload);
		InsertUpdateDeleteRequestDTO dto = this.mapper.readValue(payload, InsertUpdateDeleteRequestDTO.class);
		switch (dto.getAcao()) {
		case INSERT: {
			this.incluirArmazem(dto.getArmazem());
			break;
		}
		case UPDATE: {
			this.editarArmazem(dto.getArmazem());
			break;
		}
		default: {
			this.inativarArmazem(dto.getArmazem().getId());
			break;
		}
		}

	}

	private void incluirArmazem(ArmazemDTO armazemDTO) throws Exception {
		Armazem armazem = new Armazem();
		preencherArmazemEntityDeUmArmazemDTO(armazemDTO, armazem);
		this.armazemRepository.save(armazem);
	}

	public void editarArmazem(ArmazemDTO armazemDTO) throws Exception {

		Armazem armazemOptional = this.armazemRepository.findByIdAndStatus(armazemDTO.getId(), Constants.STATUS_ATIVO);

		if (armazemOptional!=null) {
			Armazem armazem = armazemOptional;
			preencherArmazemEntityDeUmArmazemDTO(armazemDTO, armazem);
			this.armazemRepository.save(armazem);
		}
		else {
			throw new Exception();
		}
		

	}

	public void inativarArmazem(String codigo) throws JsonProcessingException {
		Optional<Armazem> armazemOptional = this.armazemRepository.findById(codigo);
		if (armazemOptional.isPresent()) {
			Armazem armazem = armazemOptional.get();
			armazem.setStatus(Constants.STATUS_INATIVO);
			this.armazemRepository.save(armazem);
		}
	}

	@KafkaListener(topics = "${spring.kafka.armazem-read.topico}", groupId = "${spring.kafka.consumer.group-id}")
	@SendTo
	public String lerArmazens(String payload) throws JsonProcessingException {
		GetArmazensRequestDTO dto= mapper.readValue(payload, GetArmazensRequestDTO.class);
		if(dto.getCodigoArmazem() ==null) {
			return this.getAllArmazens(dto);
		}
		return this.getById(dto);
	}
	
	public String getAllArmazens(GetArmazensRequestDTO dto) throws JsonProcessingException {

		PageRequest page = PageRequest.of(dto.getPage(), dto.getSize());

		var armazensEntity = this.armazemRepository.findAllByStatus(Constants.STATUS_ATIVO, page);
		//var armazensEntity = this.armazemRepository.findAll(page);

		List<ArmazemDTO> armazens = new ArrayList<>();

		armazensEntity.forEach(armazem -> {
			ArmazemDTO armazemDTO = criarArmazemDTODeUmArmazemEntity(armazem);
			armazens.add(armazemDTO);
		});

		var resposta = new GetArmazensResponseDTO();
		resposta.setArmazens(armazens);
		resposta.setTotal(armazensEntity.getTotalElements());

		return this.mapper.writeValueAsString(resposta);
	}

	public String getById(GetArmazensRequestDTO dto) throws JsonProcessingException {
		var armazemEntity = this.armazemRepository.findByIdAndStatus(dto.getCodigoArmazem(), Constants.STATUS_ATIVO);
		List<ArmazemDTO> armazens = new ArrayList<>();
		var resposta = new GetArmazensResponseDTO();
		
		if (armazemEntity != null) {
			var armazemDTO = criarArmazemDTODeUmArmazemEntity(armazemEntity);	
			armazens.add(armazemDTO);
			resposta.setArmazens(armazens);
			resposta.setTotal((long)armazens.size());
		}
		return this.mapper.writeValueAsString(resposta);
	}

	private ArmazemDTO criarArmazemDTODeUmArmazemEntity(Armazem armazem) {
		ArmazemDTO dto = new ArmazemDTO();
		dto.setArmazenaItemsParaCompra(armazem.getArmazenaItemsParaCompra());
		dto.setArmazenaItemsParaVenda(armazem.getArmazenaItemsParaVenda());
		dto.setBairro(armazem.getBairro());
		dto.setCEP(armazem.getCEP());
		dto.setCidadeEstado(armazem.getCidadeEstado());
		dto.setEndereco(armazem.getEndereco());
		dto.setId(armazem.getId());
		dto.setStatus(armazem.getStatus());
		return dto;
	}

	private void preencherArmazemEntityDeUmArmazemDTO(ArmazemDTO armazemDTO, Armazem armazem) {
		armazem.setArmazenaItemsParaCompra(armazemDTO.getArmazenaItemsParaCompra());
		armazem.setArmazenaItemsParaVenda(armazemDTO.getArmazenaItemsParaVenda());
		armazem.setCEP(armazemDTO.getCEP());
		armazem.setCidadeEstado(armazemDTO.getCidadeEstado());
		armazem.setEndereco(armazemDTO.getEndereco());
		armazem.setId(armazemDTO.getId());
		armazem.setStatus(Constants.STATUS_ATIVO);	
		armazem.setBairro(armazemDTO.getBairro());
	}

}
