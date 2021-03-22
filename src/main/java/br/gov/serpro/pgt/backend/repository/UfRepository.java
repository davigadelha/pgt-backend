package br.gov.serpro.pgt.backend.repository;

import br.gov.serpro.pgt.backend.domain.Uf;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Uf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {
}
