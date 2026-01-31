package projeto_simples.demo.handler;

public class NotFoundException extends BusinessException{
    public NotFoundException() {
        super("Recurso não encontrado");
    }
    
}
