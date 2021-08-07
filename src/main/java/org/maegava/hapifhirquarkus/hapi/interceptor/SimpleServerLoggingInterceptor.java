package org.maegava.hapifhirquarkus.hapi.interceptor;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimpleServerLoggingInterceptor {

    private final Logger ourLog = LoggerFactory.getLogger(SimpleServerLoggingInterceptor.class);

    @Hook(Pointcut.SERVER_INCOMING_REQUEST_PRE_HANDLED)
    public void logRequests(RequestDetails theRequest) {
        ourLog.info("Request of type {} with request ID: {}");
    }

}
