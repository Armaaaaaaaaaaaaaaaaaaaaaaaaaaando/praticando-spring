package projeto_simples.demo.handler;

public class NotNullException extends BusinessException{
    public NotNullException() {
        super("Campos não podem ser nulos");
    }
    
}
