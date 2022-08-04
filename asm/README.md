# ASM-Based Bytecode Analysis and Manipulation

## Static Analysis

`nz.ac.vuw.jenz.asm.staticanalysis.FindSyntheticMethodsAndFields` is an example of a simple static analysis. It scans bytecode for the presence of synthetic fields and methods. It uses a byte code visitor for this purpose, defined in `SyntheticMemberFinder`.

To run this utility, it needs one argument pointing to a folder containing bytecode (i.e. some Java classes). It is possible to run the tool on itself by using `target/classes` as argument (this folder is created when building the project with `mvn compile`). 


## Dynamic Analysis and Byte Code Manipulation with an ASM-Based Agent


This scenario implements an agent that monitors write access to fields when a program executes. Field writes are intercepted, and a record is stored in a simple memory database. A testing-scope dependency and some reconfiguration of the surefire testing plugin are used to test the agend without actually emplying it as a `java` runtime argument, see `pom.xml` for details.  

The agent class is `nz.ac.vuw.jenz.asm.instrumentation.NullLoggerAgent`, the actual byte code manipulation is implemented as an ASM visitor in `nz.ac.vuw.jenz.asm.instrumentation.CheckForNullableFieldAccess`. 

There are some simple tests that demonstrate this functionality. Note that tests may have to be run with Maven (`mvn test`), IDEs may not be able to pick up the surefire configuration.  
