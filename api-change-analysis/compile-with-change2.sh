#!/bin/bash
## recompile the client sources against a changed library easycrud-1.1.0.jar
## @author jens dietrich

mkdir tmp
javac -d tmp -cp  change2/target/easycrud-1.1.0.jar client/src/main/java/nz/ac/vuw/jenz/easycrud/client/Main.java
