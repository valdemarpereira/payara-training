package com.bmw.hdmapupdate.controllers;

import com.bmw.hdmapupdate.helloworldservice.ClockService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("hello")
public class HelloWorldController {

    private final ClockService clockService;

    @Inject
    public HelloWorldController(ClockService clockService){
        this.clockService = clockService;
    }

    @GET
    public String helloJohnDoe() {
        return String.format("Hello John Doe! [%s]", clockService.ubberUsefulClockService());
    }

    @GET
    @Path("{username}")
    public String hello(@PathParam("username") String username) {
        return String.format("Hello %s! [%s]", username, clockService.ubberUsefulClockService());
    }
}
