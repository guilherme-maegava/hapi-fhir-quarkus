package org.maegava.hapifhirquarkus.patient.controller;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;
import org.maegava.hapifhirquarkus.patient.service.PatientService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PatientController implements IResourceProvider {

    @Inject
    private PatientService patientService;

    @Override
    public Class<Patient> getResourceType() {
        return Patient.class;
    }

    @Read
    public Patient getResourceById(RequestDetails theRequestDetails, @IdParam IdType theId) {
        return patientService.getPatient(theId);
    }

    @Search
    public List<Patient> search(RequestDetails theRequestDetails,
            @OptionalParam(name = Patient.SP_FAMILY) StringParam theFamilyName) {
        final String partner = theRequestDetails.getTenantId();
        return patientService.getPatients();
    }

}
