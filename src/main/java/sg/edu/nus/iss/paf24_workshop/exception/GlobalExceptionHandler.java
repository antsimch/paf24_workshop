package sg.edu.nus.iss.paf24_workshop.exception;

import java.util.Date;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(OrderIsNotCreatedException.class)
    public ModelAndView handleOrderIsNotCreatedException(OrderIsNotCreatedException ex, HttpServletRequest request) {
        
        ErrorMessage errMsg = new ErrorMessage();
        errMsg.setStatusCode(500);
        errMsg.setTimeStamp(new Date());
        errMsg.setMessage(ex.getMessage());
        errMsg.setDescription(request.getRequestURI());

        ModelAndView mav = new ModelAndView("error.html");
        return mav;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ModelAndView handleOrderIsNotCreatedException(OrderNotFoundException ex, HttpServletRequest request) {
        
        ErrorMessage errMsg = new ErrorMessage();
        errMsg.setStatusCode(500);
        errMsg.setTimeStamp(new Date());
        errMsg.setMessage(ex.getMessage());
        errMsg.setDescription(request.getRequestURI());

        ModelAndView mav = new ModelAndView("error.html");
        return mav;
    }
}
