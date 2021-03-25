docker stop $(docker ps -aq);
docker rm $(docker ps -aq);
if [$1 = 'build']
then
    mvn clean install;
fi;
docker run -d --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver;
mvn spring-boot:run;
#open -a "Google Chrome" http://localhost:9090/swagger-ui.html
