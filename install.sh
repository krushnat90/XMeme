#!/bin/bash

echo "Starting install..."

process_var="INSTALL"
BACKEND_DIR="xmeme-backend"
TARGET_DIR="target"

### install basic ###

#maven
echo "$process_var maven install"
sudo apt install -y maven

#curl
echo "$process_var curl install"
sudo apt install -y curl

#Mongo
echo "$process_var mongo install"
curl -fsSL https://www.mongodb.org/static/pgp/server-4.4.asc | sudo apt-key add -
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu bionic/mongodb-org/4.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.4.list
sudo apt update
sudo apt install -y mongodb-org
sudo systemctl start mongod.service

#create necessary tables at mongo
echo "$process_var mongo setup"
mongo < mongodb-starter.js

#node
echo "$process_var node install"
curl -sL https://deb.nodesource.com/setup_10.x -o nodesource_setup.sh
sudo bash nodesource_setup.sh
sudo apt install -y nodejs

## BACKEND
cd $BACKEND_DIR

#build
echo "$process_var maven install"
mvn -e clean install

cd ../

echo "install done..."