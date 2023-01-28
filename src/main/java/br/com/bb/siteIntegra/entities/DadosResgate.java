package br.com.bb.siteIntegra.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_dado_resgate")
public class DadosResgate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cpfcnpj")
    private BigInteger cfp_cnpj;
    private String nome;
    private String representante;

    @Column(name = "id_processo")
    private Long idProcesso;


    @Column(name = "tipo")
    private String tipo;




    @ManyToOne
    @JoinColumn(name = "finalidade")
    private Finalidade finalidade;





}
