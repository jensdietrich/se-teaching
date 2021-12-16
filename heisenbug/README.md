# Heisenbug

This is a very simple example to illustrate a [Heisenbug](https://en.wikipedia.org/wiki/Heisenbug). To observe this, first run `Main` -- this will print `false` to the console, indicating the initialisation state of the `person` object.

The add a breakpoint to the `main` method (marked by a `*`)

```java
	public static void main(String[] args) {
		Person person = new Person();
* 		person.setName("Jens");
		System.out.println(person.hasBeenInitialised());
	}

```

and start the debugger. In the debugger, inspect the `person` variable, and then proceed. The program will run to the end, and print `true`. 

The explanation for this changed behavior is that inspecting `person` requires the debugger to print the `person` object, and to do so, it needs to be converted to a string. To do this `Person::toString` is invoked, which in turns invokes `Person::getAddress` which then triggers lazy initialisation of the field. So what looks like a query methods does actually have a [side effect](https://en.wikipedia.org/wiki/Side_effect_(computer_science)). 

### Note

This is a Maven project that can be imported into IDEs (IntellliJ, Eclipse, etc). 
