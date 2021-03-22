package br.gov.serpro.pgt.backend.repository;

import br.gov.serpro.pgt.backend.domain.Perfil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Perfil entity.
 */
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query(value = "select distinct perfil from Perfil perfil left join fetch perfil.funcionalidades",
        countQuery = "select count(distinct perfil) from Perfil perfil")
    Page<Perfil> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct perfil from Perfil perfil left join fetch perfil.funcionalidades")
    List<Perfil> findAllWithEagerRelationships();

    @Query("select perfil from Perfil perfil left join fetch perfil.funcionalidades where perfil.id =:id")
    Optional<Perfil> findOneWithEagerRelationships(@Param("id") Long id);
}