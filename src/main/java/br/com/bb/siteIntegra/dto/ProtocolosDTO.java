package br.com.bb.siteIntegra.dto;

import br.com.bb.siteIntegra.entities.Protocolos;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProtocolosDTO {
    private Long cdPrc;
    private String dtEntrada;
    private String dtSaida;
    private Integer cdEtp;
    private String identific;


    public ProtocolosDTO(Protocolos entity){
        cdPrc = entity.getCdPrc();
        dtEntrada = entity.getDtEntrada();
        dtSaida = entity.getDtSaida();
        cdEtp = entity.getCdEtp();
        identific = entity.getIdentific();
    }
}
