# API Change Analysis

This project illustrates several types of breaking changes made to an upstream API provider. The provider implements (a dummy version of) a simple persistency service and a JSON-based (dummy) implementation, the component build is `easycrud`. The baseline version `easycrud-1.0.0` is defined in `/baseline`. A client project (`easycrud-client` defined `/client`) uses this API, it has `easycrud-1.0.0` as a dependency, and it compiled and tested against it.

Then there are several `/change*` projects which define alternative versions `1.1.0` of the service defined in `/baseline`, making subtle changes.  

There are sh scripts illustrating the issues caused by various changes. Before runing those scripts, run `build-all.sh` to build all projects and produce the respective components.


`run-with-change*.sh` runs the client with the updated service `easycrud-1.1.0`, without recompiling it first. 

`check-change*.sh` runs [revapi]() in order to diff `easycrud-1.0.0.jar` and the respective `easycrud-1.1.0.jar`, and report any incompatible changes that may break clients.






