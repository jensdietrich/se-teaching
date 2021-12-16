This folder contains a blackbox test for the `firstNames` service used by the ajax example.
Tests are performed against the service deployed on an actual server, server startup and shutdown  is managed in the test fixture via
native methods (check the `startServer` and `stopServer` methods in `test.nz.ac.vuw.jenz.ajax.BlackBoxTest`.

JUnit preconditions (aka assumptions) are used to test whether the server is running. I.e., if the server is not running and test are executed (from the IDE or with `mvn test`), they will not be flagged
as *failed*, but as *skipped* , indicating that we care unable to run the tests and establish whether they pass or not !! 


