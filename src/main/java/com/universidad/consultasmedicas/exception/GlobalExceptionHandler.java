package com.universidad.consultasmedicas.exception;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException; import java.util.*;
@RestControllerAdvice(assignableTypes={com.universidad.consultasmedicas.controller.ApiController.class})
public class GlobalExceptionHandler {
 @ExceptionHandler(ResponseStatusException.class) public ResponseEntity<Map<String,String>> status(ResponseStatusException e){return ResponseEntity.status(e.getStatusCode()).body(Map.of("error",Objects.toString(e.getReason(),"Error")));}
 @ExceptionHandler({org.springframework.web.bind.MethodArgumentNotValidException.class,org.springframework.http.converter.HttpMessageNotReadableException.class}) public ResponseEntity<Map<String,String>> invalid(Exception e){return ResponseEntity.badRequest().body(Map.of("error","Solicitud inválida: revise los campos y sus formatos"));}
 @ExceptionHandler(Exception.class) public ResponseEntity<Map<String,String>> other(Exception e){return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error","Error interno al procesar la solicitud"));}
}
