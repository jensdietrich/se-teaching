import semmle.code.java.dataflow.DataFlow

class Configuration extends DataFlow::Configuration {
  Configuration() {
    this = "MainParamToURL Configuration"
  }

  override predicate isSource(DataFlow::Node source) {
    // source.asExpr() instanceof StringLiteral
    source.asParameter().getCallable().getName()="main"
  }

  override predicate isSink(DataFlow::Node sink) {
    exists(Call call |
      sink.asExpr() = call.getArgument(0) and
      call.getCallee().(Constructor).getDeclaringType().hasQualifiedName("java.lang", "Runtime")
    )
  }
}

from DataFlow::Node src, DataFlow::Node sink, Configuration config
where config.hasFlow(src, sink)
select src, sink