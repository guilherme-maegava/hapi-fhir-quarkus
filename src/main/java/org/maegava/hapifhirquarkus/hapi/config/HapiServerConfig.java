package org.maegava.hapifhirquarkus.hapi.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.tenant.UrlBaseTenantIdentificationStrategy;
import org.maegava.hapifhirquarkus.hapi.interceptor.RequestTenantPartitionInterceptor;
import org.maegava.hapifhirquarkus.patient.controller.PatientController;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

@Singleton
@WebServlet(urlPatterns= {"/fhir/*"}, displayName="FHIR Server")
public class HapiServerConfig extends RestfulServer {

    private static final long serialVersionUID = 2467468320139726878L;

    @Inject
    RequestTenantPartitionInterceptor requestTenantPartitionInterceptor;

    @Override
    public void initialize() {
        setFhirContext(FhirContext.forR4());
        setTenantIdentificationStrategy(new UrlBaseTenantIdentificationStrategy());

        List<IResourceProvider> resourceProviders = new ArrayList<>();
        resourceProviders.add(new PatientController());

        setResourceProviders(resourceProviders);

        registerInterceptor(requestTenantPartitionInterceptor);
        registerInterceptor(new LoggingInterceptor());
    }
}