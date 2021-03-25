mvn clean install &&
docker build -t credit-card-es . &&
docker run -p 9090:9090 credit-card-es