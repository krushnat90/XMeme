#!/bin/bash

echo "Starting install..."

### install basic ###

#maven
sudo apt install -y maven

#curl
sudo apt install -y curl

#Mongo
curl -fsSL https://www.mongodb.org/static/pgp/server-4.4.asc | sudo apt-key add -
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu bionic/mongodb-org/4.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.4.list
sudo apt update
sudo apt install -y mongodb-org
sudo systemctl start mongod.service

##create necessary tables at mongo
mongo < mongodb-starter.js

echo "install done..."