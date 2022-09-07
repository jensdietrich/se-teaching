#!/bin/bash

## build all projects -- baseline needs install to be used in order to build client
## @author jens dietrich

cd baseline
mvn install
cd ..

cd client
mvn package
cd ..

cd change1
mvn package
cd ..

cd change2
mvn package
cd ..

cd change3
mvn package
cd ..

cd change4
mvn package
cd ..

cd change5
mvn package
cd ..
