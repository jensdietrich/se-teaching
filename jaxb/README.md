##README

A simple scenario showing how a database access layer can be generated from a database schema. In this case, the schema is an XML schema  in 
`main/resources/email.xsd`. The generated code can be found in `target/generated-sources` after running `mvn compile`. 

Note that there is no other source code in this program, only some tests to illustrate how to use the parser in an application. 

XSD is used via a Maven plugin (see `pom.xml` for details), the underlying JAXB compiler is a binary `xjc` in the Java distribution (version 8). If code generation fails, check whether this binary is in the PATH.
 
In Java 9 JaxB was flagged as depricated and finally removed in Java 11. This example requires Java 8 (can be verified by running `java -version` and `javac -version`).
See this post [https://stackoverflow.com/questions/52502189/java-11-package-javax-xml-bind-does-not-exist] for a discussion on how to run this with newer versions of Java.
 
