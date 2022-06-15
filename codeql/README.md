# Code QL Examples

Requires `codeql` to be installed locally. The version that was used to develop this is `2.9.3`.

The project contains some Java source code to be queried, and sh scripts to create the codeql database and query it, respectively.

1. `build_db.sh` -- build the codeql database to `codeql_db`, using `mvn` as build system. The folder must be empty before running this command.