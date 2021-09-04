package dev.igorac.algamoney.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, Locale.getDefault());
        String mensagemDev = ex.getCause().toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDev));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST,
            request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<Erro> erros = criarListaErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDev));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEmptyResultDataAccessException() {
    }

    @ExceptionHandler({ PessoaInexistenteOuInativaException.class })
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDev = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDev));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({ CategoriaInexistenteException.class })
    public ResponseEntity<Object> handleCategoriaInexistenteException(CategoriaInexistenteException ex) {
        String mensagemUsuario = messageSource.getMessage("categoria.inexistente", null, LocaleContextHolder.getLocale());
        String mensagemDev = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDev));
        return ResponseEntity.badRequest().body(erros);
    }

    private List<Erro> criarListaErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        bindingResult.getFieldErrors()
            .forEach(fieldError -> {
                String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                String mensagemDev = fieldError.toString();
                erros.add(new Erro(mensagemUsuario, mensagemDev));
            });

        return erros;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Erro {
        private String mensagemUsuario;
        private String mensagemDev;
    }
}
