Setup

Create docker machine

Connect to docker machine



In docker/jboss directory
Download jboss
docker build -t soonk12/jboss-eap:7-beta .


Deploy hero service:
mvn install -Pwildfly -Dwildfly.hostname=$(docker-machine ip scorekeeper) -Dwildfly.username=admin -Dwildfly.password=Admin#007