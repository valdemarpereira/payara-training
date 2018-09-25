package com.bmw.hdmapupdate.controllers.faulttolerance;


import com.bmw.hdmapupdate.faulttolerance.FaultyService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("faulttorelance")
public class FaultToleranceExampleController {

    private FaultyService faultyService;

    @Inject
    public FaultToleranceExampleController(FaultyService faultyService) {
        this.faultyService = faultyService;
    }

    @GET
    @Path("{fail}")
    public String iwillfail(@PathParam("fail") Boolean fail){
        return faultyService.iAmGoingToFail(fail);
    }
}
