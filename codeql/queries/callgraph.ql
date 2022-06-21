import java

from Callable callee, Callable caller
// calls and callsPoly have a similar semantics, see https://codeql.github.com/codeql-standard-libraries/java/semmle/code/java/Member.qll/type.Member$Callable.html
where caller.callsImpl(callee) and caller.getName()="foo" and callee.getDeclaringType().getPackage().getName()="nz.ac.vuw.jenz.codeql_examples.callgraph"
select caller.getDeclaringType().getQualifiedName()+"::"+caller.getName() as source,callee.getDeclaringType().getQualifiedName()+"::"+callee.getName() as target