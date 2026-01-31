package projeto_simples.demo.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import projeto_simples.demo.handler.BusinessException;
import projeto_simples.demo.handler.NotFoundException;
import projeto_simples.demo.handler.NotNullException;
import projeto_simples.demo.model.Animal;
import projeto_simples.demo.model.Servico;
import projeto_simples.demo.repository.AnimalRepository;
import projeto_simples.demo.repository.ServicoRepository;


@Service
public class ServicoService {

    private ServicoRepository repositoryServico;
    private AnimalRepository animalRepository;

    public ServicoService(AnimalRepository animalRepository, ServicoRepository repositoryServico){
        this.animalRepository = animalRepository;
        this.repositoryServico = repositoryServico;
    }

    /**
     * Salva um novo serviço validando os dados e o animal associado.
     * @param servico o serviço a ser salvo
     * @return o serviço salvo
     * @throws BusinessException se houver campos nulos ou animal não encontrado
     */
    public Servico save(Servico servico) {
        if(servico == null || servico.getAnimal() == null || servico.getData() == null || servico.getTipoConsulta() == null){
            throw new NotNullException();
        }

        Long animalId = servico.getAnimal().getId();
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new BusinessException("Animal não encontrado"));

        servico.setAnimal(animal);

        return repositoryServico.save(servico);

    }

    /**
     * Recupera todos os serviços cadastrados.
     * @return lista de todos os serviços
     */
    public List<Servico> findAll() {
        return repositoryServico.findAll();
    }

    /**
     * Busca um serviço pelo ID.
     * @param id o ID do serviço
     * @return o serviço encontrado
     * @throws BusinessException se o ID for nulo ou serviço não encontrado
     */
    public Servico findById(Long id) {
       if(id == null){
            throw new NotNullException();
       }else if(!repositoryServico.existsById(id)){
        throw new NotFoundException();
       }
       return repositoryServico.findById(id).orElseThrow(() -> new NotFoundException());

    }

    /**
     * Deleta um serviço pelo ID.
     * @param id o ID do serviço a deletar
     * @return o serviço deletado
     * @throws BusinessException se o ID for nulo ou serviço não encontrado
     */
    public Servico deleteById(Long id) {
        if(id == null){
            throw new NotNullException();
        }

        Servico servicoDeletado = repositoryServico.findById(id).orElseThrow(() -> new NotFoundException());

        repositoryServico.delete(servicoDeletado);
        return servicoDeletado;
    }

    /**
     * Atualiza um serviço existente.
     * @param servico o serviço com dados atualizados
     * @return o serviço atualizado
     * @throws BusinessException se houver campos nulos ou serviço não encontrado
     */
    public Servico updateServico(Servico servico) {
        if(servico == null || servico.getData() == null || servico.getTipoConsulta() == null){
            throw new NotNullException();
        }

        Servico atual = repositoryServico.findById(servico.getId()).orElseThrow(() -> new NotFoundException());

        atual.setData(servico.getData());
        atual.setTipoConsulta(servico.getTipoConsulta());
        atual.setDescricao(servico.getDescricao());

        repositoryServico.save(atual);

        return atual;
        
    }

    /**
     * Busca todos os serviços associados a um animal específico.
     *
     * @param id o identificador único do animal para o qual se deseja encontrar os serviços.
     *           Não pode ser nulo.
     * @return uma lista de {@link Servico} associados ao animal com o ID fornecido.
     *         Retorna uma lista vazia se nenhum serviço for encontrado para o animal.
     * @throws BusinessException se o parâmetro id for nulo.
     */
    public List<Servico> findServicoByAnimal(Long id) {
        if(id == null){
            throw new NotNullException();
        }
        else if(repositoryServico.findByAnimalId(id).isEmpty()){
            throw new NotFoundException();
        }
        return repositoryServico.findByAnimalId(id);
    }



    
}
