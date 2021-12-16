# XML Decoder / Encode

Example showing how by using "beans" enables reflection-based serialization / deserialization of objects. 

To illustrate this, remove or rename setters / getters / default constructors in `Student` -- those are the conventions of Java beans, 
and observe the effect this has on (de-)serialization (by running tests). 