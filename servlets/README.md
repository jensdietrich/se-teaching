This folder contains some basic servlet and JSP examples. To **run the examples**, simply execute `mvn jetty:run` and point a browser to [http://localhost:8080/webapp/](http://localhost:8080/webapp/). This is using a **jetty** server deployed as a Maven plugin.

Alternatively, you can package the web application for deployment (as a `war` file) with `mvn package`, and deploy the war file in any application server, for 
instance by dropping it into **Tomcat**'s `webapps` folder.  Then start tomcat (run `bin/startup.sh`, although tomcat can also hot deploy) and point the browser to [http://localhost:8080/webapp/](http://localhost:8080/webapp/).

To ***debug*** the application, its is recommended to install a jetty plugin for the IDE you are using, e.g.:

1. IntelliJ -- install IDEA Jetty Runner from Preferences > Plugins
2. Eclipse -- install the Jetty feature following these [instructions](https://eclipse-jetty.github.io/installation.html)
3. Netbeans -- has excellent built-in Tomcat and/or Glassfish integration, these servers can be used for debugging
4. professional / enterprise editions of IDEs may have built-in support to debug web applications without the need for further plugins


Note that servlets are configured (mainly: associated with URLs) using `web.xml` and not the more modern annotations. The reason is that 
jetty mvn plugin used has problems correctly consolidating configurations from various sources. In many cases, the servlet and filter
source code still has the annotations corresponding to the `web.xml` entries, but commented out.

## A Note on Java and JEE Versions Used

The current version is based on Java 17+ and the JEE API provided by jakarta (`jakarta.servlet` packages).
For a version based in the older `javax.servlet` packages using Java 1.8, checkout the tag `jee-by-javax`.