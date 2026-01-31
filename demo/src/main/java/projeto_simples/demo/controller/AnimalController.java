package projeto_simples.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto_simples.demo.model.Animal;
import projeto_simples.demo.service.AnimalService;

/**
 * Controller responsável pelo gerenciamento de animais.
 */
@RestController
@RequestMapping("animal")
public class AnimalController {

    @Autowired
    private AnimalService service;

    /**
     * Cria um novo animal.
     * 
     * @param animal o animal a ser salvo
     */
    @PostMapping
    public void postAnimal(@RequestBody Animal animal) {
        service.save(animal);
    }

    /**
     * Obtém todos os animais de um dono específico.
     * 
     * @param nomeDono o nome do dono
     * @return lista de animais do dono
     */
    @GetMapping("/{nomeDono}")
    public List<Animal> getAnimal(@PathVariable String nomeDono) {
        return service.getAllAnimal(nomeDono);
    }

    /**
     * Obtém todos os animais cadastrados.
     * 
     * @return lista com todos os animais
     */
    @GetMapping("/findAll")
    public List<Animal> findAll() {
        return service.findAll();
    }

    /**
     * Deleta um animal pelo ID.
     * 
     * @param id o ID do animal
     * @return o animal deletado
     */
    @DeleteMapping("/{id}")
    public Animal deteleAnimal(@PathVariable Long id) {
        return service.deleteById(id);
    }

    /**
     * Atualiza um animal existente.
     * 
     * @param animal o animal com dados atualizados
     * @return o animal atualizado
     */
    @PutMapping("/{id}")
    public Animal updateAnimal(@RequestBody Animal animal) {
        return service.updateAnimal(animal);
    }

}
