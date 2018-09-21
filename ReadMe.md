mvn clean install


Exec:
java -jar PATH_TO_PAYARA_MICRO --deploy executable\target\cdi.war


Create a uber jar

java -jar PATH_TO_PAYARA_MICRO --deploy executable\target\cdi.war --outputUberJar test-jar.jar
exec:
java -jar test-jar.jar


Any additional command line options you specify when creating an Uber JAR are recorded, so the Payara Micro instance is configured later when executing the packaged JAR:
java -jar payara-micro.jar --deploy test.war --port 9080 --lite --clusterName test-cluster --clusterPassword test-password --outputUberJar test2.jar
