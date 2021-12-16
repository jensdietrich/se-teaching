## Mutation Testing

This example shows the use of mutation testing. Traditional coverage with tools like jacoco, emma or clover measures which parts of the program are executed when tests run. With mutation testing, we are intersted to check whether the state checks in tests (i.e., the `assert*` states) are meaningful. 

The examples in this test (a tax and a leap year calculator) have tests that have a high coverage. However, they are meaningless as the checks at the end of the test are always `assertTrue(true)`. I.e. tests will **always** succeed. 

Test coverage addresses this by mutating the program, for instance, by replacing `+` by `-` in arithmetic expressions. This should break the test. However, if the tests have meaningless assertions, they survive these modifications. 

The framework used here is [https://pitest.org/](https://pitest.org/), installed as a Maven plugin. To use it, run `mvn org.pitest:pitest-maven:mutationCoverage`. The mutation coverage reports are generated in `target/pitreports`.

