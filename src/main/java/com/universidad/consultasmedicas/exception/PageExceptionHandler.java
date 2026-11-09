package com.universidad.consultasmedicas.exception;
import org.springframework.http.HttpStatus; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*; import org.springframework.web.servlet.ModelAndView; import org.springframework.web.server.ResponseStatusException;
@ControllerAdvice(assignableTypes={com.universidad.consultasmedicas.controller.PageController.class})
public class PageExceptionHandler {
 @ExceptionHandler(Exception.class) public ModelAndView pageError(Exception error,Model model){
  HttpStatus status=HttpStatus.BAD_REQUEST;
  if(error instanceof ResponseStatusException r){int code=r.getStatusCode().value();status=HttpStatus.valueOf(code);model.addAttribute("message",r.getReason());}
  else model.addAttribute("message",error.getMessage());
  String view=status==HttpStatus.NOT_FOUND?"404":status==HttpStatus.FORBIDDEN?"403":"error";
  ModelAndView result=new ModelAndView(view,model.asMap());result.setStatus(status);return result;
 }
}
