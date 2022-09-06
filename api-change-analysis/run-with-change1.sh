#!/bin/bash

## build all projects -- baseline needs install to be used in order to build client
## @author jens dietrich

java -cp change1/target/easycrud-1.1.0.jar:client/target/easycrud-client-1.0.0.jar nz.ac.vuw.jenz.easycrud.client.Main

