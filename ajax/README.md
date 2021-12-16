This folder contains a basic Ajax example in two versions: with and without jquery. To **run the examples**, simply execute `mvn jetty:run` and point a browser to [http://localhost:8080/ajaxexample/](http://localhost:8080/ajaxexample/). 
This is using a **jetty** server deployed as a Maven plugin.

Alternatively, you can package the web application for deployment (as a `war` file) with `mvn package`, and deploy the war file in any application server, for 
instance by dropping it into **Tomcat**'s `webapps` folder.  Then start tomcat (run `bin/startup.sh`, although tomcat can also hot deploy) and point the browser to [http://localhost:8080/webapp/](http://localhost:8080/webapp/).

Note that while you type in names in the search box (try *Jo..*, the content of the text area is updated  by data
fetched  by  querying the first name service. You can monitor this with browser tools (Chrome:  View > Developer > Developer Tools > Network)). 
The `firstNames`  service is provided by the servlet `nz.ac.vuw.jenz.ajax.FirstNameService`.


