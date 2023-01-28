package br.com.bb.siteIntegra.services;


import br.com.bb.siteIntegra.dto.DadosResgateDTO;
import br.com.bb.siteIntegra.entities.DadosResgate;
import br.com.bb.siteIntegra.entities.Finalidade;
import br.com.bb.siteIntegra.repositories.DadosResgateRepository;
import br.com.bb.siteIntegra.services.exceptions.ResourcesNotFoudException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DadosResgateService {
    @Autowired
    private DadosResgateRepository repository;


    public DadosResgateDTO insert(DadosResgateDTO dto) {
        try {
            DadosResgate entity = new DadosResgate();
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new DadosResgateDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourcesNotFoudException("Recurso não encontrado");
        }
        catch (DataIntegrityViolationException e) {
            throw new ResourcesNotFoudException("Campo obrigatório");
        }


    }

    private void copyDtoToEntity(DadosResgateDTO dto, DadosResgate entity) {
        entity.setCfp_cnpj(dto.getCfp_cnpj());
        entity.setNome(dto.getNome());
        entity.setRepresentante(dto.getRepresentante());
        entity.setTipo(dto.getTipo());
//        entity.setJudicial(dto.getJudicial());
        entity.setIdProcesso(dto.getId());

        Finalidade finalidade = new Finalidade();
        finalidade.setId(dto.getFinalidade());
        entity.setFinalidade(finalidade);

    }

}
