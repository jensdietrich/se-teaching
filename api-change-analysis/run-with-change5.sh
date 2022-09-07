#!/bin/bash
## run the client build (compiled) with easycrud-1.0.0.jar against a changed library easycrud-1.1.0.jar
## @author jens dietrich

java -cp change5/target/easycrud-1.1.0.jar:client/target/easycrud-client-1.0.0.jar nz.ac.vuw.jenz.easycrud.client.Main
