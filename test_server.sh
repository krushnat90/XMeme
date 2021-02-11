!/bin/bash

GIT_USER="krushnat90"
GIT_PAT="1gytsb2Nix5htwsJNEsG"
GIT_REPO="https://$GIT_USER:$GIT_PAT@gitlab.crio.do/COHORT_ME_BUILDOUT_XMEME_ENROLL_1612436694845/krushnat90-me_buildout_xmeme.git"
CODE_DIR="krushnat90-me_buildout_xmeme"

SERVER_URL="http://localhost:8081/memes"

# git clone the repo
git clone $GIT_REPO

# cd to the cloned repo directory
cd $CODE_DIR

# Run the user’s installation steps which will install any necessary dependencies required for the server to run, with sudo permission
chmod +x install.sh
sudo ./install.sh


# 1. Run the user’s server execution steps which will bring up the server
# 2. We’ll be running your server_run.sh as a background process (using &) so that we can run the next set of commands
chmod +x server_run.sh
./server_run.sh &


# 3. Add a sleep timer to sleep.sh depending upon how long you want to sleep so that the server is ready.
chmod +x sleep.sh
./sleep.sh


# Execute the GET /memes endpoint using curl to ensure your DB is in a clean slate
# Should return an empty array.

curl --location --request GET $SERVER_URL


# Execute the POST /memes endpoint using curl

curl --location --request POST $SERVER_URL \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "xyz",
"url": "abc.com",
"caption": "This is a meme"
}'


# Execute the GET /memes endpoint using curl

curl --location --request GET $SERVER_URL


# If you have swagger enabled, make sure it is exposed at localhost:8080

curl --location --request GET 'http://localhost:8081/swagger-ui/'