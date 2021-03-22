package br.gov.serpro.pgt.backend.service.impl;

import br.gov.serpro.pgt.backend.service.CategoriaTipoValidacaoService;
import br.gov.serpro.pgt.backend.domain.CategoriaTipoValidacao;
import br.gov.serpro.pgt.backend.repository.CategoriaTipoValidacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CategoriaTipoValidacao}.
 */
@Service
@Transactional
public class CategoriaTipoValidacaoServiceImpl implements CategoriaTipoValidacaoService {

    private final Logger log = LoggerFactory.getLogger(CategoriaTipoValidacaoServiceImpl.class);

    private final CategoriaTipoValidacaoRepository categoriaTipoValidacaoRepository;

    public CategoriaTipoValidacaoServiceImpl(CategoriaTipoValidacaoRepository categoriaTipoValidacaoRepository) {
        this.categoriaTipoValidacaoRepository = categoriaTipoValidacaoRepository;
    }

    @Override
    public CategoriaTipoValidacao save(CategoriaTipoValidacao categoriaTipoValidacao) {
        log.debug("Request to save CategoriaTipoValidacao : {}", categoriaTipoValidacao);
        return categoriaTipoValidacaoRepository.save(categoriaTipoValidacao);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaTipoValidacao> findAll() {
        log.debug("Request to get all CategoriaTipoValidacaos");
        return categoriaTipoValidacaoRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriaTipoValidacao> findOne(Long id) {
        log.debug("Request to get CategoriaTipoValidacao : {}", id);
        return categoriaTipoValidacaoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoriaTipoValidacao : {}", id);
        categoriaTipoValidacaoRepository.deleteById(id);
    }
}
