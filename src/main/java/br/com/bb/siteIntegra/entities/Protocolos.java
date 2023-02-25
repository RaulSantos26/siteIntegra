package br.com.bb.siteIntegra.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_prc")
public class Protocolos {

    @Id
    @Column(name ="CD_PRC" )
    private Long cdPrc;
    @Column(name ="DT_ENTD_PRC" )
    private String dtEntrada;

    @Column(name ="DT_SAID_PRC" )
    private String dtSaida;

    @Column(name ="CD_ETP" )
    private Integer cdEtp;

    @Column(name ="IDENTIFIC1" )
    private String identific;

}
