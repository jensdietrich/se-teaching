# API Change Analysis

This project illustrates several types of breaking changes made to an upstream API provider. The provider implements (a dummy version of) a simple persistency service and a JSON-based (dummy) implementation, the component built is `easycrud`. The baseline version `easycrud-1.0.0` is defined in `/baseline`. A client project (`easycrud-client` defined `/client`) uses this API, it has `easycrud-1.0.0` as a dependency, and is compiled and tested against it.

Then there are several `/change*` projects which define alternative versions `1.1.0` of the service defined in `/baseline`, making sometimes subtle changes.  

There are some sh scripts illustrating the issues caused by various changes. Before running those scripts, run `build-all.sh` to build all projects and produce the respective components. This [this discussion](https://stackoverflow.com/questions/26522789/how-to-run-sh-on-windows-command-prompt) for how to run those scripts on Windows. 


`run-with-change*.sh` runs the client with the respective updated service `easycrud-1.1.0`, without recompiling it first. For changes that break binary compatibility, this will result in an error (some subtype of [LinkageError](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/LinkageError.html)).

`check-change*.sh` runs an [revapi](https://revapi.org/revapi-site/main/index.html) analysis in order to diff `easycrud-1.0.0.jar` and the respective `easycrud-1.1.0.jar`, and report any incompatible changes that may break clients.


| update      | Description | Source Compatible | Binary Compatible | Notes |
| ----------- | ----------- | ----------- | ----------- | ----------- |
| `change1`   |  `PersistencyService::readAll` has been changed from an abstract class to an interface -- this means that the invocation instructions in clients need to be changed too  | yes | no | this can be easily addressed by updating the dependency in client, and rebuilding the client |
| `change2`   | the return type of `PersistencyService::readAll` is replaced by a subtype (`Collection` > `Set`) | yes | no | this can be easily addressed by updating the dependency in client, and rebuilding the client |
| `change3`   | `throws IOException` has been added to `readAll`  | no | yes | the compiler will reject this as it requires the client to deal with the checked exception, the runtime however allows it -- thrown exceptions are not part of the descriptor used for linking |
| `change4`   | `throws IOException` has been added to `readAll`  | no | yes | almost the same as `change3` , but now  `readAll` actually throws an `IOException`. This will cause the client to fail with this exception, so this is an example of _behavioural incompatibility_.|
| `change5`   | the value of the `PersistencyService::VERSION` constant has changed  | no | no | the incompatibility is subtle -- due to constant inlining during compilation, the wrong value is printed even when the client runs with the new updated version of `easycrud`. Recompilation against the new library version will fix this.|







