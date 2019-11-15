#!/usr/bin/env bash

set -e
set -o pipefail

if [ -z "$FILE" ];
then
    FILE="cliniasearch/src/test/java/ca/clinia/search/saas/Helpers.java"
fi
echo "Helper file: $FILE."
cp $FILE $FILE.bak

echo "Replacing environment variable..."
sed -i.tmp "s/%CLINIA_APPLICATION_ID%/${CLINIA_APPLICATION_ID}/g" $FILE
sed -i.tmp "s/%CLINIA_API_KEY%/${CLINIA_API_KEY}/g" $FILE
sed -i.tmp "s/%JOB_NUMBER%/${TRAVIS_JOB_NUMBER}/g" $FILE