package lt.vu.handler;

import lombok.extern.slf4j.Slf4j;
import lt.vu.exceptions.PaymentException;
import org.springframework.webflow.execution.FlowExecutionException;
import org.springframework.webflow.execution.FlowExecutionListener;
import org.springframework.webflow.execution.RequestContext;

@Slf4j
public class LoggingFlowExceptionListener implements FlowExecutionListener {

  @Override
  public void exceptionThrown(RequestContext context, FlowExecutionException exception) {
      Throwable cause = exception.getCause();
      if (cause instanceof PaymentException) {
          log.info("Payment exception: " + cause.getMessage());
      }
      else {
          log.error("Webflow " + context.getActiveFlow().getId() +
                  " threw exception in " + context.getCurrentState().getId(), exception);
      }
    }
}
