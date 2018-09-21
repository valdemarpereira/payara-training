package com.bmw.hdmapupdate.controllers;

import com.bmw.hdmapupdate.helloworldservice.ClockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HelloWorldControllerTest {

    @Mock
    private ClockService clockServiceMock;

    @InjectMocks
    private HelloWorldController helloWorldController;

    @Before
    public void setup(){
        when(clockServiceMock.ubberUsefulClockService()).thenReturn("Bla");
    }

    @Test
    public void xxx(){
        String response = helloWorldController.helloJohnDoe();
        assertThat(response, equalTo("Hello John Doe! [Bla]"));
    }
}