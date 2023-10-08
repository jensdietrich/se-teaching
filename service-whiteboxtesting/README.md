This folder contains a whiteboc test for the `firstNames` service used by the ajax example.
Test use springframework static mocks (see dependencies in `pom.xml` and imports for details) that simulate the server. This significantly facilitates testing, in particular, an actual application server is not required.

This is an update version that uses the new servlet api 6 with jakarta package names, 
junit5 and spring-test 6. For an old version using the old javax servlet api and spring-test 5, see 
[service-whiteboxtesting-old](../service-whiteboxtesting-old). 

