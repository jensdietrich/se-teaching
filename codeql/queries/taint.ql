import semmle.code.java.dataflow.DataFlow

class MyDataFlowConfiguration extends DataFlow::Configuration {
  MyDataFlowConfiguration() { this = "MyDataFlowConfiguration" }

  override predicate isSource(DataFlow::Node source) {
    source.asParameter().getCallable().getName()="main"
  }

  override predicate isSink(DataFlow::Node sink) {
    sink.asParameter().getCallable().getName()="bar"
  }
}

from MyDataFlowConfiguration dataflow, DataFlow::Node src, DataFlow::Node snk
where dataflow.hasFlow(src, snk)
select src.asParameter().getCallable().getSignature()+"::"+src as source, snk.asParameter().getCallable().getSignature()+"::"+snk as sink