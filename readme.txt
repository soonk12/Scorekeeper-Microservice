Setup

Create docker machine

Start docker machine
docker-machine start scorekeeper

Connect to docker machine



In docker/jboss directory
Download jboss
docker build -t soonk12/jboss-eap:7-beta .


Start compose
docker-compose --x-networking up -d


Deploy hero service:
mvn install -Pwildfly -Dwildfly.hostname=$(docker-machine ip scorekeeper) -Dwildfly.username=admin -Dwildfly.password=Admin#007