#!/usr/bin/env bash

set -e
set -o pipefail

if [ -z "$FILE" ];
then
    FILE="cliniasearch/src/test/java/ca/clinia/search/saas/Helpers.java"
fi

echo "Restoring Helper file..."
mv $FILE.bak $FILE
rm $FILE.tmp