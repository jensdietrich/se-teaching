This is a deliberately unsecure web application, used to demonstrate static security analysis. It is a simplified version of [WebGoat](https://owasp.org/www-project-webgoat/) , therefore the name.


![image](webkid.jpeg)


## Running a local security analysis with codeql

1. make sure `codeql` is installed and in the path
2. build the database for this project -- run `codeql database create codeql_db -l java -c mvn clean compile`
3. run standard queries for Java -- run `codeql database analyze --format=csv --output=results.csv codeql_db codeql/java-queries`
4. inspect `results.csv`

## Seeded Vulnerabilities (all detected by standard CodeQL queries)

1. `nz.ac.vuw.jenz.webkid.GetClientDetails` tries to locate a client record by id, if no such record is found, a website with a message is generated that includes the id.
This makes it possible to use an id containing malicious javascript code, this is an example of a [cross-site scripting vulnerability](https://owasp.org/www-community/attacks/xss/). 
2. `nz.ac.vuw.jenz.webkid.GetClientDetails` uses the id to build a SQL query with string concatenation. This is an example of a [SQL inject vulnerability](https://owasp.org/www-community/attacks/SQL_Injection).
3. `nz.ac.vuw.jenz.webkid.GetClientDetails2` is almost identical to `GetClientDetails` except that the input string is reversed. The vulnerabilities are still present and are being detected, illustrating the difference between "plain" data flow analysis and taint analysis.
3. `nz.ac.vuw.jenz.webkid.UploadBulkData` allows to upload a list of client data, to be inserted into the database. Deserialization may trigger arbitrary code execution, this is an example of an [unsafe deserialisation vulnerability](https://owasp.org/www-project-top-ten/2017/A8_2017-Insecure_Deserialization).
