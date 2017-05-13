package Exceptions;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by eku on 10.05.17.
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        LOGGER.error(ex);

        ModelAndView model = new ModelAndView("errorAll");
        model.addObject("exception", ex);

        return model;
    }

}

