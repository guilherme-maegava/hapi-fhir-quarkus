package org.maegava.hapifhirquarkus.patient.controller;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PatientController implements IResourceProvider {

    @Override
    public Class<Patient> getResourceType() {
        return Patient.class;
    }

    @Read()
    public Patient getResourceById(@IdParam IdType theId) {
        Patient retVal = new Patient();

        // ...populate...
        retVal.addIdentifier().setSystem("urn:mrns").setValue("12345");
        retVal.addName().setFamily("Smith").addGiven("Tester").addGiven("Q");
        // ...etc...

        // if you know the version ID of the resource, you should set it and HAPI will
        // include it in a Content-Location header
        retVal.setId(new IdType("Patient", "123", "2"));

        return retVal;
    }

    @Search
    public List<Patient> search(RequestDetails theRequestDetails,
            @OptionalParam(name = Patient.SP_FAMILY) StringParam theFamilyName) {
        final String partner = theRequestDetails.getTenantId();
        return patients;
    }

    /** HELPERS ***/

    private static List<Patient> patients = new ArrayList<>();

    static {
        new Patient();
        Patient p1 = new Patient();
        p1.setId(UUID.randomUUID().toString());
        p1.addIdentifier();
        p1.getIdentifier().get(0).setUse(Identifier.IdentifierUse.OFFICIAL);
        p1.getIdentifier().get(0).setSystem("urn:hapitest:mrns");
        p1.getIdentifier().get(0).setValue("00001");
        p1.addName();
        p1.getName().get(0).setText("John Wick");
        p1.getName().get(0).addGiven("PatientOne");

        Patient p2 = new Patient();
        p2.setId(UUID.randomUUID().toString());
        p2.addIdentifier();
        p2.getIdentifier().get(0).setUse(Identifier.IdentifierUse.OFFICIAL);
        p2.getIdentifier().get(0).setSystem("urn:hapitest:mrns");
        p2.getIdentifier().get(0).setValue("00002");
        p2.addName();
        p2.getName().get(0).setText("John Ramone");
        p2.getName().get(0).addGiven("PatientOne");

        patients.add(p1);
        patients.add(p2);
    }
}
