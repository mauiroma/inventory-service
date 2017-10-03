package com.redhat.coolstore.inventory.rest;

import org.wildfly.swarm.health.Health;
import org.wildfly.swarm.health.HealthStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Date;

@Path("/")
public class HealthCheckResource {
    @GET
    @Health
    @Path("/status")
    public HealthStatus check() {
        return HealthStatus.named("server-state").up().withAttribute("date", new Date().toString());
    }
}
