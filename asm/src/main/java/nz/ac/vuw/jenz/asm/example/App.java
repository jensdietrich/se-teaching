package nz.ac.vuw.jenz.asm.example;

/**
 * Executable to demonstrate how to deploy and use the agent.
 * @author jens dietrich
 */
public class App {

    private String field = null;

    public static void main(String[] args) {
        System.out.println("starting app: " + App.class);
        new App().field = "foo"; // to be logged by agent
        System.out.println("terminating app: " + App.class);
    }

}
