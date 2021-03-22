package br.gov.serpro.pgt.backend.service.impl;

import br.gov.serpro.pgt.backend.service.SolicitacaoService;
import br.gov.serpro.pgt.backend.domain.Solicitacao;
import br.gov.serpro.pgt.backend.repository.SolicitacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Solicitacao}.
 */
@Service
@Transactional
public class SolicitacaoServiceImpl implements SolicitacaoService {

    private final Logger log = LoggerFactory.getLogger(SolicitacaoServiceImpl.class);

    private final SolicitacaoRepository solicitacaoRepository;

    public SolicitacaoServiceImpl(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
    }

    @Override
    public Solicitacao save(Solicitacao solicitacao) {
        log.debug("Request to save Solicitacao : {}", solicitacao);
        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Solicitacao> findAll() {
        log.debug("Request to get all Solicitacaos");
        return solicitacaoRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Solicitacao> findOne(Long id) {
        log.debug("Request to get Solicitacao : {}", id);
        return solicitacaoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Solicitacao : {}", id);
        solicitacaoRepository.deleteById(id);
    }
}
