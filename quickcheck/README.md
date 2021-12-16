This project illustrates how (the Java version of) [quickcheck](https://github.com/pholser/junit-quickcheck) works. The domain model is a very simple `Rectangle` class with `length` and `height` properties. 

The first tested invariant is whether the size can be divided by length and height (e.g. `size % length == 0`, similar for height). The randomly generated test input shows that this invariant can be violated. Quickcheck also constructs an example how the constraints are violated, the respective data is output to the console and can be used to create a *normal* test case. The violation is due to an `int` overflow. 

The second test reveals that the implementations of `equals` and `hashCode` violate the contract for those methods: two instances of `Rectangle` can be equal, yet have different hash codes. It is much more expensive to find an example illustrating this as the test uses two randomly generated objects as input, and therefore *quadratic blowup* occurs. Therefore the number of trials has to be larger, and / or the size of the randomly generated objects has to be reduced (see `RectangleGenerator` for details).

Note that due to the complexity issue different generators have to be used in this example: the overflow is only revealed if large rectangles are generated. To reveal a hash contract violation, small rectangles need to be used, otherwise the probability of finding a counter-example becomes too low. 

quickcheck integrates with junit via a custom runner, i.e. the tests can simple be executed with the standard command `mvn test`, the
test results are stored in the standard test reports in `target/surefire-reports`. Those tests can also be executed in other test runners, including the test runners integrated in IDEs like IntelliJ and Eclipse.  