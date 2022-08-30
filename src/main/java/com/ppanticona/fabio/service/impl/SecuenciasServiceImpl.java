package com.ppanticona.fabio.service.impl;

import com.ppanticona.fabio.domain.Secuencias;
import com.ppanticona.fabio.repository.SecuenciasRepository;
import com.ppanticona.fabio.service.SecuenciasService;
import org.springframework.stereotype.Service;

@Service
public class SecuenciasServiceImpl implements SecuenciasService {

    private final SecuenciasRepository secuenciasRepository;

    public SecuenciasServiceImpl(SecuenciasRepository secuenciasRepository) {
        this.secuenciasRepository = secuenciasRepository;
    }

    @Override
    public Integer getNextByNameSeq(String nameSeq) {
        Secuencias secuencia = secuenciasRepository.findBySeqid(nameSeq);
        Integer seq = secuencia.getSequence();
        secuencia.setSequence(seq + 1);
        secuenciasRepository.save(secuencia);
        return seq;
    }
}
