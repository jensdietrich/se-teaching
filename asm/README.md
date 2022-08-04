# ASM-Based Bytecode Analysis and Manipulation

## Static Analysis Example 1 - Finding Synthetic Fields and Methods

`nz.ac.vuw.jenz.asm.staticanalysis1.FindSyntheticMethodsAndFields` is an example of a simple static analysis. It scans bytecode for the presence of synthetic fields and methods. It uses a byte code visitor for this purpose, defined in `SyntheticMemberVisitor`.

Run unit tests in `nz.ac.vuw.jenz.asm.staticanalysis1.TestFindSyntheticMethodsAndFields` to see this in action, they use test data with synthetic field generated to access outer class instances, and synthetic bridge methods
generated to compile an overridden method with covariant return type.

## Static Analysis Example 2 - Finding Field Writes in Methods

The second analysis detects field writes, it is implemented in `nz.ac.vuw.jenz.asm.staticanalysis2.FindFieldWrites` and uses a visitor in the same package. The visitor now is *nested*, i.e. `visitMethod` returns another visitor to analyse the body of the method being visited.

There are unit tests that illustrates how this works.

Note that the analysis also detects field writes in the special methods `<init>` and `<clinit>`, representing object and class initialisation, respectively. 

## Dynamic Analysis and Byte Code Manipulation with an ASM-Based Agent


This scenario implements an agent that monitors write access to fields when a program executes. Field writes are intercepted, and a record is stored in a simple memory database. A testing-scope dependency and some reconfiguration of the surefire testing plugin are used to test the agend without actually emplying it as a `java` runtime argument, see `pom.xml` for details.  

The agent class is `nz.ac.vuw.jenz.asm.instrumentation.LogFieldWriteAgent`, the actual byte code manipulation is implemented as an ASM visitor in `nz.ac.vuw.jenz.asm.instrumentation.LogFieldWriteVisitor`. 

There are no unit tests. Unit testing agents can be tricky as the JVM needs to be started with the `-javaagent` parameter, and the agent is just to be built. There are some approaches to reload classes with an agent, such as the [ea-agentloader](https://mvnrepository.com/artifact/com.ea.agentloader/ea-agent-loader) and [bytebuddy](https://bytebuddy.net/).

## Deploying and Using the Agent

An agent jar can be built with `mvn package` (note that this includes some redundant classes from other packages not actually needed). This is achieved by using the *maven assembly plugin* configured in `pom.xml`. In particular, the manifest of this jar contains the agent class set as *Premain-Class*.

The application also contains a very simple application `nz.ac.vuw.jenz.asm.example.App` that accesses a field. After building the project, run the following command:

`java -cp target/classes/ nz.ac.vuw.jenz.asm.example.App`

This will log start and termination to the console. Then run the application with the agent deployed as JVM argument as follows: 

`java -cp target/classes/ -javaagent:target/log-field-write-agent.jar nz.ac.vuw.jenz.asm.example.App`

This will also log the field write access to the console. This will look somehow like this:

```java
starting app: class nz.ac.vuw.jenz.asm.example.App
field access recorded: nz.ac.vuw.jenz.asm.example.App::field
         > java.base/java.lang.Thread.getStackTrace(Thread.java:1606)
         > nz.ac.vuw.jenz.asm.instrumentation.LogFieldWriteVisitor.fieldAccessLogged(LogFieldWriteVisitor.java:66)
         > nz.ac.vuw.jenz.asm.example.App.main(App.java:13)
terminating app: class nz.ac.vuw.jenz.asm.example.App

```

While the static analysis (example 2 described before) does also report the method where the field write takes place, the dynamic analsis can do more -- it can report the stack trace, i.e. the invocation chain of methods leading to field access. This  provides more analysis context. 
