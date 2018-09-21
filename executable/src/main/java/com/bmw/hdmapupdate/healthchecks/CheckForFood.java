package com.bmw.hdmapupdate.healthchecks;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.faces.bean.ApplicationScoped;


@Health
@ApplicationScoped
public class CheckForFood implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.
                named("food-check").
                withData("apples", 2).
                withData("Oragenes", 1).
                up().build();
    }
}
