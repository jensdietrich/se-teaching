# ASM-Based Bytecode Analysis and Manipulation

## Static Analysis

`nz.ac.vuw.jenz.asm.staticanalysis.FindSyntheticMethodsAndFields` is an example of a simple static analysis. It scans bytecode for the presence of synthetic fields and methods. It uses a byte code visitor for this purpose, defined in `SyntheticMemberFinder`.

To run this utility, it needs one argument pointing to a folder containing bytecode (i.e. some Java classes). It is possible to run the tool on itself by using `target/classes` as argument (this folder is created when building the project with `mvn compile`). 


## Dynamic Analysis and Byte Code Manipulatiom with an ASM-Based Agent


TODO
