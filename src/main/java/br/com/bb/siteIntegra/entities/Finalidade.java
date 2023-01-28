package br.com.bb.siteIntegra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_finalidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Finalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    @OneToMany(mappedBy = "finalidade")
    private List<DadosResgate> dadosResgates = new ArrayList<>();



}
