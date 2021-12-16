package nz.ac.vuw.jenz.rest.helloworld;

import io.javalin.Javalin;

/**
 * Basic example to start javelin with a simple Hello World service.
 * Note that this does not create a war file, the server is embedded into the framework.
 * @author jens dietrich
 */
public class HelloWorld {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        app.get("/", ctx -> {
            ctx
                .contentType("text/plain")
                .result("Hello World\n");
        });
    }
}


