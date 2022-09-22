package br.com.calculadora.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.calculadora.exception.DivisaoPorZeroException;
import br.com.calculadora.exception.OperandoNaoInformadoException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String MSG_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
      + "Tente novamente e se o problema persistir, entre em contato "
      + "com o administrador do sistema.";

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(OperandoNaoInformadoException.class)
  public ResponseEntity<?> handleOperandoNaoInformado(OperandoNaoInformadoException ex, WebRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
    String detail = ex.getMessage();

    Problem problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_GENERICA_USUARIO_FINAL).build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(DivisaoPorZeroException.class)
  public ResponseEntity<?> handleDivisaoPorZero(DivisaoPorZeroException ex, WebRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
    String detail = ex.getMessage();

    Problem problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_GENERICA_USUARIO_FINAL).build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";

    BindingResult bindingResult = ex.getBindingResult();

    List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
        .map(fieldError -> {

          String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

          return Problem.Field.builder()
              .name(fieldError.getField())
              .userMessage(message)
              .build();
        }).collect(Collectors.toList());

    Problem problem = createProblemBuilder(status, problemType, detail)
        .userMessage(detail)
        .fields(problemFields)
        .build();

    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    if (body == null) {
      // cairá aqui caso seja alguma exception do spring tratada por
      // ResponseEntityExceptionHandler (classe extendida)
      body = Problem.builder()
          .title(status.getReasonPhrase())
          .status(status.value())
          .timestamp(LocalDateTime.now())
          .userMessage(MSG_GENERICA_USUARIO_FINAL)
          .build();
    } else if (body instanceof String) {
      body = Problem.builder()
          .title((String) body)
          .status(status.value())
          .timestamp(LocalDateTime.now())
          .userMessage(MSG_GENERICA_USUARIO_FINAL)
          .build();
    }
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }

  private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
    return Problem.builder()
        .status(status.value())
        .type(problemType.getUri())
        .title(problemType.getTitle())
        .detail(detail)
        .timestamp(LocalDateTime.now());
  }

}
