package br.gov.serpro.pgt.backend.repository;

import br.gov.serpro.pgt.backend.domain.Funcionalidade;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Funcionalidade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FuncionalidadeRepository extends JpaRepository<Funcionalidade, Long> {
}
