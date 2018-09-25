package com.bmw.hdmapupdate.faulttolerance;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FaultyService {

   @Retry(maxRetries = 1)
   @Fallback(DataFallbackHandler.class)
    public String iAmGoingToFail(boolean fail){
        if(fail){
            throw new IllegalStateException("bad bad service");
        }
        return "All good";
    }
}
