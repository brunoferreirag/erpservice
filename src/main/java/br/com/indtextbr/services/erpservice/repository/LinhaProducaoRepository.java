package br.com.indtextbr.services.erpservice.repository;

import org.springframework.stereotype.Repository;

import br.com.indtextbr.services.erpservice.entity.LinhaProducao;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LinhaProducaoRepository extends JpaRepository<LinhaProducao, Long> {

}
