#!/bin/bash

echo "server install start..."
process_var="SERVER"
BACKEND_DIR="xmeme-backend"
FRONTEND_DIR="xmeme-frontend"
TARGET_DIR="target"
JAR_NAME="XMeme-1.0.jar"


#run
cd $BACKEND_DIR/$TARGET_DIR

echo "$process_var starting server"
nohup java -jar $JAR_NAME &

#frontend directory
cd ../../$FRONTEND_DIR

#start app
cp properties.txt .env.local
echo "$process_var npm install"
npm install

echo "$process_var npm start"
nohup npm start &

echo "server install end..."


