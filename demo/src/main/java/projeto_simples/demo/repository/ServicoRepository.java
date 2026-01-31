package projeto_simples.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto_simples.demo.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{
    List<Servico> findByAnimalId(Long animalId);
}
