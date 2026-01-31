package projeto_simples.demo.handler;

import java.lang.reflect.UndeclaredThrowableException;


import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


   private final MessageSource messageSource;


   public GlobalExceptionHandler(MessageSource messageSource) {
       this.messageSource = messageSource;
   }


   private HttpHeaders headers() {
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       return headers;
   }


   private ResponseError responseError(String message, HttpStatus statusCode) {
       ResponseError responseError = new ResponseError();
       responseError.setError(message);
       responseError.setStatusCode(statusCode.value());
       return responseError;
   }


   @ExceptionHandler(RuntimeException.class)
   protected ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {


       if (e instanceof UndeclaredThrowableException exception
               && exception.getUndeclaredThrowable() instanceof BusinessException be) {
           return handleBusinessException(be, request);
       }


       String message;
       try {
           message = messageSource.getMessage(
                   "error.server",
                   new Object[]{e.getMessage()},
                   request.getLocale()
           );
       } catch (Exception ex) {
           message = "Erro interno do servidor";
       }


       ResponseError error = responseError(message, HttpStatus.INTERNAL_SERVER_ERROR);


       return handleExceptionInternal(
               e,
               error,
               headers(),
               HttpStatus.INTERNAL_SERVER_ERROR,
               request
       );
   }


   @ExceptionHandler(BusinessException.class)
   protected ResponseEntity<Object> handleBusinessException(
           BusinessException e, WebRequest request) {


       ResponseError error = responseError(e.getMessage(), HttpStatus.CONFLICT);


       return handleExceptionInternal(
               e,
               error,
               headers(),
               HttpStatus.CONFLICT,
               request
       );
   }
}


