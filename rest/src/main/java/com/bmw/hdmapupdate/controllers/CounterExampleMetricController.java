package com.bmw.hdmapupdate.controllers;

import com.bmw.hdmapupdate.helloworldservice.ClockService;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("countme")
public class CounterExampleMetricController {

   // @Metric
    private final ClockService clockService;

    @Inject
    public CounterExampleMetricController(ClockService clockService){
        this.clockService = clockService;
    }

    //@Metered
    @Counted(unit = MetricUnits.NONE,
            name = "countMeACounter",
            absolute = true,
            monotonic = true,
            displayName = "Method A Counter",
            description = "Metrics to show how many times countMeA method was called.",
            tags = {"checkout=items"})
    @GET
    @Path("/a")
    public String countMeA() {
        return String.format("Hi, I'm methodA");
    }

    //@Metered
    @Counted(unit = MetricUnits.NONE,
            name = "countMeBCounter",
            absolute = true,
            monotonic = true,
            displayName = "Method B Counter",
            description = "Metrics to show how many times countMeB method was called.",
            tags = {"checkout=items"})
    @GET
    @Path("/b")
    public String countMeB() {
        return String.format("Hi, I'm methodB");
    }
}
