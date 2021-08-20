package org.maegava.hapifhirquarkus.patient.service;

import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PatientService {

    public Patient getPatient(final IdType theId) {
        return patients
                .stream()
                .filter(p -> p.getId().equals(theId.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    /** HELPERS ***/
    private static final List<Patient> patients = new ArrayList<>();

    static {
        new Patient();
        Patient p1 = new Patient();
        p1.setId("8a155c3d-280e-4b69-a570-7ac4029c0fd1");
        p1.addIdentifier();
        p1.getIdentifier().get(0).setUse(Identifier.IdentifierUse.OFFICIAL);
        p1.getIdentifier().get(0).setSystem("urn:hapitest:mrns");
        p1.getIdentifier().get(0).setValue("00001");
        p1.addName();
        p1.getName().get(0).setText("John Wick");
        p1.getName().get(0).addGiven("PatientOne");

        Patient p2 = new Patient();
        p2.setId("e15517a9-46d7-4876-ad56-5fcff3b9e218");
        p2.addIdentifier();
        p2.getIdentifier().get(0).setUse(Identifier.IdentifierUse.OFFICIAL);
        p2.getIdentifier().get(0).setSystem("urn:hapitest:mrns");
        p2.getIdentifier().get(0).setValue("00002");
        p2.addName();
        p2.getName().get(0).setText("John Ramone");
        p2.getName().get(0).addGiven("PatientTwo");

        Patient p3 = new Patient();
        p3.setId(UUID.randomUUID().toString());
        p3.addIdentifier();
        p3.getIdentifier().get(0).setUse(Identifier.IdentifierUse.OFFICIAL);
        p3.getIdentifier().get(0).setSystem("urn:hapitest:mrns");
        p3.getIdentifier().get(0).setValue("00002");
        p3.addName();
        p3.getName().get(0).setText("Johnny Cage");
        p3.getName().get(0).addGiven("PatientThree");

        patients.add(p1);
        patients.add(p2);
        patients.add(p3);
    }

}
