package lt.vu.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    public static final String ERROR_PAGE_NAME = "error";

    @ExceptionHandler(value = Exception.class)
    ModelAndView handleDefault(HttpServletRequest req, Exception exc) {
        System.out.println("Exception caught");

        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", exc);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("displayMessage", "Generic error message");
        mav.setViewName(ERROR_PAGE_NAME);
        return mav;
    }
}
