package br.com.bb.siteIntegra.dto;


import br.com.bb.siteIntegra.entities.DadosResgate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DadosResgateDTO {

    private Long id;



    @PositiveOrZero
    @NotNull
    private BigInteger cfp_cnpj;
    private String nome;
    private String representante;

    @Size(min = 2, max = 2)
    private String tipo;
    private Long finalidade;
    private Long idProcesso;



    public DadosResgateDTO(DadosResgate entity) {
        id = entity.getId();
        nome = entity.getNome();
        tipo = entity.getTipo();
        cfp_cnpj = entity.getCfp_cnpj();
        representante = entity.getRepresentante();
//        judicial = entity.getJudicial();
        finalidade = entity.getFinalidade().getId();
        idProcesso = entity.getIdProcesso();






    }
}
