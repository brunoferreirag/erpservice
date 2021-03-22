package br.com.indtextbr.services.erpservice.repository;

import org.springframework.stereotype.Repository;

import br.com.indtextbr.services.erpservice.entity.ParadaProducao;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface LinhaProducaoRepository extends PagingAndSortingRepository<ParadaProducao, Long> {

}
