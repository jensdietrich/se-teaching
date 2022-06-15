#!/bin/sh

DB="codeql_db"
if [ -d $DB ]
then
    echo "folder $DB exists, delete this first to recreate."
    exit 1
fi
# clean is necessary if classes already exist, odeql will complain that no classes have been found
codeql database create $DB -l java -c 'mvn clean install'




