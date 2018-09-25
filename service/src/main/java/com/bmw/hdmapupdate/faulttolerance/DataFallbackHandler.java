package com.bmw.hdmapupdate.faulttolerance;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import javax.faces.bean.ApplicationScoped;

@ApplicationScoped
public class DataFallbackHandler implements FallbackHandler<String> {
    @Override
    public String handle(ExecutionContext executionContext) {
        return "Something happened. I am the fallback service";
    }
}
