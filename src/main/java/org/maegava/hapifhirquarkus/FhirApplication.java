//package org.maegava.hapifhirquarkus;
//
//import ca.uhn.fhir.rest.server.RestfulServer;
//import io.quarkus.runtime.Application;
//import org.maegava.hapifhirquarkus.hapi.config.HapiServerConfig;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//
//@ApplicationScoped
//public class FhirApplication extends Application {
//
//    @Inject
//    private HapiServerConfig hapiServerConfig;
//
////    public ServletRegistrationBean<RestfulServer> servletRegistrationBean() {
////        ServletRegistrationBean<RestfulServer> servletRegistrationBean = new ServletRegistrationBean<>();
////        servletRegistrationBean.setServlet(jpaRestfulServer);
////        servletRegistrationBean.addUrlMappings("/fhir/*");
////        servletRegistrationBean.setLoadOnStartup(1);
////
////        return servletRegistrationBean;
////    }
//
//}
