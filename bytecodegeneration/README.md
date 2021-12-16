# Generating Java Byte Code 

This project contains examples how to generate Java byte code without the Java compiler (`javac`). This approach is used to create high-performance DSLs, like stylesheets, templates or expression languages.
The [ASM](https://asm.ow2.io/) library is used to facilitate byte code generation.

There are two examples `HelloWorld` and `Calculator`. There are tests for both, running the respective tests will:

1. generate the class file in `/generatedClasses`
2. load the class dynamically using a custom class loader
3. use reflection to invoke the `main` methods of the class


