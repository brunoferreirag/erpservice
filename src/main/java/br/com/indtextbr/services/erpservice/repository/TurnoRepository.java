package br.com.indtextbr.services.erpservice.repository;

import org.springframework.stereotype.Repository;

import br.com.indtextbr.services.erpservice.entity.Turno;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

}
