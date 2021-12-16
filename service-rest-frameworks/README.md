##REST Examples using Frameworks 

### Server (Basic)

[Javelin](https://javalin.io/) is used as a server-side framework. The server is the executable class `nz.ac.vuw.jenz.rest.helloworld.HelloWorld`, it uses Jetty internally. This simple Hello World - type example starts the server at [localhost:8080](http://localhost:8080), and can be tested by simply pointing the browser to this URL. 

### Server (CRUD)

This is a more sophisticated example that exposes a [CRUD service](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) of a domain object 
via HTTP services. The domain model consists of a single class `nz.ac.vuw.jenz.rest.Order` with fields for `id`, `product`, `amount` and `price`, and some  pre-defined instances.

There is no real persistence, this is simulated with a static variable in `nz.ac.vuw.jenz.rest.server.OrderDB` for simplicity. The server executable is `nz.ac.vuw.jenz.rest.server.OrderManagement`. 
 
The example uses the [javelin](https://javalin.io/) built-in framework support for CRUD, `nz.ac.vuw.jenz.rest.server.OrderDB` implements `io.javalin.apibuilder.CrudHandler` for this purpose.

### Client (CRUD)

There are several client executables in `nz.ac.vuw.jenz.rest.client` to read and manipulate data. To execute them, the server (`OrderManagement`, see above) must be running. Note that these applications may behave differently when executed multiple times (without restarting the server) -- e.g.  deleting an order with a given id may work once, but fail if attempted twice as the order is no longer available. The scripts report the HTTP status codes of the service responses for more info. 

The client-side framework used is [jersey](https://jersey.github.io/). 

### Databinding (Converting between Objects and JSON)

Part of both the client and the server-side framework used is that the order data transferred must be encoded and decoded. 
JSON is used for this purpose. For the process of converting `Order` instances to JSON and vice versa, [Jackson](https://github.com/FasterXML/jackson-databind) is used.
The data mapper makes it very easy to convert simple Java objects such as `Order` instance , complying with some basic requirements  (so-called [Java Beans](https://en.wikipedia.org/wiki/JavaBeans)) to and from JSON.
In the client code, this conversion is visible, the server framework also uses this functionality but it is hidden. 

Note that this simplicity is somehow deceiving: for more complex real-world  domain models the mapping is less straight forward and may require significant customising.