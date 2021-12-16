# Static Null Pointer Analysis with the Checkerframework

Several scenarios showing how potenial null pointer problems can be detected with static analysis, using the 
[checkerframework](https://checkerframework.org/). The checkerframework uses type annotations and subtype relationships.
This approach is also used by the null checkers developed by [facebook (`infer eradicate`)](https://cacm.acm.org/magazines/2019/8/238344-scaling-static-analyses-at-facebook/fulltext) and [uber (`NullAway`)](https://arxiv.org/pdf/1907.02127). 

Issues are detected via a compiler plugin, to see the analysis results, run `mvn compile`. Issues detected by the checkframework are
reported as compiler warnings, to report them as compiler errors, disable the `<arg>-Awarns</arg>` option in the plugin configuration
in `pom.xml`. Java 8 is required (newer versions might not work or may require a re-configuration of plugins used, confirm JRE version with `java -version` before trying to run the code).

1. `PostConditionExample1` -- Checking postconditions with the checkerframework. The checker will detect a possible nullpointer exception in main. This is a *true positive* that actually would occur when executing the program.
2. `PostConditionExample2` -- Checking postconditions with the checkerframework. The checker will detect a possible nullpointer exception in main. This is a *false positive* that would not occur when executing the program. This illustrates a common shortcoming of static analysis tools. 
3. `PostConditionExample3` -- Checking postconditions with the checkerframework. The checker will check the `@NonNull` annotation and emmit a warning that this is actually not correct.
4. `PostConditionExample4` -- Checking postconditions with the checkerframework. The check now succeeds without a warning, based on the confirmed `@NonNull` annotation of the method called in `main`.
5. `PreConditionExample1` -- a similar scenario with an argument annotation, demonstrating how the checkerframework can also check preconditions.
6. `InvariantExample1` -- a similar scenario with a field annotation, demonstrating how the checkerframework can also check class invariants. 
7. `InheritanceExample1` and `InheritanceExample1` show how the checkerframework can be used to enforce some aspects of [LSP](https://en.wikipedia.org/wiki/Liskov_substitution_principle) - overriding a methods and changing the return type from `@NonNull` to `@Nullable` is detected, this scan be considered as a weakening of the post condition of this method. The opposite is ok.