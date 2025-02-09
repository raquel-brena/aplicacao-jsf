package com.rb.esig.infra.exceptionHandler;

import com.rb.esig.shared.exceptions.BadRequestException;
import com.rb.esig.shared.exceptions.ForbbidenException;
import com.rb.esig.shared.exceptions.NotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import java.util.Iterator;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
    private final ExceptionHandler wrapped;

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() {
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = event.getContext();
            Throwable exception = context.getException();

            try {
                Throwable rootCause = ExceptionUtils.getRootCause(exception);

                FacesContext facesContext = FacesContext.getCurrentInstance();
                if (facesContext != null) {
                    if (rootCause instanceof BadRequestException || rootCause instanceof NotFoundException || rootCause instanceof ForbbidenException) {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", rootCause.getMessage()));
                    } else {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro inesperado. Tente novamente."));
                    }
                    facesContext.getExternalContext().getFlash().setKeepMessages(true);
                }

            } finally {
                events.remove();
            }
        }
        getWrapped().handle();
    }
}
