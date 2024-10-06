## Regression Test Generation with Randoop 

[Randoop](https://randoop.github.io/randoop/) can be used to create regression tests. The project has two projects `versions` and `version2` and a single method `pck.Foo` with a single method `pck.Foo::cryptHash`. In version 1 the hash is computed using MD5, in version 2 this is replaced by SHA1, and an additional pre-condition check is added. 

The projects can be build by CDing into the project folders and running `mvn -Dmaven.test.skip=true package`. Tests are skipped due to the behaviour of the regression tests to be generated (since those tests are distributed in the repo).

Using *randoop*, the following commands generate regression tests:

`java -Xmx3000m -classpath version1/target/foo-1.0.0.jar:randoop-all-4.3.3.jar randoop.main.Main gentests --testjar=version1/target/foo-1.0.0.jar --output-limit=100 --junit-package-name=regressiontests`

This generates regression tests for `version1` in `/regressiontests`. Those tests can then be copied into the projects (`<version*>/src/test/java` folders) and run using `mvn test`. Those tests pass for `version1`, and fail for `version2`, reflecting the changed behaviour.

Note that test generation is often non-deterministic, i.e. repeating generation may produce similar, yet different tests.    


[Evosuite](https://github.com/EvoSuite/) has a similar feature, however, it seems to have been [disabled in recent versions](https://github.com/EvoSuite/evosuite/issues/353).

### References

1. Pacheco, Carlos, and Michael D. Ernst. "Randoop: feedback-directed random testing for Java." OOPSLA'07.