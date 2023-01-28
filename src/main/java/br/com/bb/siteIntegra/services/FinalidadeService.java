package br.com.bb.siteIntegra.services;


import br.com.bb.siteIntegra.dto.FinalidadeDTO;
import br.com.bb.siteIntegra.entities.Finalidade;
import br.com.bb.siteIntegra.repositories.FinalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FinalidadeService {

    @Autowired
    private FinalidadeRepository repository;

    @Transactional(readOnly = true)
    public List<FinalidadeDTO> findAll(){
        List<Finalidade> result = repository.findAll();
        return result.stream().map(x-> new FinalidadeDTO(x)).toList();
    }

}
