#!/bin/bash

## check for changes using revapi
## @author jens dietrich

revapi-0.11.6/revapi.sh --extensions=org.revapi:revapi-java:0.27.0,org.revapi:revapi-reporter-text:0.14.6 -o baseline/target/easycrud-1.0.0.jar -n change1/target/easycrud-1.1.0.jar
