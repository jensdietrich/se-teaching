# Using SpotBugs

A project with a few classes with bugs, and SpotBugs (the successor of the popular FindBugs) integrated into Maven. The Maven setup requires some additional plugins due to a bug discussed [here](https://stackoverflow.com/questions/51091539/maven-site-plugins-3-3-java-lang-classnotfoundexception-org-apache-maven-doxia). 

To run the analysis, run `mvn compile site`, the bug report will be created in `target/site/spotbugs.html`.
Also check the comments in the respective sources. 

Note that `mvn clean site` will fail as classes are not compiled, `mvn clean compile site` will work  as expected.