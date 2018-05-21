package lt.vu.handler;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    public static final String ERROR_PAGE_NAME = "error";

    @ExceptionHandler(value = Exception.class)
    ModelAndView handleDefault(HttpServletRequest req, HttpServletResponse resp,
                               Exception exc) {
        System.out.println("Exception caught");

        ModelAndView mav = new ModelAndView();

        String displayMessage = "Something went wrong on our side :(";

        Annotation responseAnnot = AnnotationUtils.findAnnotation(exc.getClass(), ResponseStatus.class);
        if (responseAnnot != null) {
            int status = ((ResponseStatus) responseAnnot).value().value();
            if (status < 500)
                displayMessage = "Something wrong with your request :(";
            resp.setStatus(status);
        }
        else
            resp.setStatus(500);

        mav.addObject("exception", exc);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("displayMessage", displayMessage);
        mav.setViewName(ERROR_PAGE_NAME);
        return mav;
    }
}
