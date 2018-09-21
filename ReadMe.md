#Building and running this project


## Using the Uber approach 

> NOTE: This pom is prepare to build a Uber Jar (Fat jar). Unfortunately it does not run under windows.
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
> runs under MS Windows

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

##Building and using a docker image

**TBC**




#MicroProfile
https://microprofile.io/
https://docs.payara.fish/documentation/microprofile/

##Health Check
https://docs.payara.fish/documentation/microprofile/healthcheck.html
https://microprofile.io/project/eclipse/microprofile-health

###REST endpoint
> /health


Can be tailored to add additional health endpoints:

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

#Notes
MD guidelines by git: https://help.github.com/articles/basic-writing-and-formatting-syntax/
