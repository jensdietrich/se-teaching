# Violating Contracts

The [contract of `java.util.Map::containsKey`](https://docs.oracle.com/javase/9/docs/api/java/util/Map.html#containsKey-java.lang.Object-) states that 
this method *"Returns true if this map contains a mapping for the specified key. More formally, returns true if and only if this map contains a mapping for a key k such that Objects.equals(key, k). (There can be at most one such mapping.)"*. 


Implementation classes like `java.util.HashMap` and `java.util.TreeMap` all comply to this contract, however, [java.util.IdentityHashMap](https://docs.oracle.com/javase/9/docs/api/java/util/IdentityHashMap.html) does not. Its documentation states tha: *"This class is not a general-purpose Map implementation! While this class implements the Map interface, it intentionally violates Map's general contract, which mandates the use of the equals method when comparing objects. This class is designed for use only in the rare cases wherein reference-equality semantics are required."*.

This project contains two tests illustrating this: a containsKey operation unexpeacitly fails when a `HashMap` instance is replaces by an `IdentityHashMap` instance. 
