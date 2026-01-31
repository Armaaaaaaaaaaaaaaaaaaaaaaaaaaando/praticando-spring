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

import projeto_simples.demo.model.Servico;
import projeto_simples.demo.service.ServicoService;

/**
 * Controlador REST para gerenciar operações de Serviço.
 */
@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService service;

    // ===== CREATE =====

    /**
     * Cria um novo serviço.
     *
     * @param servico objeto do serviço a ser salvo
     * @return serviço salvo
     */
    @PostMapping
    public Servico saveServico(@RequestBody Servico servico) {
        return service.save(servico);
    }

    // ===== READ =====

    /**
     * Retorna todos os serviços.
     *
     * @return lista de todos os serviços
     */
    @GetMapping
    public List<Servico> findAll() {
        return service.findAll();
    }

    /**
     * Busca um serviço por ID.
     *
     * @param Id identificador do serviço
     * @return serviço encontrado
     */
    @GetMapping("/{Id}")
    public Servico findById(@PathVariable Long Id) {
        return service.findById(Id);
    }

    /**
     * Busca serviços associados a um animal específico.
     *
     * @param id identificador do animal
     * @return lista de serviços do animal
     */
    @GetMapping("/animal/{id}")
    public List<Servico> findServicoByAnimal(@PathVariable Long id) {
        return service.findServicoByAnimal(id);
    }

    // ===== UPDATE =====

    /**
     * Atualiza um serviço existente.
     *
     * @param servico objeto do serviço com dados atualizados
     * @return serviço atualizado
     */
    @PutMapping("/{id}")
    public Servico updateServico(@RequestBody Servico servico) {
        return service.updateServico(servico);
    }

    // ===== DELETE =====

    /**
     * Deleta um serviço por ID.
     *
     * @param id identificador do serviço
     * @return serviço deletado
     */
    @DeleteMapping("/{id}")
    public Servico deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
