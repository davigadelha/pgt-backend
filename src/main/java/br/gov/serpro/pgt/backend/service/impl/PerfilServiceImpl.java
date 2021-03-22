package br.gov.serpro.pgt.backend.service.impl;

import br.gov.serpro.pgt.backend.service.PerfilService;
import br.gov.serpro.pgt.backend.domain.Perfil;
import br.gov.serpro.pgt.backend.repository.PerfilRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Perfil}.
 */
@Service
@Transactional
public class PerfilServiceImpl implements PerfilService {

    private final Logger log = LoggerFactory.getLogger(PerfilServiceImpl.class);

    private final PerfilRepository perfilRepository;

    public PerfilServiceImpl(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @Override
    public Perfil save(Perfil perfil) {
        log.debug("Request to save Perfil : {}", perfil);
        return perfilRepository.save(perfil);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Perfil> findAll() {
        log.debug("Request to get all Perfils");
        return perfilRepository.findAllWithEagerRelationships();
    }


    public Page<Perfil> findAllWithEagerRelationships(Pageable pageable) {
        return perfilRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Perfil> findOne(Long id) {
        log.debug("Request to get Perfil : {}", id);
        return perfilRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Perfil : {}", id);
        perfilRepository.deleteById(id);
    }
}
