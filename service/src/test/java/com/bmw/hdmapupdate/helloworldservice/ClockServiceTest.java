package com.bmw.hdmapupdate.helloworldservice;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ClockServiceTest {

    private ClockService clockService;

    @Before
    public void setup() {
        clockService =  new ClockService();
    }

    @Test
    public void ubberUsefulClockService() {
        assertThat(clockService.ubberUsefulClockService(), equalTo("It's Wine O'Clock"));
    }
}