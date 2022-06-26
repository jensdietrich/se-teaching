# Code QL Examples

Requires `codeql` to be installed locally. The version that was used to develop this is `2.9.3`. Follow instructions 
[https://codeql.github.com/docs/codeql-cli/getting-started-with-the-codeql-cli/](here).


The project contains some Java source code to be queried, and sh scripts to create the codeql database and query it, respectively.

## Building the database for this project 

1. `build_db.sh` -- build the codeql database to `codeql_db`, using `mvn` as build system. The folder must be empty before running this command.

## Running Standard Queries

1. Make sure that the Java query pack is installed (`codeql pack download codeql/java-queries`).
2. the queries are located in `~/.codeql`
3. then run `codeql database analyze --format=csv --output=results.csv codeql_db codeql/java-queries`

## Running Custom Queries

1. download and install Java pack as described above
2. run `codeql query run --database codeql_db queries/subtype.ql --additional-packs ~/.codeql/packages/codeql/java-all`  

The `--additional-packs` features looks unnecessary, but seems to be required. Replace `queries/subtype.ql` by the respective query. 

## Hint: Editing Queries

The [online query console](https://lgtm.com/) offers autocompletion.

## Exporting Query Results to Files

1. The following parameter used with `codeql query run` exports query results to the proprietary *bqrs* format: `-o query-results.bqrs`
2. The following command can then be used to convert this to csv, where the column header row (1st row) correspond to variable names in the `select` clause (use the `as <varname>`) construct to assign simple names to expressions in the `select` clause: `codeql bqrs decode --format csv -o query-results.csv query-results.bqrs` . The column seperator used is comma, cell values are in double quotes.