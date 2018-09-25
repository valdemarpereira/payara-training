# Building and running this project


## Using the Uber approach 

> NOTE: This pom is prepared to build a Uber Jar (Fat jar). Unfortunately it does not run under windows.
Run this example under a Unix operation system

**Build:**
```
mvn clean install
```
**Execute:**
```
java -jar executable/target/payara-hello-world-microbundle.jar
```

## Building a Uber Jar manually 
> It runs under MS Windows

**Build:**
```
mvn clean install
```

**Create an Uber Jar:**
```
java -jar PATH_TO_PAYARA_MICRO --deploy executable\target\payara-hello-world.war --outputUberJar payara-hello-world-uber.jar
```
**Execute the Uber Jar**
```
java -jar payara-hello-world-uber.jar
```

>Any additional command line options you specify when creating an Uber JAR are recorded, so the Payara Micro instance is configured later when executing the packaged JAR:

```
java -jar payara-micro.jar --deploy test.war --port 9080 --lite --clusterName test-cluster --clusterPassword test-password --outputUberJar test2.jar
```
## Manually run the service

**Build:**
```
mvn clean install
```

**Execute:**
```
java -jar PATH_TO_PAYARA_MICRO --deploy executable\target\payara-hello-world.war
```

## Building and using a docker image

**TBC**



# A Multi-Module Maven Approach

So, quite tricky to get this to run. In order to CDI to load all the beans from the other modules, we need to create the META-INF folder under resources/META-INF and put a bean.xml file in there.
> Notice the difference  between the META-INF and the webapp/WEB-INF folder. My understating is that: 
When generating a WAR file, CDI will look for the bean.xml under webapp/WEB-INF folder.
When facing with a JAR file, CDI will look for the bean.xml under resources/META-INF folder.

# MicroProfile
https://microprofile.io/
https://docs.payara.fish/documentation/microprofile/
https://blog.payara.fish/building-your-next-microservice-with-eclipse-microprofile

## Health Check
https://docs.payara.fish/documentation/microprofile/healthcheck.html
https://microprofile.io/project/eclipse/microprofile-health

### REST endpoint
> /health


Can be tailored to add additional health "checkpoints":

- CheckForFood
- DownCheckCoffeeAvailable
- SuccessfulCheckDiskSpace

Invoking the this project example, we have:

```
curl http://10.0.75.1:8080/health
```

```json
{  
   "outcome":"DOWN",
   "checks":[  
      {  
         "name":"successful-check",
         "state":"UP",
         "data":{  

         }
      },
      {  
         "name":"food-check",
         "state":"UP",
         "data":{  
            "Oragenes":"1",
            "apples":"2"
         }
      },
      {  
         "name":"fail-check-coffee",
         "state":"DOWN",
         "data":{  

         }
      }
   ]
}
```

## Metrics

The MicroProfile Metrics is a specification that provides a standard way for application servers to expose metrics and, also, an API for developers to build their own application metrics. 

A few useful links:

https://docs.payara.fish/documentation/microprofile/metrics.html
https://microprofile.io/project/eclipse/microprofile-metrics
https://blog.payara.fish/microprofile-metrics-in-payara-micro
https://www.tomitribe.com/blog/getting-started-with-microprofile-metrics/
https://github.com/eclipse/microprofile-metrics/releases/download/1.1.1/metrics_spec-1.1.1.pdf


Servers that implement MicroProfile Metrics have their metrics located at a resource called /metrics. 
Under this resource there are 3 different areas called scopes. 
The available scopes are /metrics/base, /metrics/vendor, and  /metrics/application.

Example:

```
curl http://localhost:8080/metrics
```

```
# TYPE vendor:system_cpu_load gauge
# HELP vendor:system_cpu_load Display the "recent cpu usage" for the whole system. This value is a double in the [0.0,1.0] interval. A value of 0.0 means that all CPUs were idle during the recent period of time observed, while a value of 1.0 means that all CPUs were actively running 100% of the time during the recent period being observed. All values betweens 0.0 and 1.0 are possible depending of the activities going on in the system. If the system recent cpu usage is not available, the method returns a negative value.
vendor:system_cpu_load 0.13440860215053763
# TYPE base:classloader_total_loaded_class_count counter
# HELP base:classloader_total_loaded_class_count Displays the total number of classes that have been loaded since the JVM has started execution.
```

and now in json format
```
curl -H "Accept: application/json" http://localhost:8080/metrics
```


```json
{
  "vendor": {
    "system.cpu.load": 0.07824364476724992
  },
  "base": {
    "classloader.totalLoadedClass.count": 15221,
    "cpu.systemLoadAverage": 0.52,
    "thread.count": 83,
    "classloader.currentLoadedClass.count": 15221,
    "jvm.uptime": 42882586,
    "memory.committedNonHeap": 109494272,
    "gc.PS MarkSweep.count": 3,
    "memory.committedHeap": 1344798720,
    "thread.max.count": 115,
    "gc.PS Scavenge.count": 13,
    "cpu.availableProcessors": 8,
    "thread.daemon.count": 40,
    "classloader.totalUnloadedClass.count": 1,
    "memory.usedNonHeap": 106301952,
    "memory.maxHeap": 3726639104,
    "memory.usedHeap": 517276800,
    "gc.PS MarkSweep.time": 283,
    "memory.maxNonHeap": -1,
    "gc.PS Scavenge.time": 287
  }
}
```

### Metrics Scopes

**Base Scope**
The Base Scope has all the core information of the server; the metrics that are REQUIRED by all application servers to implement. 
This scope will provide data on, among other things, heap memory, thread count, and available processors.

HTTP Verbs: GET, OPTIONS
Resource Path: /metrics/base

**Vendor Scope**
Vendor Scope exposes vendor-specific information. 
Each application server may have different implementations or internal components that can be monitored. 

HTTP Verbs: GET, OPTIONS
Resource Path: /metrics/vendor

**Application Scope**
Application Scope exposes the application-specific information. 
For this scope a Java API is provided to monitor different parts of the application. 

HTTP Verbs: GET, OPTIONS
Resource Path: /metrics/application

## HTTP Verbs
For each Resource URL there are two HTTP verbs that can be used to call the endpoint: 
**GET** and **OPTIONS**. The GET verb is used to retrieve the metric value:

GET /metrics/application/countMeBCounter
Response in Prometheus format:

```
# TYPE application:count_me_bcounter counter
# HELP application:count_me_bcounter Metrics to show how many times countMeB method was called.
application:count_me_bcounter{checkout="items"} 1
```

The format retrieved will be Prometheus by default, or add application/json in the HTTP  Accept header so the payload retrieved is JSON.

Response in application/json format:
```
{
   "countMeBCounter": 3
}
```

The OPTIONS HTTP verb is used to retrieve the metadata for the metric.


> Metadata is pieces of data that describe a metric. It will have attributes/fields, such as, the metric type (counter, gauge, meter, histogram or timer), description, displayName and so on.

This data is exposed via the HTTP Verb OPTIONS and Resource URL /metrics/{scope}/{metric_name}. See following example:

OPTIONS /metrics/application/countMeBCounter

```
{
    "countMeBCounter": {
        "name": "countMeBCounter",
        "displayName": "Method B Counter",
        "description": "Metrics to show how many times countMeB method was called.",
        "type": "counter",
        "unit": "none",
        "tags": "checkout=items"
    }
}
```

### Metadata

**@Counted**
A counter is a simple incrementing and decrementing long.

Check CounterExampleMetricController
A few examples:

```
curl http://localhost:8080/metrics/application/
```

```
{
    "countMeBCounter": 1,
    "countMeACounter": 7
}
```

```
curl http://localhost:8080/metrics/application/countMeBCounter
```

```
{
    "countMeBCounter": 1
}
```



**@Gauge**
A gauge is the simplest metric type that just returns a value.

TBC : check https://www.tomitribe.com/blog/getting-started-with-microprofile-metrics/


**@Metered**
A meter measures the rate at which a set of events occur.

TBC : check https://www.tomitribe.com/blog/getting-started-with-microprofile-metrics/


**@Timed**
It is a timer that tracks the duration of an event.

Check TimedExampleMetricController

```
curl http://localhost:8080/metrics/application/longRunningMethod
```

```
{
    "longRunningMethod": {
        "fiveMinRate": 0.028100229485082995,
        "max": 3000619000,
        "count": 2,
        "p50": 3000535000,
        "p95": 3000535000,
        "p98": 3000535000,
        "p75": 3000535000,
        "p99": 3000535000,
        "min": 3000535000,
        "fifteenMinRate": 0.10096614481431597,
        "meanRate": 0.0031658230508033805,
        "mean": 3000535008.402019,
        "p999": 3000535000,
        "oneMinRate": 0.01354217425950337,
        "stddev": 840.0589339037518
    }
}
```

## Fault Tolerance

Microservices are designed to run in a highly distributed environment. 
These type of environments introduce new challenges and new architecture problems and several stability patterns have been created to minimize the impact of
failures in distributed systems.


https://is.muni.cz/th/ubkja/masters-thesis.pdf
https://microprofile.io/project/eclipse/microprofile-fault-tolerance
https://microprofile.io/project/eclipse/microprofile-fault-tolerance/spec/src/main/asciidoc/fallback.asciidoc


### Fallback
Provide an alternative solution for a failed execution.

Check FaultyService and DataFallbackHandler for an example

Simulating a service when things goes wrong:
```
curl http://localhost:8080/rest/faulttorelance/true
```

Simulating a service when things goes well:
```
curl http://localhost:8080/rest/faulttorelance/false  
```

### Circuit Breaker Pattern
Gracefully degrade functionality when a method call fails. 
Use of the Circuit Breaker pattern can allow a microservice to continue operating when a related service fails, 
preventing the failure from cascading and giving the failing service time to recover.


**TBC**

### Bulkhead
Isolate failures in part of the system while the rest part of the system can still function.
 
**TBC**

### TimeOut and RetryPolicy
Defines TimeOut's and criterias when to retry
 
**TBC**



# Notes
MD guidelines by git: https://help.github.com/articles/basic-writing-and-formatting-syntax/
