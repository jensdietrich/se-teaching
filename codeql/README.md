# Code QL Examples

Requires `codeql` to be installed locally. The version that was used to develop this is `2.9.3`. Follow instructions 
[https://codeql.github.com/docs/codeql-cli/getting-started-with-the-codeql-cli/](here).


The project contains some Java source code to be queried, and sh scripts to create the codeql database and query it, respectively.

## Building the database for this project 

1. `build_db.sh` -- build the codeql database to `codeql_db`, using `mvn` as build system. The folder must be empty before running this command.

## Analysing Standard Queries

1. Make sure that the Java query pack is installed (`codeql pack download codeql/java-queries`).
2. the queries are located in `~/.codeql`





