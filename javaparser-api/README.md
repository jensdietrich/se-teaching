# The JavaParser API

This project contains some simple analyses written using the [javaparser](https://github.com/javaparser/) API. 

They are ported from the [equivalent analyses written in PMD](https://github.com/jensdietrich/se-teaching/tree/main/pmd-custom), and have similar limitations. The javaparser offers a visitor API to traverse ASTs, and a functional API that allows to write analyses with lambdas / list comprehensions. The later is used in the examples.