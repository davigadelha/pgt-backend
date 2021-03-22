package br.gov.serpro.pgt.backend.service.impl;

import br.gov.serpro.pgt.backend.service.FuncionalidadeService;
import br.gov.serpro.pgt.backend.domain.Funcionalidade;
import br.gov.serpro.pgt.backend.repository.FuncionalidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Funcionalidade}.
 */
@Service
@Transactional
public class FuncionalidadeServiceImpl implements FuncionalidadeService {

    private final Logger log = LoggerFactory.getLogger(FuncionalidadeServiceImpl.class);

    private final FuncionalidadeRepository funcionalidadeRepository;

    public FuncionalidadeServiceImpl(FuncionalidadeRepository funcionalidadeRepository) {
        this.funcionalidadeRepository = funcionalidadeRepository;
    }

    @Override
    public Funcionalidade save(Funcionalidade funcionalidade) {
        log.debug("Request to save Funcionalidade : {}", funcionalidade);
        return funcionalidadeRepository.save(funcionalidade);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionalidade> findAll() {
        log.debug("Request to get all Funcionalidades");
        return funcionalidadeRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Funcionalidade> findOne(Long id) {
        log.debug("Request to get Funcionalidade : {}", id);
        return funcionalidadeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Funcionalidade : {}", id);
        funcionalidadeRepository.deleteById(id);
    }
}
