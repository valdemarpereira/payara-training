package com.bmw.hdmapupdate.controllers;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("timedExample")
public class TimedExampleMetricController {

    public TimedExampleMetricController(){}

    @GET
    @Path("/threeSecondsDelay")
    @Timed(name = "longRunningMethod",
            description = "Metrics to monitor the times of longRunningMethod method.",
            unit = MetricUnits.MINUTES,
            absolute = true)
    public String longRunningMethod() {

        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "done";
    }
}
