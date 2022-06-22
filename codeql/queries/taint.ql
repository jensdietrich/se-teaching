import semmle.code.java.dataflow.DataFlow

predicate isMain(Callable method) {
  // The method must have the name "newJMXConnectorServer"...
  // method.getName() = "main"
  // could further restrict this:
  // and method.getDeclaringType().hasQualifiedName("<package>", "<class>")
  method.getName()="main"
}

predicate isOSCommand(Callable method) {
  // The method must have the name "newJMXConnectorServer"...
  // method.getName() = "bar"
  // could further restrict this:
  // and method.getDeclaringType().hasQualifiedName("<package>", "<class>")
  method.getName()="exec"
}

class MyDataFlowConfiguration extends DataFlow::Configuration {
  MyDataFlowConfiguration() { this = "MyDataFlowConfiguration" }
  override predicate isSource(DataFlow::Node source) {
    isMain(source.asParameter().getCallable())
  }

  override predicate isSink(DataFlow::Node sink) {
    isOSCommand(sink.asParameter().getCallable())
  }
}

from MyDataFlowConfiguration dataflow, DataFlow::Node src, DataFlow::Node snk
where dataflow.hasFlow(src, snk)
select src.asParameter().getCallable().getSignature()+"::"+src as source, snk.asParameter().getCallable().getSignature()+"::"+snk as sink