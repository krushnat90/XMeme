#!/bin/bash

BACKEND_DIR="xmeme-backend"
FRONTEND_DIR="xmeme-frontend"
TARGET_DIR="target"
JAR_NAME="XMeme-1.0.jar"

## BACKEND
cd $BACKEND_DIR

#build
mvn -e clean install

#run
cd $TARGET_DIR

nohup java -jar $JAR_NAME &

#frontend directory
cd ../../$FRONTEND_DIR

#start app
npm install

npm start &


