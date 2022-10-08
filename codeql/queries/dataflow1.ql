import java
import semmle.code.java.dataflow.DataFlow

from Method m, Call call, Parameter p
where
  m.getDeclaringType().hasQualifiedName("java.lang", "Runtime") and
  DataFlow::localFlow(DataFlow::parameterNode(p), DataFlow::exprNode(call.getArgument(0)))
select m,call.getCaller() as caller,call.getCallee() as callee