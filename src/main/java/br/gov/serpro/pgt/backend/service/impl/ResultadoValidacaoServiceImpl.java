package br.gov.serpro.pgt.backend.service.impl;

import br.gov.serpro.pgt.backend.service.ResultadoValidacaoService;
import br.gov.serpro.pgt.backend.domain.ResultadoValidacao;
import br.gov.serpro.pgt.backend.repository.ResultadoValidacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ResultadoValidacao}.
 */
@Service
@Transactional
public class ResultadoValidacaoServiceImpl implements ResultadoValidacaoService {

    private final Logger log = LoggerFactory.getLogger(ResultadoValidacaoServiceImpl.class);

    private final ResultadoValidacaoRepository resultadoValidacaoRepository;

    public ResultadoValidacaoServiceImpl(ResultadoValidacaoRepository resultadoValidacaoRepository) {
        this.resultadoValidacaoRepository = resultadoValidacaoRepository;
    }

    @Override
    public ResultadoValidacao save(ResultadoValidacao resultadoValidacao) {
        log.debug("Request to save ResultadoValidacao : {}", resultadoValidacao);
        return resultadoValidacaoRepository.save(resultadoValidacao);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultadoValidacao> findAll() {
        log.debug("Request to get all ResultadoValidacaos");
        return resultadoValidacaoRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ResultadoValidacao> findOne(Long id) {
        log.debug("Request to get ResultadoValidacao : {}", id);
        return resultadoValidacaoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResultadoValidacao : {}", id);
        resultadoValidacaoRepository.deleteById(id);
    }
}
