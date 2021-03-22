package br.com.indtextbr.services.erpservice.repository;

import org.springframework.stereotype.Repository;

import br.com.indtextbr.services.erpservice.entity.ParadaProducao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface ParadaProducaoRepository extends PagingAndSortingRepository<ParadaProducao, Long> {
	List<ParadaProducao> findByDataHoraFim(LocalDateTime dataHoraFim);
	Page<ParadaProducao> findAllByOrderByDataHoraInicioDesc(Pageable page);
}
