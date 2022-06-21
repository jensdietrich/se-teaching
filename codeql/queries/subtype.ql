import java

from Class cl
where cl.getQualifiedName()="nz.ac.vuw.jenz.codeql_examples.subtyping.ClassB"
select cl.getQualifiedName() as a_class,cl.getASupertype+().getQualifiedName() as super_class
