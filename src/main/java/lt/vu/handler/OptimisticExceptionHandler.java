package lt.vu.handler;


import jdk.nashorn.internal.ir.Optimistic;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.OptimisticLockException;

@ControllerAdvice
public class OptimisticExceptionHandler {
	@ExceptionHandler(OptimisticLockException.class)
	public String optimisticLockException(final OptimisticLockException e) {
		return "redirect:/lock";
	}

}
