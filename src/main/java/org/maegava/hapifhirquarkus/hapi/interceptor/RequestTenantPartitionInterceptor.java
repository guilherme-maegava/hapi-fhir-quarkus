package org.maegava.hapifhirquarkus.hapi.interceptor;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.interceptor.model.RequestPartitionId;
import ca.uhn.fhir.rest.server.servlet.ServletRequestDetails;

import javax.inject.Singleton;

@Singleton
public class RequestTenantPartitionInterceptor {

    @Hook(Pointcut.STORAGE_PARTITION_IDENTIFY_CREATE)
    public RequestPartitionId PartitionIdentifyCreate(ServletRequestDetails theRequestDetails) {
        return extractPartitionIdFromRequest(theRequestDetails);
    }

    @Hook(Pointcut.STORAGE_PARTITION_IDENTIFY_READ)
    public RequestPartitionId PartitionIdentifyRead(ServletRequestDetails theRequestDetails) {
        return extractPartitionIdFromRequest(theRequestDetails);
    }

    private RequestPartitionId extractPartitionIdFromRequest(ServletRequestDetails theRequestDetails) {
        // We will use the tenant ID that came from the request as the partition name
        String tenantId = theRequestDetails.getTenantId();
        return RequestPartitionId.fromPartitionName(tenantId);
    }

}