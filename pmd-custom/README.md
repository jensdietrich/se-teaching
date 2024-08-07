# Writing Custom Source Code Analyses as PMD Rules

This project shows how to build custom analyses with [pmd](https://pmd.github.io/). PMD is a language-agnostic source code analysis framework that supports Java, JavaScript, and a few other languages. 

PMD has a large set of on-board analyses (rules), those analyses can be defined by either traversing or querying (with XPath) the AST generated from the program under analysis. The precise nature of the AST depends on the language module used, in this project this is Java (this is a dependency in the pom, if this was replaced by the [JavaScript](https://mvnrepository.com/artifact/net.sourceforge.pmd/pmd-javascript) or some other module, analyses for other target languages can be written in the same manner, the ASTs supported by those modules will reflect the different grammars of the respective target languages). 


## Project Setup and Tests

The additional rules defined are declared in a rule set in `src/main/resources/category/vuw/pmd.xml`.  For each rule, there are matching tests. PMD has its own rather peculiar  testing framework. The test classes in `src/test/java/nz/ac/vuw/jenz/pmd/` don't do much, the actual testing logic is defined in xml files located in `src/test/resources/nz/ac/vuw/jenz/pmd/xml/`. 

## HelloWorld check implemented in Java

The pmd custom rule *HelloWorld* is a `HelloWorldRule` which detects classes named `HelloWorld` :-) , arguing that classes with such a name should not be part of real projects. 

The `HelloWorldDetectorRule` has the analysis logic, note the Java AST - specific superclass `net.sourceforge.pmd.lang.java.rule.AbstractJavaRule` (which would be different for an analysis in another language). The rule is implemented via an AST traversal -- PMD itself initiates the traversal, the rule only intercepts when a node representing a class declaration is visited, checks the name and creates an issue if necessary. 

## HelloWorld check for Java implemented as XPath query

PMD supports writing analyses as XPath queries on ASTs. In this case, no Java code is required, the rule set declaration (`src/main/resources/category/vuw/pmd.xml`) contain the query defining the analysis. This is convenient for simple analyses.

To aid the writing of xpath queries, pmd comes with a visual query designer / evaluator. To start this, do the following:

1. download pmd from [https://sourceforge.net/projects/pmd/files/pmd/](https://sourceforge.net/projects/pmd/files/pmd/)
2. unzip downloaded file
3. start the tool with `./bin/run.sh designer`


## HashcodeShouldNotReturnConstant for Java (alternative implementations using Java and XPath)

The rule finds implementations of `hashCode()` returning an int literal. This is an anti-pattern as the performance of hashed containers deteriorates from constant to linear for elements that are instances of the respective type.

Example:

```java
public class Foo {
	public int hashCode() {
		return 42;
	}
}
```

There is a Java and an XPath-based definitions in the repository. Note that for both one test fails, showing the limitations of the slightly simplified definitions. 


## NoTripleBang for JavaScript (alternative implementations using Java and XPath)

The example illustrated a custom analysis for JavaScript (officially ECMAScript). While the `!!` (*double bang*) is widely used in JavaScript to type cast variables, negating it does not make much sense, `!!!` is semantically equivalent to `!`. The rules checks for this pattern. 


## More Info

[This](https://pmd.github.io/latest/pmd_userdocs_extending_writing_rules_intro.html) is a tutorial on how yto write custom rules.










  