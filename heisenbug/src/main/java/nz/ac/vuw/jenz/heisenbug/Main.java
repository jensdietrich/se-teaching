package nz.ac.vuw.jenz.heisenbug;

public class Main {

	public static void main(String[] args) {
		Person person = new Person();
		person.setName("Jens");
		System.out.println(person.hasBeenInitialised());
	}
}
