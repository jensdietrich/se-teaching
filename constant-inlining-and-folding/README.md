# Constant Inlining and Folding 

The Java compiler inlines constants and evaluates simple arithmetic expressions. To see this, inspect the source code and note 
how the classes `App<id>` references constants defines in `Constant<id>` (`id` is a counter). 

Then compile the project (`mvn compile`) and inspect the bytecode generated in `target/classes`, for instance:

`javap -c target/classes/nz/ac/vuw/jenz/constants/App1.class`

This will yield something like this:

```
public class nz.ac.vuw.jenz.constants.App1 {
  public nz.ac.vuw.jenz.constants.App1();
    Code:
       0: aload_0
       1: invokespecial #1       // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #7       // Field java/lang/System.out:Ljava/io/PrintStream;
       3: bipush        42
       5: invokevirtual #15      // Method java/io/PrintStream.println:(I)V
       8: return
```

Note that there is no reference to `Constant1`, however, the constant 42 is referenced (`bipush 42`).

The only exception is scenario 4 (`Constant4`/`App4`) -- here the constant is defined using the Integer wrapper types (`Integer`), and despite this class being immutable, it is not being inlined. 

```
...
       3: getstatic     #13     // Field nz/ac/vuw/jenz/constants/Constant4.MAGIC:Ljava/lang/Integer;
       6: invokevirtual #19     // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
...
}

```

Example overview:

| id      | description |
| ----------- | ----------- |
| 1      | simple int constant -- will be inlined       |
| 2   | simple String constant -- will be inlined       |
| 3   | int expression using multiple variables and simple computations -- will be folded and inlined        |
| 4   | Text        |

See those [puzzlers](https://www.slideshare.net/JensDietrich/presentation-30367644) for the relevance this has on sofwtare maintanence (when upgrading binaries without re-compilation)



