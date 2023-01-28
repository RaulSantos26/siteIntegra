package br.com.bb.siteIntegra.repositories;



import br.com.bb.siteIntegra.entities.Pesquisa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PesquisaRepository  extends JpaRepository<Pesquisa, Long> {

    Pesquisa findByjudicial(Integer judicial);

    Pesquisa findBygsv(Integer gsv);
}

