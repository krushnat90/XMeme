#!/bin/bash

BACKEND_DIR="xmeme-backend"
TARGET_DIR="target"
JAR_NAME="XMeme-1.0.jar"

## BACKEND
cd $BACKEND_DIR

#build
mvn -e clean install

#run
cd $TARGET_DIR

nohup java -jar $JAR_NAME &




