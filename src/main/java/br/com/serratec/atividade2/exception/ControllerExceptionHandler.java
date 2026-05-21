package br.com.serratec.atividade2.exception;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                List<String> erros= new ArrayList<>();
                for(FieldError erro : ex.getBindingResult().getFieldErrors()) {
                    erros.add(erro.getField()+ " : "+ erro.getDefaultMessage());
                }
                ErroResposta er= new ErroResposta(status.value(), "Existem erros na requisição.", LocalDate.now(), erros);
        return super.handleExceptionInternal(ex, er, headers, status, request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                ErroResposta er= new ErroResposta(status.value(), "Existem erros na requisição. "+ ex.getMessage(), LocalDate.now());
        return super.handleExceptionInternal(ex, er, headers, status, request);
    }

    /*Não conseguimos fazer funcionar. O Postman simplesmente se recusa a implementar isso.
      Assim sendo, na hora que for acessar um recurso inexistente, como "/lancamentos/2",
      O Postman só mostra o erro 404 e nada mais.*/
    @Override
    protected @Nullable ResponseEntity<Object> handleNoResourceFoundException(
            NoResourceFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ErroResposta er = new ErroResposta(
                status.value(),
                "Recurso não encontrado: " + ex.getResourcePath(),
                LocalDate.now());

        return super.handleExceptionInternal(ex, er, headers, status, request);
    }

}
