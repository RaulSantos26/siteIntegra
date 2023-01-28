package br.com.bb.siteIntegra.dto;


import br.com.bb.siteIntegra.entities.Finalidade;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FinalidadeDTO {

    private Long id;
    private String descricao;

    public FinalidadeDTO(Finalidade entity){
        id = entity.getId();
        descricao = entity.getDescricao();
    }
}
