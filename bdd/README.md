# BDD with cucumber

A simple scenario to show how JUNit tests are written using the [Gerkhin DSL](https://docs.cucumber.io/gherkin/reference/) in order to make them more readable to stakeholders. 

Key classes and resources are:

1. `nz.ac.vuw.jenz.bdd.Adder` in `src/main/java` a simple math class with an intenionally incorrect `plus(int,int)` method (to show how failing tests are reported)
2. `calc.feature` in `src/test/resources/features/` definition of actual tests using the DSL
3. `test.nz.ac.vuw.jenz.bdd.TestAdder` in `src/test/java` a unit test wrapper for the actual tests defined in `calc.feature`
4. `test.nz.ac.vuw.jenz.bdd.Glue` in `src/test/java` - a mapping that defines the semantics of the phrases used in the feature definitions `calc.feature`

To run tests, run `mvn test` from the command line, or invoke it from an IDE.