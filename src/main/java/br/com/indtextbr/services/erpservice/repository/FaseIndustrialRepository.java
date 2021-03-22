package br.com.indtextbr.services.erpservice.repository;

import org.springframework.stereotype.Repository;

import br.com.indtextbr.services.erpservice.entity.FaseIndustrial;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface FaseIndustrialRepository extends PagingAndSortingRepository<FaseIndustrial, Long> {

}
