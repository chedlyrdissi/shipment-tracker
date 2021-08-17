package shipmenttracker.data.advice;

import shipmenttracker.data.exception.ProviderAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProviderAlreadyExistsAdvice {

  @ResponseBody
  @ExceptionHandler(ProviderAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.IM_USED)
  String employeeNotFoundHandler(ProviderAlreadyExistsException ex) {
    return ex.getMessage();
  }
}
