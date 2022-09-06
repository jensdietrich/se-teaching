#!/bin/bash

## build all projects -- baseline needs install to be used in order to build client
## @author jens dietrich

cd baseline
mvn clean install
cd ..

cd client
mvn clean package
cd ..

cd change1
mvn clean package
cd ..
