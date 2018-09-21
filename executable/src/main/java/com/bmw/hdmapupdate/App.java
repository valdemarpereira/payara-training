package com.bmw.hdmapupdate;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
public class App extends Application
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
