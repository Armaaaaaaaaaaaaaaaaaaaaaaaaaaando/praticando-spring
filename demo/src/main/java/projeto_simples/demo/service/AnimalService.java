package projeto_simples.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import projeto_simples.demo.handler.BusinessException;
import projeto_simples.demo.handler.NotFoundException;
import projeto_simples.demo.model.Animal;
import projeto_simples.demo.repository.AnimalRepository;

/**
 * Service responsável pelo gerenciamento de operações relacionadas a animais.
 * Realiza validações de negócio e coordena as operações com o repositório de dados.
 */
@Service
public class AnimalService {
    
    private AnimalRepository animalRepository;
    
    /**
     * Construtor que injeta a dependência do repositório de animais.
     *
     * @param animalRepository o repositório de dados para operações de Animal
     */
    public AnimalService(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    /**
     * Salva um novo animal no banco de dados após validar os campos obrigatórios.
     *
     * @param animal o objeto Animal a ser salvo contendo nome, nome do dono e raça
     * @return o Animal salvo com seu ID gerado
     * @throws BusinessException se nome, nome_dono ou raça forem nulos
     */
    public Animal save(Animal animal){
        return animalRepository.save(animal);
    }

    /**
     * Recupera todos os animais associados a um proprietário específico.
     *
     * @param nomeDono o nome do dono dos animais a serem buscados
     * @return uma lista de animais pertencentes ao dono informado
     * @throws BusinessException se nomeDono for nulo, vazio ou não encontrado no registro
     */
    public List<Animal> getAllAnimal(String nomeDono) {
        return animalRepository.findByNomeDonoContainingIgnoreCase(nomeDono);
    }

    /**
     * Recupera todos os animais cadastrados no banco de dados.
     *
     * @return uma lista contendo todos os animais registrados
     */
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    /**
     * Remove um animal do banco de dados pelo seu identificador.
     *
     * @param id o identificador único do animal a ser removido
     * @return o objeto Animal removido
     * @throws BusinessException se o id for nulo ou o animal não for encontrado
     */
    public Animal deleteById(Long id) {
        animalRepository.deleteById(id);
        return null;
    }

    /**
     * Atualiza os dados de um animal existente no banco de dados.
     *
     * @param animal o objeto Animal contendo os novos dados e o ID do animal a ser atualizado
     * @return o Animal atualizado
     * @throws BusinessException se animal for nulo ou se o animal com o ID informado não for encontrado
     */
    public Animal updateAnimal(Animal animal){
        Animal atual = animalRepository.findById(animal.getId()).orElseThrow(() -> new NotFoundException());
        atual.setRaca(animal.getRaca());
        atual.setNome(animal.getNome());
        atual.setNome_dono(animal.getNome_dono());
        return animalRepository.save(atual);
    }
}
