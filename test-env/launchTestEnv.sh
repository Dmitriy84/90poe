#!/bin/bash
if [[ $1 == 'build' ]]
then
  cd ..
  mvn -DskipTests clean package spring-boot:repackage
  docker build --tag=dmytromalohlovets/message-server:latest .
# docker.io
  docker push dmytromalohlovets/message-server:latest
  cd test-env
fi

docker-compose up -d --force-recreate --remove-orphans
docker-compose exec kafka0  kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic devTesTask90POE
