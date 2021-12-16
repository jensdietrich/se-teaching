# A Collection Protection Proxy using DynamicProxy 

A simple scenario to show how to use Javas dynamic proxies. The example uses this feature to create a read-only collection wrapper
similar to `java.util.Collections::unmodifiableCollection`. Invocations to methods defined in `java.util.Collection` are intercepted, and 
if a mutating method is detected (detection uses naming patterns like `add*`), an `UnsupportedOperationExcepotion` is thrown. See tests 
for details on how to use this utility.

This approach is elegant but has some limitations that render it unfit for practical use:

 * subtypes may introduce additional methods which mutate the collection, and the proxy wont capture them
 * state can still be mutated via `Collection::iterator` and then `Iterator::remove`
 * it is likely to be (much) slower than the static proxy `Collections::unmodifiableCollection`