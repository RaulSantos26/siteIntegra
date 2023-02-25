package br.com.bb.siteIntegra.services;

import br.com.bb.siteIntegra.dto.ProtocolosDTO;
import br.com.bb.siteIntegra.entities.Protocolos;
import br.com.bb.siteIntegra.repositories.ProtocolosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProtocolosService {

    @Autowired
    public ProtocolosRepository protocolosRepository;


    @Transactional(readOnly = true)
    public List<ProtocolosDTO> findAll(){
        List<Protocolos> result = protocolosRepository.findAll();
        return result.stream().map(x-> new ProtocolosDTO(x)).toList();
    }

}
