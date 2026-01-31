package projeto_simples.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto_simples.demo.model.Animal;


public interface AnimalRepository extends JpaRepository<Animal, Long>{
    List<Animal> findByNomeDonoContainingIgnoreCase(String nome);


}
