package com.bmw.hdmapupdate.healthchecks;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.faces.bean.ApplicationScoped;


@Health
@ApplicationScoped
public class SuccessfulCheckDiskSpace implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("successful-check").up().build();
    }
}
